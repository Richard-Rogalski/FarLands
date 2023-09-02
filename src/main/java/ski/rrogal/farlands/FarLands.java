package com.kpabr.FarLands;
/*
 * Created by Valiec2019
 * on July 25, 2015
 * currently using Minecraft Forge 10.13.4.1448
 */

import java.io.IOException;
import java.net.UnknownHostException;

import com.kpabr.FarLands.CommonProxy;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
@Mod(modid = FarLands.MODID, version = FarLands.VERSION, name = FarLands.NAME)
public class FarLands
{
	
    @SidedProxy(clientSide="com.kpabr.FarLands.client.ClientProxy", serverSide="com.kpabr.FarLands.CommonProxy")
    public static CommonProxy proxy;
	
 
    /*Mod ID and Version declarations*/
    public static final String MODID = "farlands";
    public static final String VERSION = "2.0.0";
    public static final String NAME = "farlands";
    
    public static FarLands instance;
    
    public static int threshold;
    public static int thresholdNether;
    public static int thresholdEnd;

	public static String directions;
	public static boolean genNorth;
	public static boolean genEast;
	public static boolean genWest;
	public static boolean genSouth;
    
    public static Configuration config;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile()); //gets default config file
    	
    	FarLands.config.load();
        this.threshold = FarLands.config.getInt("FarLandsStart", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance. Might not change the start distance for some generators added by mods. (set to -1 for default distance)");
        this.thresholdNether = FarLands.config.getInt("FarLandsStartNether", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance in the nether. If you want the farlands in the nether to line up with farlands in the overworld, set this to an eighth of the value. (set to -1 for default distance)");
        this.thresholdEnd = FarLands.config.getInt("FarLandsStartEnd", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance in the end. (set to -1 for default distance)");
        this.directions = FarLands.config.getString("generateInDirection", Configuration.CATEGORY_GENERAL, "NEWS", "Directions that farlands will generate. Examples: \"NEWS\" for all directions. \"NW\" to spawn them north and to the west. \"E\" to spawn them to the east.");
        FarLands.config.save(); //TODO: make farlands able to start after the default

		this.genNorth = false; this.genEast = false; this.genWest = false; this.genSouth = false;
		
		if(this.directions.contains("N"))
			this.genNorth = true;
		if(this.directions.contains("E"))
            this.genEast = true;
		if(this.directions.contains("W"))
            this.genWest = true;
		if(this.directions.contains("S"))
            this.genSouth = true;
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        this.instance = this;
        
        FMLCommonHandler.instance().bus().register(this);
        //MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.TERRAIN_GEN_BUS.register(this);
        
     	//proxy.registerRenderers();
    }

	/*
    @SubscribeEvent
    public void onGen(ChunkProviderEvent.InitNoiseField event)
    {
    	//uses static 1 and -1 for dimensions, won't support multiworlds / multiverse, should fix
        if(event.chunkProvider instanceof ChunkProviderEnd)// && Minecraft.getMinecraft().theWorld.provider.terrainType instanceof FarWorldType)
        {
            //ChunkProviderFarEnd end = (ChunkProviderFarEnd)(new ChunkProviderFarEnd(DimensionManager.getWorld(1), DimensionManager.getWorld(1).getSeed()));
            //event.setResult(Result.DENY);

            //event.noisefield = end.initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
        }
        else if(event.chunkProvider instanceof ChunkProviderHell)// && Minecraft.getMinecraft().theWorld.provider.terrainType instanceof FarWorldType)
        {
            //ChunkProviderFarNether nether = (ChunkProviderFarNether)(new ChunkProviderFarNether(DimensionManager.getWorld(-1), DimensionManager.getWorld(-1).getSeed()));
            //event.setResult(Result.DENY);

            //event.noisefield = nether.initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
        }  
		// should probably just check if it's dimension 0 for now
		//else if(event.chunkProvider instanceof ChunkProviderGenerate){
            //event.noisefield = ((ChunkProviderGenerate)DimensionManager.getProvider(0).createChunkGenerator()).initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
		//}
    }
	*/
}

