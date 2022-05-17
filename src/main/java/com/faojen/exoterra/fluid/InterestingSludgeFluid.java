package com.faojen.exoterra.fluid;

import com.faojen.exoterra.setup.Registration;
import com.faojen.exoterra.setup.ExoTerraItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class InterestingSludgeFluid extends ForgeFlowingFluid {
    public static final ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(Registration.AQUEOUS_STELLAR,
            Registration.FLOWING_AQUEOUS_STELLAR,
            FluidAttributes.builder(new ResourceLocation("exoterra:block/aqueousstellarstill"), new ResourceLocation("exoterra:block/aqueousstellarflow"))
                    .luminosity(6)

                    .rarity(Rarity.RARE))
            .explosionResistance(1f);

    private InterestingSludgeFluid() {
        super(PROPERTIES);
    }

    public static class Source extends InterestingSludgeFluid {
        public Source() {
            super();
        }

        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends InterestingSludgeFluid {
        public Flowing() {
            super();
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }


}
