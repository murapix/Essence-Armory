package essenceMod.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.items.ItemMod;
import essenceMod.items.ItemShardContainer;
import essenceMod.utility.RegisterHelper;

public class ModItems
{
	public static Item infusedShard = new ItemMod().setUnlocalizedName("shardInfused");
	public static Item infusedDiamond = new ItemMod().setUnlocalizedName("gemInfused");
	public static Item infusedStar = new ItemMod().setUnlocalizedName("starInfused");
	public static Item crystalDiamond = new ItemMod().setUnlocalizedName("gemCrystallized");
	public static Item crystalStar = new ItemMod().setUnlocalizedName("starCrystallized");
	public static Item platedDiamond = new ItemMod().setUnlocalizedName("gemPlated");
	public static Item platedStar = new ItemMod().setUnlocalizedName("starPlated");
	
	public static Item infusedNugget = new ItemMod().setUnlocalizedName("nuggetInfused");
	public static Item infusedIngot = new ItemMod().setUnlocalizedName("ingotInfused");
	public static Item infusedRod = new ItemMod().setUnlocalizedName("stickInfused");
	public static Item infusedString = new ItemMod().setUnlocalizedName("stringInfused");
	
	public static Item infusedWand = new ItemMod().setUnlocalizedName("wandInfused");
	
	public static Item shardContainer = new ItemShardContainer().setUnlocalizedName("shardContainer");
	
	public static void init()
	{
		RegisterHelper.registerItem(infusedShard);
		RegisterHelper.registerItem(infusedDiamond);
		RegisterHelper.registerItem(infusedStar);
		RegisterHelper.registerItem(crystalDiamond);
		RegisterHelper.registerItem(crystalStar);
		RegisterHelper.registerItem(platedDiamond);
		RegisterHelper.registerItem(platedStar);
		
		RegisterHelper.registerItem(infusedNugget);
		RegisterHelper.registerItem(infusedIngot);
		RegisterHelper.registerItem(infusedRod);
		RegisterHelper.registerItem(infusedString);
		
		RegisterHelper.registerItem(infusedWand);
		
		RegisterHelper.registerItem(shardContainer);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initItemRenderers()
	{
		((ItemMod) infusedShard).initModel();
		((ItemMod) infusedDiamond).initModel();
		((ItemMod) infusedStar).initModel();
		((ItemMod) crystalDiamond).initModel();
		((ItemMod) crystalStar).initModel();
		((ItemMod) platedDiamond).initModel();
		((ItemMod) platedStar).initModel();
		
		((ItemMod) infusedNugget).initModel();
		((ItemMod) infusedIngot).initModel();
		((ItemMod) infusedRod).initModel();
		((ItemMod) infusedString).initModel();
		
		((ItemMod) infusedWand).initModel();
		
		((ItemShardContainer) shardContainer).initModel();
	}
}
