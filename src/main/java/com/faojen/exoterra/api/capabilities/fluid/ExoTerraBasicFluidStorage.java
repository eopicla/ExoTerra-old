package com.faojen.exoterra.api.capabilities.fluid;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import javax.annotation.Nonnull;

import java.util.function.Predicate;

/**
 * This fluid tank class will hold any fluid. Haven't yet figured out how to use the validator stuff...
 */
public class ExoTerraBasicFluidStorage implements IFluidHandler, IFluidTank {
    private static final String FLUID = "fluid";
    private BlockEntity tile;
    protected FluidStack fluid = FluidStack.EMPTY;
    private int capacity;
    @Nonnull

    public ExoTerraBasicFluidStorage(BlockEntity tile , int capacity)
    {
        this.capacity = capacity;
        this.tile = tile;
    }

    public ExoTerraBasicFluidStorage setCapacity(int capacity)
    {
        this.capacity = capacity;
        return this;
    }

    public ExoTerraBasicFluidStorage setValidator(Predicate<FluidStack> validator)
    {
        return this;
    }

    public boolean isFluidValid(FluidStack stack)
    {
        return true;
    }

    public int getCapacity()
    {
        return capacity;
    }

    @Nonnull
    public FluidStack getFluid()
    {
        return fluid;
    }

    public Fluid getFluidSingle()
    {
        return fluid.getFluid();
    }

    public int getFluidAmount()
    {
        return fluid.getAmount();
    }

    public ExoTerraBasicFluidStorage deserializeNBT(CompoundTag compound) {

        this.fluid = FluidStack.loadFluidStackFromNBT(compound.getCompound(FLUID));

        return this;

    }

    public CompoundTag serializeNBT() {

        CompoundTag compound = new CompoundTag();
        compound.put(FLUID, this.fluid.writeToNBT(new CompoundTag()));
        return compound;

    }

    @Override
    public int getTanks() {

        return 1;
    }

    @Nonnull
    @Override
    public FluidStack getFluidInTank(int tank) {

        return getFluid();
    }

    @Override
    public int getTankCapacity(int tank) {

        return getCapacity();
    }

    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {

        return isFluidValid(stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action)
    {
        if (resource.isEmpty() || !isFluidValid(resource))
        {
            return 0;
        }
        if (action.simulate())
        {
            if (fluid.isEmpty())
            {
                return Math.min(capacity, resource.getAmount());
            }
            if (!fluid.isFluidEqual(resource))
            {
                return 0;
            }
            return Math.min(capacity - fluid.getAmount(), resource.getAmount());
        }
        if (fluid.isEmpty())
        {
            fluid = new FluidStack(resource, Math.min(capacity, resource.getAmount()));
            onContentsChanged();
            return fluid.getAmount();
        }
        if (!fluid.isFluidEqual(resource))
        {
            return 0;
        }
        int filled = capacity - fluid.getAmount();

        if (resource.getAmount() < filled)
        {
            fluid.grow(resource.getAmount());
            filled = resource.getAmount();
        }
        else
        {
            fluid.setAmount(capacity);
        }
        if (filled > 0)
            onContentsChanged();
        return filled;
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action)
    {
        if (resource.isEmpty() || !resource.isFluidEqual(fluid))
        {
            return FluidStack.EMPTY;
        }
        return drain(resource.getAmount(), action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action)
    {
        int drained = maxDrain;
        if (fluid.getAmount() < drained)
        {
            drained = fluid.getAmount();
        }
        FluidStack stack = new FluidStack(fluid, drained);
        if (action.execute() && drained > 0)
        {
            fluid.shrink(drained);
            onContentsChanged();
        }
        return stack;
    }

    protected void onContentsChanged()
    {

    }

    public void setFluid(FluidStack stack)
    {
        this.fluid = stack;
    }

    public boolean isEmpty()
    {
        return fluid.isEmpty();
    }

    public int getSpace()
    {
        return Math.max(0, capacity - fluid.getAmount());
    }

    public String toString() {
        return "ChargerFluidStorage{" +
                "fluid=" + fluid +
                ", capacity=" + capacity +
                '}';
    }

}

