package essenceMod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import essenceMod.handlers.ConfigHandler;
import essenceMod.utility.Reference;

public class EnchantmentShard extends Enchantment
{
	public EnchantmentShard()
	{
		super(Rarity.COMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{ EntityEquipmentSlot.MAINHAND });
		this.setName("shardLoot");
		Enchantment.REGISTRY.register(ConfigHandler.shardEnchantID, new ResourceLocation(Reference.MODID + ".enchantment_shard"), this);
	}
	
	public int getMinEnchantability(int level)
	{
		return 1;
	}
	
	public int getMaxEnchantability(int level)
	{
		return Integer.MAX_VALUE;
	}
}