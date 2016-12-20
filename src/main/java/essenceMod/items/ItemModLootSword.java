package essenceMod.items;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import essenceMod.registry.ModArmory;
import essenceMod.registry.crafting.InfuserRecipes;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;
import essenceMod.tabs.ModTabs;

public class ItemModLootSword extends ItemSword
{
	public final ToolMaterial toolMaterial;
	
	public ItemModLootSword(ToolMaterial material)
	{
		super(material);
		toolMaterial = material;
		setCreativeTab(ModTabs.tabEssence);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < 5; i++)
			list.add(new ItemStack(item, 1, i));
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack item)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (slot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", ToolMaterial.DIAMOND.getDamageVsEntity() + 4.0F, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4, 0));
		}
		return multimap;
	}
	
	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer entityPlayer)
	{
		NBTTagCompound compound = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
		compound.setInteger("Level", item.getMetadata());
		item.setTagCompound(compound);
		item.addEnchantment(ModArmory.shardLooter, 1);
		InfuserRecipes.addUpgrade(item, UpgradeRegistry.ShardSwordLooting.setLevel(item.getMetadata() + 1));
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean b)
	{
		if (!item.hasTagCompound())
			onCreated(item, world, (EntityPlayer) entity);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		for (int i = 0; i < 5; i++)
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, i, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer entityPlayer, List<String> list, boolean bool)
	{
		list.add("Increases shard drop chance to " + ((Upgrade.getUpgradeLevel(item, UpgradeRegistry.ShardSwordLooting) + 1) * 100 / 6) + "%");
		list.add("Multiplies shard drop amount by " + (Upgrade.getUpgradeLevel(item, UpgradeRegistry.ShardSwordLooting) + 1)); 
	}
}
