package essenceMod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.blocks.models.InfuserRenderer;
import essenceMod.blocks.models.PylonRenderer;
import essenceMod.effects.GlowFX;
import essenceMod.entities.tileEntities.TileEntityEssenceInfuser;
import essenceMod.entities.tileEntities.TileEntityEssencePylon;
import essenceMod.registry.ModArmory;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.ModItems;
import essenceMod.utility.Reference;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		
		OBJLoader.INSTANCE.addDomain(Reference.MODID.toLowerCase());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void Init(FMLInitializationEvent event)
	{
		super.Init(event);
		
		InfuserRenderer infuser = new InfuserRenderer();
		PylonRenderer pylon = new PylonRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssenceInfuser.class, infuser);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssencePylon.class, pylon);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.essenceInfuser), 0, new ModelResourceLocation(ModBlocks.essenceInfuser.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.essencePylon), 0, new ModelResourceLocation(ModBlocks.essencePylon.getRegistryName(), "inventory"));
		
		ModItems.initItemRenderers();
		ModBlocks.initItemRenderers();
		ModArmory.initItemRenderers();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}

	@Override
	public void spawnGlow(World world, double x, double y, double z, double red, double green, double blue)
	{
		GlowFX glow = new GlowFX(world, x, y, z, red, green, blue);
		Minecraft.getMinecraft().effectRenderer.addEffect(glow);
	}
	
	@Override
	public void spawnGlow(World world, double x, double y, double z, double red, double green, double blue, int lifetime)
	{
		GlowFX glow = new GlowFX(world, x, y, z, red, green, blue, lifetime);
		Minecraft.getMinecraft().effectRenderer.addEffect(glow);
	}

	@Override
	public void spawnParticle(EnumParticleTypes type, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int... params)
	{
		Minecraft.getMinecraft().theWorld.spawnParticle(type, posX, posY, posZ, motionX, motionY, motionZ, params);
	}
}