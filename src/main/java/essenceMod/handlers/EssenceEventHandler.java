package essenceMod.handlers;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import baubles.api.BaublesApi;
import essenceMod.items.ItemModArmor;
import essenceMod.items.ItemModSword;
import essenceMod.items.ItemShardContainer;
import essenceMod.items.baubles.ItemAmulet;
import essenceMod.registry.ModArmory;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.ModItems;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;
import essenceMod.utility.Reference;

public class EssenceEventHandler
{
	Random rand = new Random();

	public static void preinit()
	{
		MinecraftForge.EVENT_BUS.register(new EssenceEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new EssenceEventHandler());
		MinecraftForge.ORE_GEN_BUS.register(new EssenceEventHandler());
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onLivingDropsEvent(LivingDropsEvent event)
	{
		if (event.getEntityLiving() instanceof EntityDragon && rand.nextInt(100) < ConfigHandler.dragonShardChance * 100)
		{
			int shardCount = ConfigHandler.dragonShardCount;

			if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
				ItemStack weapon = player.getHeldItem(player.getActiveHand());
				if (weapon != null && EnchantmentHelper.getEnchantmentLevel(ModArmory.shardLooter, weapon) == 1)
				{
					int amuletLevel = 0;
					ItemStack amulet = BaublesApi.getBaubles(player).getStackInSlot(0);
					if (amulet != null && amulet.getItem() instanceof ItemAmulet)
					{
						amuletLevel = Upgrade.getUpgradeLevel(amulet, "AmuletLooting");
					}
					amuletLevel = Math.max(amuletLevel, Upgrade.getUpgradeLevel(weapon, UpgradeRegistry.ShardSwordLooting));
					shardCount *= (1 + amuletLevel);
				}
			}

			while (shardCount > 64)
			{
				event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ModItems.infusedShard, 64)));
				shardCount -= 64;
			}
			if (shardCount != 0)
				event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ModItems.infusedShard, shardCount)));
		}

		else if (event.getEntityLiving() instanceof EntityWither && rand.nextInt(100) < ConfigHandler.witherShardChance * 100)
		{
			int shardCount = ConfigHandler.witherShardCount;

			if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
				ItemStack weapon = player.getHeldItem(player.getActiveHand());
				if (weapon != null && EnchantmentHelper.getEnchantmentLevel(ModArmory.shardLooter, weapon) == 1)
				{
					int amuletLevel = 0;
					ItemStack amulet = BaublesApi.getBaubles(player).getStackInSlot(0);
					if (amulet != null && amulet.getItem() instanceof ItemAmulet)
					{
						amuletLevel = Upgrade.getUpgradeLevel(amulet, "AmuletLooting");
					}
					amuletLevel = Math.max(amuletLevel, Upgrade.getUpgradeLevel(weapon, UpgradeRegistry.ShardSwordLooting));
					shardCount *= (1 + amuletLevel);
				}
			}

			while (shardCount > 64)
			{
				event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ModItems.infusedShard, 64)));
				shardCount -= 64;
			}
			if (shardCount != 0)
				event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ModItems.infusedShard, shardCount)));
		}

		else if (event.getEntityLiving() instanceof EntityMob)
		{
			if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof EntityPlayer)
			{
				if (ConfigHandler.useWhiteList)
				{
					ArrayList<String> whiteListedMobs = new ArrayList<String>();
					for (String string : ConfigHandler.whiteList)
						whiteListedMobs.add(string);
					if (!(whiteListedMobs.contains(event.getEntityLiving().toString())))
						return;
				}
				if (ConfigHandler.useBlackList)
				{
					ArrayList<String> blackListedMobs = new ArrayList<String>();
					for (String string : ConfigHandler.blackList)
						blackListedMobs.add(string);
					if (blackListedMobs.contains(event.getEntityLiving().toString()))
						return;
				}
				EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
				ItemStack weapon = player.getHeldItem(player.getActiveHand());
				if (weapon != null && EnchantmentHelper.getEnchantmentLevel(ModArmory.shardLooter, weapon) == 1)
				{
					int amuletLevel = 0;
					ItemStack amulet = BaublesApi.getBaubles(player).getStackInSlot(0);
					if (amulet != null && amulet.getItem() instanceof ItemAmulet)
					{
						amuletLevel = Upgrade.getUpgradeLevel(amulet, "AmuletLooting");
					}
					amuletLevel = Math.max(amuletLevel, Upgrade.getUpgradeLevel(weapon, UpgradeRegistry.ShardSwordLooting));
					if (rand.nextInt(30) < (5 * (1 + amuletLevel)))
					{
						int amount;
						if (event.getLootingLevel() == 0)
							amount = 1;
						else amount = 1 + rand.nextInt(Math.max(0, event.getLootingLevel()));
						amount *= (1 + amuletLevel);
						event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ModItems.infusedShard, amount)));
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHit(LivingHurtEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			DamageSource source = event.getSource();
			int protValue = 0;
			int resValue = 0;
			int poisonThorns = 0;
			int poisonCount = 0;
			int blindThorns = 0;
			int blindCount = 0;
			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			{
				if (slot.getSlotType() == EntityEquipmentSlot.Type.HAND)
					continue;
				ItemStack armor = player.getItemStackFromSlot(slot);
				if (armor != null && armor.getItem() instanceof ItemModArmor)
				{
					NBTTagCompound compound = armor.getTagCompound();
					compound.setInteger("Absorption Delay", ConfigHandler.absorptionDelay);
					armor.setTagCompound(compound);

					if (source.isFireDamage())
						event.setAmount(event.getAmount() - Upgrade.getUpgradeLevel(armor, UpgradeRegistry.ArmorFireProtection));
					if (source.isMagicDamage())
						event.setAmount(event.getAmount() - Upgrade.getUpgradeLevel(armor, UpgradeRegistry.ArmorMagicProtection));
					if (source.isExplosion())
						event.setAmount(event.getAmount() - Upgrade.getUpgradeLevel(armor, UpgradeRegistry.ArmorBlastProtection));
					if (source.isProjectile())
						event.setAmount(event.getAmount() - Upgrade.getUpgradeLevel(armor, UpgradeRegistry.ArmorProjectileProtection));
					if (source.damageType.equals(DamageSource.wither.damageType))
						event.setAmount(event.getAmount() - Upgrade.getUpgradeLevel(armor, UpgradeRegistry.ArmorWitherProtection));

					protValue += Upgrade.getUpgradeLevel(armor, "ArmorPhysicalProtection");
					resValue += Upgrade.getUpgradeLevel(armor, "ArmorResistance");

					int poisonTemp = Upgrade.getUpgradeLevel(armor, "ArmorMagicThorns");
					if (poisonTemp != 0)
					{
						poisonThorns += poisonTemp;
						poisonCount++;
					}
					int blindTemp = Upgrade.getUpgradeLevel(armor, "ArmorBlindThorns");
					if (blindTemp != 0)
					{
						blindThorns += blindTemp;
						blindCount++;
					}
				}
			}

			double protReduction = protValue * ConfigHandler.maxProtectionValue / 16;
			double resReduction = resValue * 0.025;
			event.setAmount((float) (event.getAmount() * (1 - protReduction) * (1 - resReduction)));
			if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof EntityLivingBase)
			{
				EntityLivingBase enemy = (EntityLivingBase) event.getSource().getEntity();
				if (rand.nextInt(100) < (ConfigHandler.blindThornsChance * blindCount))
				{
					enemy.addPotionEffect(new PotionEffect(Potion.blindness.id, blindThorns * ConfigHandler.blindThornsDuration, 0));
				}
				if (poisonCount != 0)
				{
					enemy.addPotionEffect(new PotionEffect(Potion.poison.id, poisonThorns * ConfigHandler.poisonThornsDuration, poisonCount - 1));
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void infusedTooltip(ItemTooltipEvent event)
	{
		if (event.getItemStack() == null || event.getEntityPlayer() == null || event.getEntityPlayer().worldObj == null)
			return;
		if (event.getItemStack().getItem() instanceof ItemModSword)
		{
			ListIterator<String> iterator = event.getToolTip().listIterator();
			while (iterator.hasNext())
			{
				String next = iterator.next();
				if (next.contains("Attack Damage"))
				{
					iterator.previous();
					float weaponDamage = event.getItemStack().getTagCompound().getFloat("weaponDamage");
					float fireDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponFireDamage);
					float witherDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponWitherDamage);
					float magicDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponMagicDamage);
					float chaosDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponChaosDamage);
					float divineDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponDivineDamage);
					float taintDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponTaintDamage);
					float frostDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponFrostDamage);
					float holyDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponHolyDamage);
					float lightningDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponLightningDamage);
					float windDamage = Upgrade.getUpgradeLevel(event.getItemStack(), UpgradeRegistry.WeaponWindDamage);

					fireDamage *= ConfigHandler.isFireDamagePercent ? weaponDamage * ConfigHandler.fireDamageMulti : ConfigHandler.fireDamageAmount;
					witherDamage *= ConfigHandler.isWitherDamagePercent ? weaponDamage * ConfigHandler.witherDamageMulti : ConfigHandler.witherDamageAmount;
					magicDamage *= ConfigHandler.isMagicDamagePercent ? weaponDamage * ConfigHandler.magicDamageMulti : ConfigHandler.magicDamageAmount;
					chaosDamage *= ConfigHandler.isChaosDamagePercent ? weaponDamage * ConfigHandler.chaosDamageMulti : ConfigHandler.chaosDamageAmount;
					divineDamage *= ConfigHandler.isDivineDamagePercent ? weaponDamage * ConfigHandler.divineDamageMulti : ConfigHandler.divineDamageAmount;
					taintDamage *= ConfigHandler.isTaintDamagePercent ? weaponDamage * ConfigHandler.taintDamageMulti : ConfigHandler.taintDamageAmount;
					frostDamage *= ConfigHandler.isFrostDamagePercent ? weaponDamage * ConfigHandler.frostDamageMulti : ConfigHandler.frostDamageAmount;
					holyDamage *= ConfigHandler.isHolyDamagePercent ? weaponDamage * ConfigHandler.holyDamageMulti : ConfigHandler.holyDamageAmount;
					lightningDamage *= ConfigHandler.isLightningDamagePercent ? weaponDamage * ConfigHandler.lightningDamageMulti : ConfigHandler.lightningDamageAmount;
					windDamage *= ConfigHandler.isWindDamagePercent ? weaponDamage * ConfigHandler.windDamageMulti : ConfigHandler.windDamageAmount;

					double fireText = Math.round(fireDamage * 4) / 4D;
					double witherText = Math.round(witherDamage * 4) / 4D;
					double magicText = Math.round(magicDamage * 4) / 4D;
					double chaosText = Math.round(chaosDamage * 4) / 4D;
					double divineText = Math.round(divineDamage * 4) / 4D;
					double taintText = Math.round(taintDamage * 4) / 4D;
					double frostText = Math.round(frostDamage * 4) / 4D;
					double holyText = Math.round(holyDamage * 4) / 4D;
					double lightningText = Math.round(lightningDamage * 4) / 4D;
					double windText = Math.round(windDamage * 4) / 4D;

					if (fireText != 0)
					{
						if (fireText == (int) fireText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) fireText) + " Fire Damage");
						else iterator.add(TextFormatting.BLUE + "+" + fireText + " Fire Damage");
					}
					if (witherText != 0)
					{
						if (witherText == (int) witherText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) witherText) + " Wither Damage");
						else iterator.add(TextFormatting.BLUE + "+" + witherText + " Wither Damage");
					}
					if (magicText != 0)
					{
						if (magicText == (int) magicText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) magicText) + " Magic Damage");
						else iterator.add(TextFormatting.BLUE + "+" + magicText + " Magic Damage");
					}
					if (chaosText != 0)
					{
						if (chaosText == (int) chaosText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) chaosText) + " Chaos Damage");
						else iterator.add(TextFormatting.BLUE + "+" + chaosText + " Chaos Damage");
					}
					if (divineText != 0)
					{
						if (divineText == (int) divineText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) divineText) + " Divine Damage");
						else iterator.add(TextFormatting.BLUE + "+" + divineText + " Divine Damage");
					}
					if (taintText != 0)
					{
						if (taintText == (int) taintText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) taintText) + " Flux Damage");
						else iterator.add(TextFormatting.BLUE + "+" + taintText + " Flux Damage");
					}
					if (frostText != 0)
					{
						if (frostText == (int) frostText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) frostText) + " Frost Damage");
						else iterator.add(TextFormatting.BLUE + "+" + frostText + " Frost Damage");
					}
					if (holyText != 0)
					{
						if (holyText == (int) holyText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) holyText) + " Holy Damage");
						else iterator.add(TextFormatting.BLUE + "+" + holyText + " Holy Damage");
					}
					if (lightningText != 0)
					{
						if (lightningText == (int) lightningText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) lightningText) + " Lightning Damage");
						else iterator.add(TextFormatting.BLUE + "+" + lightningText + " Lightning Damage");
					}
					if (windText != 0)
					{
						if (windText == (int) windText)
							iterator.add(TextFormatting.BLUE + "+" + ((int) windText) + " Wind Damage");
						else iterator.add(TextFormatting.BLUE + "+" + windText + " Wind Damage");
					}
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onShardPickup(EntityItemPickupEvent event)
	{
		ItemStack item = event.getItem().getEntityItem();
		ArrayList<ItemStack> shardContainers = new ArrayList<ItemStack>();
		if (event.getEntityPlayer().inventory.hasItemStack(new ItemStack(ModItems.shardContainer)))
		{
			for (ItemStack stack : event.getEntityPlayer().inventory.mainInventory)
			{
				if (stack == null || stack.stackSize == 0)
					continue;
				if (stack.getItem().getUnlocalizedName().equals(ModItems.shardContainer.getUnlocalizedName()))
					shardContainers.add(stack);
			}
		}
		if (item.isItemEqual(new ItemStack(ModItems.infusedShard)))
		{
			while (item.stackSize > 0 && shardContainers.size() > 0)
			{
				item.stackSize = ItemShardContainer.addShards(shardContainers.get(0), item.stackSize);
				if (item.stackSize > 0)
					shardContainers.remove(0);
			}
		}
		if (item.isItemEqual(new ItemStack(ModBlocks.shardBlock)))
		{
			int shards = item.stackSize * 9;
			while (shards > 0 && shardContainers.size() > 0)
			{
				shards = ItemShardContainer.addShards(shardContainers.get(0), shards);
				if (shards > 0)
					shardContainers.remove(0);
			}
			item.stackSize = shards / 9;
			event.getItem().worldObj.spawnEntityInWorld(new EntityItem(event.getItem().worldObj, event.getItem().posX, event.getItem().posY, event.getItem().posZ, new ItemStack(ModItems.infusedShard, shards % 9)));
		}
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event)
	{
		for (String str : Reference.SPRITES)
		{
			ResourceLocation sprite = new ResourceLocation(Reference.MODID + ":" + str);
			event.getMap().registerSprite(sprite);
		}
	}
}