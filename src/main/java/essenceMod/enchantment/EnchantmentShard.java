package essenceMod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentShard extends Enchantment
{
	public EnchantmentShard(int id, int rarity)
	{
		super(Rarity.COMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{ EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND });
		this.setName("shardLoot");
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