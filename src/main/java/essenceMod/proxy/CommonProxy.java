package essenceMod.proxy;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import essenceMod.handlers.CommandUpgrade;
import essenceMod.handlers.ConfigHandler;
import essenceMod.handlers.EssenceEventHandler;
import essenceMod.handlers.OreDictHandler;
import essenceMod.registry.AttackRegistry;
import essenceMod.registry.ModArmory;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.ModEntities;
import essenceMod.registry.ModItems;
import essenceMod.registry.ModTileEntity;
import essenceMod.registry.crafting.InfuserRecipes;
import essenceMod.registry.crafting.Recipes;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.initProps(event.getSuggestedConfigurationFile());
		ModItems.init();
		ModBlocks.init();
		ModArmory.init();
		ModEntities.init();
		ModTileEntity.init();
		EssenceEventHandler.preinit();
		OreDictHandler.preInit();
	}
	
	public void Init(FMLInitializationEvent event)
	{
		InfuserRecipes.init();
		Recipes.init();
		AttackRegistry.init();
//		if (Loader.isModLoaded("TConstruct") && ConfigHandler.ticoIntegration)
//		{
//			try
//			{
//				TConstructHandler.init();
//			}
//			catch (Exception e){}
//		}	
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		// Do Nothing
	}
	
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandUpgrade());
	}

	public void spawnGlow(World world, double x, double y, double z, double red, double green, double blue)
	{}
	
	public void spawnGlow(World world, double x, double y, double z, double red, double green, double blue, int lifetime)
	{}

	public void spawnParticle(EnumParticleTypes type, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int... params)
	{}
}