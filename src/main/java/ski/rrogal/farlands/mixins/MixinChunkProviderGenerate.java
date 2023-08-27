package ski.rrogal.farlands.mixins;

import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
//import ski.rrogal.farlands.FarLands;
import com.kpabr.FarLands.FarLands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.world.chunk.IChunkProvider;

import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldType;

//tmp

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.Event.*;
import net.minecraftforge.event.terraingen.*;


@Mixin(ChunkProviderGenerate.class)
//public class MixinChunkProviderGenerate implements IChunkProvider{
public class MixinChunkProviderGenerate {

	//this is to override
	/*
	@Override
	public void saveExtraData() {}

	public void recreateStructures(int p_82695_1_, int p_82695_2_)
    {
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
            this.villageGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
        }
    }

	 public int getLoadedChunkCount()
    {
        return 0;
    }

	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
        return p_73155_1_ == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return "Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
    }

	    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }


    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }
	//done
	*/
/*
    @Inject(method = "func_147423_a", at = @At("HEAD"))
    private void injectCustomCode(int p_147423_1_, int p_147423_2_, int p_147423_3_, CallbackInfo ci) {
 	   			//p_147423_1_ = (int)(p_147423_1_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_1_)));
 	   			//p_147423_3_ = (int)(p_147423_3_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_3_)));
	System.out.println("Injecting custom code into ChunkProviderGenerate.func_147423_a GENERATEAAAAAAA" + FarLands.threshold + " " + p_147423_1_ + " " + p_147423_3_);
		if(FarLands.threshold > 0){
 	   		if(Math.abs(p_147423_1_) > FarLands.threshold/4){
 	   			p_147423_1_ = (int)(p_147423_1_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_1_)));
 	   		}
 	   		if(Math.abs(p_147423_3_) > FarLands.threshold/4){
 	   			p_147423_3_ = (int)(p_147423_3_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_3_)));
 	   		}
     	}
	System.out.println("Injecting custom code into ChunkProviderGenerate.func_147423_a GENERATEBBBBBBBBBBB" + FarLands.threshold + " " + p_147423_1_ + " " + p_147423_3_);
    }
*/

/*
@ModifyArg(
    method = "func_147423_a",
    at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/world/gen/ChunkProviderGenerate;func_147423_a(III)V"
    ),
    index = 0
)
private int modifyArgument1(int p_147423_1_) {
	System.out.println("AAAAAA: " + p_147423_1_);
    if (FarLands.threshold > 0 && Math.abs(p_147423_1_) > FarLands.threshold / 4) {
        return (int) (p_147423_1_ + (Math.copySign(((12550820 - FarLands.threshold) / 4), p_147423_1_)));
    }
    return p_147423_1_;
}

@ModifyArg(
    method = "func_147423_a",
    at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/world/gen/ChunkProviderGenerate;func_147423_a(III)V"
    ),
    index = 2
)
private int modifyArgument3(int p_147423_3_) {
	System.out.println("BBBBBBBBBB: " + p_147423_3_);
    if (FarLands.threshold > 0 && Math.abs(p_147423_3_) > FarLands.threshold / 4) {
        return (int) (p_147423_3_ + (Math.copySign(((12550820 - FarLands.threshold) / 4), p_147423_3_)));
    }
    return p_147423_3_;
}
*/

@ModifyArgs(method = "func_147424_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/ChunkProviderGenerate;func_147423_a(III)V"))
    private void thisShouldWork(Args args) {
        if(1 > 0){
            int i = (int)args.get(0);
            if(Math.abs(args.get(0)) > FarLands.threshold/4){
				i = (int)(i + (Math.copySign(((12550820 - FarLands.threshold)/4), i)));
                args.set(0, i);
            }
            i = (int)args.get(2);
            if(Math.abs(args.get(2)) > FarLands.threshold/4){
				i = (int)(i + (Math.copySign(((12550820 - FarLands.threshold)/4), i)));
                args.set(2, i);
            }
        }
    }

/*
    //@Shadow private Random rand;
    //@Shadow private NoiseGeneratorFarlandsOctaves field_147431_j;
    //@Shadow private NoiseGeneratorFarlandsOctaves field_147432_k;
    //@Shadow private NoiseGeneratorFarlandsOctaves field_147429_l;
    @Shadow private NoiseGeneratorPerlin field_147430_m;
    //@Shadow public NoiseGeneratorFarlandsOctaves noiseGen5;
    //@Shadow public NoiseGeneratorFarlandsOctaves noiseGen6;
    //@Shadow public NoiseGeneratorFarlandsOctaves mobSpawnerNoise;
    //@Shadow private World worldObj;
    @Shadow private final boolean mapFeaturesEnabled;
    @Shadow private WorldType field_147435_p;
    @Shadow public double[] field_147434_q;
    @Shadow private final float[] parabolicField;
    @Shadow private double[] stoneNoise = new double[256];
    @Shadow private MapGenBase caveGenerator = new MapGenCaves();
    //@Shadow private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    //@Shadow private MapGenVillage villageGenerator = new MapGenVillage();
    //@Shadow private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    //@Shadow private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    @Shadow private MapGenBase ravineGenerator = new MapGenRavine();
    @Shadow private BiomeGenBase[] biomesForGeneration;
    @Shadow double[] field_147427_d;
    @Shadow double[] field_147428_e;
    @Shadow double[] field_147425_f;
    @Shadow double[] field_147426_g;
    @Shadow int[][] field_73219_j = new int[32][32];
*/

/*
    @Overwrite
	public void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
    {
    	if(FarLands.threshold > 0)
    	{
	    	if(Math.abs(p_147423_1_) > FarLands.threshold/4)
	    	{
	    		p_147423_1_ = (int)(p_147423_1_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_1_)));
	    	}
	    	if(Math.abs(p_147423_3_) > FarLands.threshold/4)
	    	{
	    		p_147423_3_ = (int)(p_147423_3_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_3_)));
	    	}
    	}
        double d0 = 684.412D;
        double d1 = 684.412D;
        double d2 = 512.0D;
        double d3 = 512.0D;
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5D;

        for (int j1 = 0; j1 < 5; ++j1)
        {
            for (int k1 = 0; k1 < 5; ++k1)
            {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.rootHeight;
                        float f4 = biomegenbase1.heightVariation;

                        if (this.field_147435_p == WorldType.AMPLIFIED && f3 > 0.0F)
                        {
                            f3 = 1.0F + f3 * 2.0F;
                            f4 = 1.0F + f4 * 4.0F;
                        }

                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
                        {
                            f5 /= 2.0F;
                        }

                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d12 = this.field_147426_g[i1] / 8000.0D;

                if (d12 < 0.0D)
                {
                    d12 = -d12 * 0.3D;
                }

                d12 = d12 * 3.0D - 2.0D;

                if (d12 < 0.0D)
                {
                    d12 /= 2.0D;

                    if (d12 < -1.0D)
                    {
                        d12 = -1.0D;
                    }

                    d12 /= 1.4D;
                    d12 /= 2.0D;
                }
                else
                {
                    if (d12 > 1.0D)
                    {
                        d12 = 1.0D;
                    }

                    d12 /= 8.0D;
                }

                ++i1;
                double d13 = (double)f1;
                double d14 = (double)f;
                d13 += d12 * 0.2D;
                d13 = d13 * 8.5D / 8.0D;
                double d5 = 8.5D + d13 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

                    if (d6 < 0.0D)
                    {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[l] / 512.0D;
                    double d8 = this.field_147425_f[l] / 512.0D;
                    double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

                    if (j2 > 29)
                    {
                        double d11 = (double)((float)(j2 - 29) / 3.0F);
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }
*/
}
