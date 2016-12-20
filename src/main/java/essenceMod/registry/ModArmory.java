package essenceMod.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.enchantment.EnchantmentShard;
import essenceMod.items.ItemModArmor;
import essenceMod.items.ItemModBow;
import essenceMod.items.ItemModLootSword;
import essenceMod.items.ItemModSword;
import essenceMod.items.baubles.ItemAmulet;
import essenceMod.items.baubles.ItemBelt;
import essenceMod.items.baubles.ItemRing;
import essenceMod.utility.RegisterHelper;

public class ModArmory
{
	public static final Enchantment shardLooter = new EnchantmentShard();
	
	public static ToolMaterial INFUSED = EnumHelper.addToolMaterial("INFUSED", 6, 1500, 10.0F, 6.0F, 20);
	public static ArmorMaterial AINFUSED = EnumHelper.addArmorMaterial("INFUSED", "infused", 1500, new int[] { 4, 7, 8, 5 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);

	public static Item infusedSword = new ItemModSword(INFUSED).setUnlocalizedName("infusedSword");
	
	public static Item lootSword = new ItemModLootSword(INFUSED).setUnlocalizedName("lootSword");

	public static Item infusedBow = new ItemModBow().setUnlocalizedName("infusedBow");

	public static Item infusedHelm = new ItemModArmor(AINFUSED, EntityEquipmentSlot.HEAD).setUnlocalizedName("infusedHelm");
	public static Item infusedPlate = new ItemModArmor(AINFUSED, EntityEquipmentSlot.CHEST).setUnlocalizedName("infusedPlate");
	public static Item infusedPants = new ItemModArmor(AINFUSED, EntityEquipmentSlot.LEGS).setUnlocalizedName("infusedPants");
	public static Item infusedBoots = new ItemModArmor(AINFUSED, EntityEquipmentSlot.FEET).setUnlocalizedName("infusedBoots");

	public static Item infusedRing = new ItemRing().setUnlocalizedName("infusedRing");
	public static Item infusedBelt = new ItemBelt().setUnlocalizedName("infusedBelt");
	public static Item infusedAmulet = new ItemAmulet().setUnlocalizedName("infusedAmulet");

	public static Item infusedPauldrons;
	public static Item infusedVambraces;

	public static void init()
	{
		RegisterHelper.registerItem(infusedSword);
		
		RegisterHelper.registerItem(lootSword);

		RegisterHelper.registerItem(infusedBow);

		RegisterHelper.registerItem(infusedHelm);
		RegisterHelper.registerItem(infusedPlate);
		RegisterHelper.registerItem(infusedPants);
		RegisterHelper.registerItem(infusedBoots);

		RegisterHelper.registerItem(infusedAmulet);
		RegisterHelper.registerItem(infusedBelt);
		RegisterHelper.registerItem(infusedRing);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initItemRenderers()
	{
		((ItemModSword) infusedSword).initModel();
		
		((ItemModLootSword) lootSword).initModel();
		
		((ItemModBow) infusedBow).initModel();
		
		((ItemModArmor) infusedHelm).initModel();
		((ItemModArmor) infusedPlate).initModel();
		((ItemModArmor) infusedPants).initModel();
		((ItemModArmor) infusedBoots).initModel();
		
		((ItemRing) infusedRing).initModel();
		((ItemBelt) infusedBelt).initModel();
		((ItemAmulet) infusedAmulet).initModel();
	}
}