package essenceMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.tabs.ModTabs;

public class ModBlock extends Block
{
	public ModBlock()
	{
		super(Material.IRON);
		setCreativeTab(ModTabs.tabEssence);
		setHardness(5.0F);
		setResistance(10.0F);
		setLightLevel(1.0F);
		setHarvestLevel("pickaxe", 3);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}