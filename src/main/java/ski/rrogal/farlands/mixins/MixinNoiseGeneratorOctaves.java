package ski.rrogal.farlands.mixins;

import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorImproved;
import net.minecraft.world.gen.NoiseGenerator;
import java.util.Random;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NoiseGeneratorOctaves.class)
public class MixinNoiseGeneratorOctaves {

	@Shadow
    private NoiseGeneratorImproved[] generatorCollection;
    @Shadow
    private int octaves;

	// i might replace this with something that's a not-overwrite if there's incompats
    @Overwrite
    public double[] generateNoiseOctaves(double[] p_76304_1_, int p_76304_2_, int p_76304_3_, int p_76304_4_, int p_76304_5_, int p_76304_6_, int p_76304_7_, double p_76304_8_, double p_76304_10_, double p_76304_12_) {
        if (p_76304_1_ == null) {
            p_76304_1_ = new double[p_76304_5_ * p_76304_6_ * p_76304_7_];
        } else {
            for (int k1 = 0; k1 < p_76304_1_.length; ++k1) {
                p_76304_1_[k1] = 0.0D;
            }
        }

        double d6 = 1.0D;

        for (int l1 = 0; l1 < this.octaves; ++l1) {
            double d3 = (double)p_76304_2_ * d6 * p_76304_8_;
            double d4 = (double)p_76304_3_ * d6 * p_76304_10_;
            double d5 = (double)p_76304_4_ * d6 * p_76304_12_;
			// remove farlands fix
			//
			//long i2 = MathHelper.floor_double_long(d3);
            //long j2 = MathHelper.floor_double_long(d5);
            //d3 -= (double)i2;
            //d5 -= (double)j2;
			//
            //i2 %= 16777216L;
            //j2 %= 16777216L;
			//
            //d3 += (double)i2;
            //d5 += (double)j2;
			//
            this.generatorCollection[l1].populateNoiseArray(p_76304_1_, d3, d4, d5, p_76304_5_, p_76304_6_, p_76304_7_, p_76304_8_ * d6, p_76304_10_ * d6, p_76304_12_ * d6, d6);
            d6 /= 2.0D;
        }

        return p_76304_1_;
    }
}

