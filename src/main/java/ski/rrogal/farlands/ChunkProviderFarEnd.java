package com.kpabr.FarLands;

import java.util.List;
import java.util.Random;

import com.kpabr.FarLands.NoiseGeneratorFarlandsOctaves;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.Event.*;
import net.minecraftforge.event.terraingen.*;

public class ChunkProviderFarEnd extends ChunkProviderEnd implements IChunkProvider
{
    private Random endRNG;
    private NoiseGeneratorFarlandsOctaves noiseGen1;
    private NoiseGeneratorFarlandsOctaves noiseGen2;
    private NoiseGeneratorFarlandsOctaves noiseGen3;
    public NoiseGeneratorFarlandsOctaves noiseGen4;
    public NoiseGeneratorFarlandsOctaves noiseGen5;
    private World endWorld;
    private double[] densities;
    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;
    int[][] field_73203_h = new int[32][32];

    public ChunkProviderFarEnd(World p_i2007_1_, long p_i2007_2_)
    {
    	super(p_i2007_1_, p_i2007_2_);
        this.endWorld = p_i2007_1_;
        this.endRNG = new Random(p_i2007_2_);
        this.noiseGen1 = new NoiseGeneratorFarlandsOctaves(this.endRNG, 16);
        this.noiseGen2 = new NoiseGeneratorFarlandsOctaves(this.endRNG, 16);
        this.noiseGen3 = new NoiseGeneratorFarlandsOctaves(this.endRNG, 8);
        this.noiseGen4 = new NoiseGeneratorFarlandsOctaves(this.endRNG, 10);
        this.noiseGen5 = new NoiseGeneratorFarlandsOctaves(this.endRNG, 16);

        NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5};
        noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2007_1_, this.endRNG, noiseGens);
        this.noiseGen1 = (NoiseGeneratorFarlandsOctaves)noiseGens[0];
        this.noiseGen2 = (NoiseGeneratorFarlandsOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorFarlandsOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorFarlandsOctaves)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorFarlandsOctaves)noiseGens[4];
    }
    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    public double[] initializeNoiseField(double[] p_73187_1_, int p_73187_2_, int p_73187_3_, int p_73187_4_, int p_73187_5_, int p_73187_6_, int p_73187_7_)
    {
    	if(FarLands.threshold > 0)
    	{
	    	if(Math.abs(p_73187_2_) > FarLands.threshold/4)
	    	{
	    		p_73187_2_ = (int)(p_73187_2_+(Math.copySign(((12550820-FarLands.threshold)/4), p_73187_2_)));
	    	}
	    	if(Math.abs(p_73187_4_) > FarLands.threshold/4)
	    	{
	    		p_73187_4_ = (int)(p_73187_4_+(Math.copySign(((12550820-FarLands.threshold)/4), p_73187_4_)));
	    	}
    	}
        if (p_73187_1_ == null)
        {
            p_73187_1_ = new double[p_73187_5_ * p_73187_6_ * p_73187_7_];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;
        this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 1.121D, 1.121D, 0.5D);
        this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 200.0D, 200.0D, 0.5D);
        d0 *= 2.0D;
        this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
        this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < p_73187_5_; ++i2)
        {
            for (int j2 = 0; j2 < p_73187_7_; ++j2)
            {
                double d2 = (this.noiseData4[l1] + 256.0D) / 512.0D;

                if (d2 > 1.0D)
                {
                    d2 = 1.0D;
                }

                double d3 = this.noiseData5[l1] / 8000.0D;

                if (d3 < 0.0D)
                {
                    d3 = -d3 * 0.3D;
                }

                d3 = d3 * 3.0D - 2.0D;
                float f = (float)(i2 + p_73187_2_ - 0) / 1.0F;
                float f1 = (float)(j2 + p_73187_4_ - 0) / 1.0F;
                float f2 = 100.0F - MathHelper.sqrt_float(f * f + f1 * f1) * 8.0F;

                if (f2 > 80.0F)
                {
                    f2 = 80.0F;
                }

                if (f2 < -100.0F)
                {
                    f2 = -100.0F;
                }

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                d3 /= 8.0D;
                d3 = 0.0D;

                if (d2 < 0.0D)
                {
                    d2 = 0.0D;
                }

                d2 += 0.5D;
                d3 = d3 * (double)p_73187_6_ / 16.0D;
                ++l1;
                double d4 = (double)p_73187_6_ / 2.0D;

                for (int k2 = 0; k2 < p_73187_6_; ++k2)
                {
                    double d5 = 0.0D;
                    double d6 = ((double)k2 - d4) * 8.0D / d2;

                    if (d6 < 0.0D)
                    {
                        d6 *= -1.0D;
                    }

                    double d7 = this.noiseData2[k1] / 512.0D;
                    double d8 = this.noiseData3[k1] / 512.0D;
                    double d9 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d9 < 0.0D)
                    {
                        d5 = d7;
                    }
                    else if (d9 > 1.0D)
                    {
                        d5 = d8;
                    }
                    else
                    {
                        d5 = d7 + (d8 - d7) * d9;
                    }

                    d5 -= 8.0D;
                    d5 += (double)f2;
                    byte b0 = 2;
                    double d10;

                    if (k2 > p_73187_6_ / 2 - b0)
                    {
                        d10 = (double)((float)(k2 - (p_73187_6_ / 2 - b0)) / 64.0F);

                        if (d10 < 0.0D)
                        {
                            d10 = 0.0D;
                        }

                        if (d10 > 1.0D)
                        {
                            d10 = 1.0D;
                        }

                        d5 = d5 * (1.0D - d10) + -3000.0D * d10;
                    }

                    b0 = 8;

                    if (k2 < b0)
                    {
                        d10 = (double)((float)(b0 - k2) / ((float)b0 - 1.0F));
                        d5 = d5 * (1.0D - d10) + -30.0D * d10;
                    }

                    p_73187_1_[k1] = d5;
                    ++k1;
                }
            }
        }

        return p_73187_1_;
    }
    

}
