package essenceMod.registry.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.Loader;
import essenceMod.items.ItemShardContainer;
import essenceMod.registry.ModArmory;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.ModItems;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRecipe;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;

public class InfuserRecipes
{
	public static HashMap<Class<? extends Item>, ArrayList<UpgradeRecipe>> upgradeRecipes;

	public static ArrayList<ItemRecipe> itemRecipes;

	public static void init()
	{
		UpgradeRegistry.init();

		upgradeRecipes = new HashMap<>();
		itemRecipes = new ArrayList<ItemRecipe>();

		swordRecipes();
		bowRecipes();
		armorRecipes();
		amuletRecipes();
		ringRecipes();
		beltRecipes();
		regeantRecipes();
	}

	private static void swordRecipes()
	{
		ItemStack sword = new ItemStack(ModArmory.infusedSword);

		addRecipe(sword, UpgradeRegistry.WeaponFireDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.COAL, ModItems.infusedDiamond, Items.REDSTONE);// Coal
		addRecipe(sword, UpgradeRegistry.WeaponFireDoT.setLevel(2), UpgradeRegistry.WeaponFireDoT.setLevel(1), Items.COAL, ModItems.infusedDiamond, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponFireDoT.setLevel(3), UpgradeRegistry.WeaponFireDoT.setLevel(2), Items.COAL, ModItems.crystalDiamond, Items.REDSTONE);
		addRecipe(sword, UpgradeRegistry.WeaponFireDoT.setLevel(4), UpgradeRegistry.WeaponFireDoT.setLevel(3), Items.COAL, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponFireDoT.setLevel(5), UpgradeRegistry.WeaponFireDoT.setLevel(4), Items.COAL, ModItems.platedDiamond, Items.REDSTONE);

		addRecipe(sword, UpgradeRegistry.WeaponMagicDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SPIDER_EYE, ModItems.infusedDiamond, Items.REDSTONE);// Spider Eye
		addRecipe(sword, UpgradeRegistry.WeaponMagicDoT.setLevel(2), UpgradeRegistry.WeaponMagicDoT.setLevel(1), Items.SPIDER_EYE, ModItems.infusedDiamond, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDoT.setLevel(3), UpgradeRegistry.WeaponMagicDoT.setLevel(2), Items.SPIDER_EYE, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDoT.setLevel(4), UpgradeRegistry.WeaponMagicDoT.setLevel(3), Items.SPIDER_EYE, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDoT.setLevel(5), UpgradeRegistry.WeaponMagicDoT.setLevel(4), Items.SPIDER_EYE, ModItems.platedDiamond, Items.GLOWSTONE_DUST);

		addRecipe(sword, UpgradeRegistry.WeaponWitherDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.SOUL_SAND, ModItems.infusedStar, Items.REDSTONE);// Soul Sand
		addRecipe(sword, UpgradeRegistry.WeaponWitherDoT.setLevel(2), UpgradeRegistry.WeaponWitherDoT.setLevel(1), Blocks.SOUL_SAND, ModItems.infusedStar, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDoT.setLevel(3), UpgradeRegistry.WeaponWitherDoT.setLevel(2), Blocks.SOUL_SAND, ModItems.crystalStar, Items.REDSTONE);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDoT.setLevel(4), UpgradeRegistry.WeaponWitherDoT.setLevel(3), Blocks.SOUL_SAND, ModItems.crystalStar, Items.GLOWSTONE_DUST);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDoT.setLevel(5), UpgradeRegistry.WeaponWitherDoT.setLevel(4), Blocks.SOUL_SAND, ModItems.platedStar, Items.REDSTONE);

		addRecipe(sword, UpgradeRegistry.WeaponArmorPiercing.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, ModItems.infusedStar, ModItems.infusedStar);// Ender Pearl
		addRecipe(sword, UpgradeRegistry.WeaponArmorPiercing.setLevel(2), UpgradeRegistry.WeaponArmorPiercing.setLevel(1), Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_EYE, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(sword, UpgradeRegistry.WeaponArmorPiercing.setLevel(3), UpgradeRegistry.WeaponArmorPiercing.setLevel(2), Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(sword, UpgradeRegistry.WeaponArmorPiercing.setLevel(4), UpgradeRegistry.WeaponArmorPiercing.setLevel(3), Items.ENDER_PEARL, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(sword, UpgradeRegistry.WeaponArmorPiercing.setLevel(5), UpgradeRegistry.WeaponArmorPiercing.setLevel(4), Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.platedStar, ModItems.platedStar);

		addRecipe(sword, UpgradeRegistry.SwordLifesteal.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.GHAST_TEAR, Items.GHAST_TEAR, Items.BEEF, Items.BEEF, ModItems.infusedStar, ModItems.infusedStar); // Ghast Tears + Raw Meat
		addRecipe(sword, UpgradeRegistry.SwordLifesteal.setLevel(2), UpgradeRegistry.SwordLifesteal.setLevel(1), Items.GHAST_TEAR, Items.GHAST_TEAR, Items.COOKED_BEEF, Items.COOKED_BEEF, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(sword, UpgradeRegistry.SwordLifesteal.setLevel(3), UpgradeRegistry.SwordLifesteal.setLevel(2), Items.GHAST_TEAR, Items.GHAST_TEAR, Items.COOKED_BEEF, Items.COOKED_BEEF, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(sword, UpgradeRegistry.SwordLifesteal.setLevel(4), UpgradeRegistry.SwordLifesteal.setLevel(3), Items.GHAST_TEAR, Items.GHAST_TEAR, Items.COOKED_BEEF, Items.COOKED_BEEF, ModItems.platedStar, ModItems.platedStar);
		addRecipe(sword, UpgradeRegistry.SwordLifesteal.setLevel(5), UpgradeRegistry.SwordLifesteal.setLevel(4), Items.GHAST_TEAR, Items.GHAST_TEAR, Items.COOKED_BEEF, Items.COOKED_BEEF, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		addRecipe(sword, UpgradeRegistry.WeaponKnockback.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SLIME_BALL, Items.SLIME_BALL, ModItems.infusedDiamond, ModItems.infusedDiamond);// Slime Ball
		addRecipe(sword, UpgradeRegistry.WeaponKnockback.setLevel(2), UpgradeRegistry.WeaponKnockback.setLevel(1), Blocks.PISTON, Blocks.PISTON, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponKnockback.setLevel(3), UpgradeRegistry.WeaponKnockback.setLevel(2), Blocks.PISTON, Blocks.PISTON, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponKnockback.setLevel(4), UpgradeRegistry.WeaponKnockback.setLevel(3), Blocks.STICKY_PISTON, Blocks.STICKY_PISTON, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponKnockback.setLevel(5), UpgradeRegistry.WeaponKnockback.setLevel(4), Blocks.STICKY_PISTON, Blocks.STICKY_PISTON, ModItems.platedDiamond, ModItems.platedDiamond);

		ItemStack inkSac = new ItemStack(Items.DYE, 1, 0);
		addRecipe(sword, UpgradeRegistry.WeaponBlind.setLevel(1), UpgradeRegistry.BaseUpgrade, inkSac, inkSac, inkSac, inkSac, ModItems.infusedStar, ModItems.infusedStar); // Soul Sand + Ink Sac
		addRecipe(sword, UpgradeRegistry.WeaponBlind.setLevel(2), UpgradeRegistry.WeaponBlind.setLevel(1), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(sword, UpgradeRegistry.WeaponBlind.setLevel(3), UpgradeRegistry.WeaponBlind.setLevel(2), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(sword, UpgradeRegistry.WeaponBlind.setLevel(4), UpgradeRegistry.WeaponBlind.setLevel(3), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedStar, ModItems.platedStar);
		addRecipe(sword, UpgradeRegistry.WeaponBlind.setLevel(5), UpgradeRegistry.WeaponBlind.setLevel(4), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		addRecipe(sword, UpgradeRegistry.WeaponSlow.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedDiamond, ModItems.infusedDiamond); // CobWEB
		addRecipe(sword, UpgradeRegistry.WeaponSlow.setLevel(2), UpgradeRegistry.WeaponSlow.setLevel(1), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponSlow.setLevel(3), UpgradeRegistry.WeaponSlow.setLevel(2), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(sword, UpgradeRegistry.WeaponSlow.setLevel(4), UpgradeRegistry.WeaponSlow.setLevel(3), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponSlow.setLevel(5), UpgradeRegistry.WeaponSlow.setLevel(4), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(sword, UpgradeRegistry.WeaponPhysicalDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.infusedDiamond, ModItems.infusedDiamond);// Quartz
		addRecipe(sword, UpgradeRegistry.WeaponPhysicalDamage.setLevel(2), UpgradeRegistry.WeaponPhysicalDamage.setLevel(1), Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponPhysicalDamage.setLevel(3), UpgradeRegistry.WeaponPhysicalDamage.setLevel(2), Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(sword, UpgradeRegistry.WeaponPhysicalDamage.setLevel(4), UpgradeRegistry.WeaponPhysicalDamage.setLevel(3), Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponPhysicalDamage.setLevel(5), UpgradeRegistry.WeaponPhysicalDamage.setLevel(4), Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		ItemStack witherSkull = new ItemStack(Items.SKULL, 1, 1);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, witherSkull, witherSkull, witherSkull, ModItems.infusedStar, ModItems.infusedStar, ModItems.infusedStar);// Wither Skeleton Skull
		addRecipe(sword, UpgradeRegistry.WeaponWitherDamage.setLevel(2), UpgradeRegistry.WeaponWitherDamage.setLevel(1), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDamage.setLevel(3), UpgradeRegistry.WeaponWitherDamage.setLevel(2), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDamage.setLevel(4), UpgradeRegistry.WeaponWitherDamage.setLevel(3), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedStar, ModItems.platedStar);
		addRecipe(sword, UpgradeRegistry.WeaponWitherDamage.setLevel(5), UpgradeRegistry.WeaponWitherDamage.setLevel(4), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		ItemStack potionHarming = new ItemStack(Items.POTIONITEM, 1, 8204);
		ItemStack potionHarmingII = new ItemStack(Items.POTIONITEM, 1, 8236);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, potionHarming, potionHarming, potionHarming, ModItems.infusedDiamond, ModItems.infusedDiamond, ModItems.infusedDiamond);// Potion of Harming
		addRecipe(sword, UpgradeRegistry.WeaponMagicDamage.setLevel(2), UpgradeRegistry.WeaponMagicDamage.setLevel(1), potionHarming, potionHarming, potionHarming, ModItems.crystalDiamond, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDamage.setLevel(3), UpgradeRegistry.WeaponMagicDamage.setLevel(2), potionHarming, potionHarming, potionHarming, ModItems.infusedIngot, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDamage.setLevel(4), UpgradeRegistry.WeaponMagicDamage.setLevel(3), potionHarmingII, potionHarmingII, potionHarmingII, ModItems.platedDiamond, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponMagicDamage.setLevel(5), UpgradeRegistry.WeaponMagicDamage.setLevel(4), potionHarmingII, potionHarmingII, potionHarmingII, ModBlocks.infusedBlock, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(sword, UpgradeRegistry.WeaponFireDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.infusedDiamond, ModItems.infusedDiamond, ModItems.infusedDiamond);// Blaze Rod
		addRecipe(sword, UpgradeRegistry.WeaponFireDamage.setLevel(2), UpgradeRegistry.WeaponFireDamage.setLevel(1), Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.crystalDiamond, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponFireDamage.setLevel(3), UpgradeRegistry.WeaponFireDamage.setLevel(2), Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.infusedIngot, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(sword, UpgradeRegistry.WeaponFireDamage.setLevel(4), UpgradeRegistry.WeaponFireDamage.setLevel(3), Items.FIRE_CHARGE, Items.FIRE_CHARGE, Items.FIRE_CHARGE, ModItems.platedDiamond, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(sword, UpgradeRegistry.WeaponFireDamage.setLevel(5), UpgradeRegistry.WeaponFireDamage.setLevel(4), Items.FIRE_CHARGE, Items.FIRE_CHARGE, Items.FIRE_CHARGE, ModBlocks.infusedBlock, ModBlocks.infusedBlock, ModBlocks.infusedBlock);
	}

	private static void bowRecipes()
	{
		ItemStack bow = new ItemStack(ModArmory.infusedBow);

		addRecipe(bow, UpgradeRegistry.WeaponFireDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.COAL, ModItems.infusedDiamond, Items.REDSTONE);// Coal
		addRecipe(bow, UpgradeRegistry.WeaponFireDoT.setLevel(2), UpgradeRegistry.WeaponFireDoT.setLevel(1), Items.COAL, ModItems.infusedDiamond, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponFireDoT.setLevel(3), UpgradeRegistry.WeaponFireDoT.setLevel(2), Items.COAL, ModItems.crystalDiamond, Items.REDSTONE);
		addRecipe(bow, UpgradeRegistry.WeaponFireDoT.setLevel(4), UpgradeRegistry.WeaponFireDoT.setLevel(3), Items.COAL, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponFireDoT.setLevel(5), UpgradeRegistry.WeaponFireDoT.setLevel(4), Items.COAL, ModItems.platedDiamond, Items.REDSTONE);

		addRecipe(bow, UpgradeRegistry.WeaponMagicDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SPIDER_EYE, ModItems.infusedDiamond, Items.REDSTONE);// Spider Eye
		addRecipe(bow, UpgradeRegistry.WeaponMagicDoT.setLevel(2), UpgradeRegistry.WeaponMagicDoT.setLevel(1), Items.SPIDER_EYE, ModItems.infusedDiamond, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDoT.setLevel(3), UpgradeRegistry.WeaponMagicDoT.setLevel(2), Items.SPIDER_EYE, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDoT.setLevel(4), UpgradeRegistry.WeaponMagicDoT.setLevel(3), Items.SPIDER_EYE, ModItems.crystalDiamond, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDoT.setLevel(5), UpgradeRegistry.WeaponMagicDoT.setLevel(4), Items.SPIDER_EYE, ModItems.platedDiamond, Items.GLOWSTONE_DUST);

		addRecipe(bow, UpgradeRegistry.WeaponWitherDoT.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.SOUL_SAND, ModItems.infusedStar, Items.REDSTONE);// Soul Sand
		addRecipe(bow, UpgradeRegistry.WeaponWitherDoT.setLevel(2), UpgradeRegistry.WeaponWitherDoT.setLevel(1), Blocks.SOUL_SAND, ModItems.infusedStar, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDoT.setLevel(3), UpgradeRegistry.WeaponWitherDoT.setLevel(2), Blocks.SOUL_SAND, ModItems.crystalStar, Items.REDSTONE);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDoT.setLevel(4), UpgradeRegistry.WeaponWitherDoT.setLevel(3), Blocks.SOUL_SAND, ModItems.crystalStar, Items.GLOWSTONE_DUST);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDoT.setLevel(5), UpgradeRegistry.WeaponWitherDoT.setLevel(4), Blocks.SOUL_SAND, ModItems.platedStar, Items.REDSTONE);

		addRecipe(bow, UpgradeRegistry.WeaponArmorPiercing.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, ModItems.infusedStar, ModItems.infusedStar);// Ender Pearl
		addRecipe(bow, UpgradeRegistry.WeaponArmorPiercing.setLevel(2), UpgradeRegistry.WeaponArmorPiercing.setLevel(1), Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_EYE, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(bow, UpgradeRegistry.WeaponArmorPiercing.setLevel(3), UpgradeRegistry.WeaponArmorPiercing.setLevel(2), Items.ENDER_PEARL, Items.ENDER_PEARL, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(bow, UpgradeRegistry.WeaponArmorPiercing.setLevel(4), UpgradeRegistry.WeaponArmorPiercing.setLevel(3), Items.ENDER_PEARL, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(bow, UpgradeRegistry.WeaponArmorPiercing.setLevel(5), UpgradeRegistry.WeaponArmorPiercing.setLevel(4), Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, Items.ENDER_EYE, ModItems.platedStar, ModItems.platedStar);

		addRecipe(bow, UpgradeRegistry.BowArrowSpeed.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SLIME_BALL, Items.SLIME_BALL, Items.FEATHER, Items.FEATHER, ModItems.infusedDiamond, ModItems.infusedDiamond); // Slime + Feathers
		addRecipe(bow, UpgradeRegistry.BowArrowSpeed.setLevel(2), UpgradeRegistry.BowArrowSpeed.setLevel(1), Items.SLIME_BALL, Items.SLIME_BALL, Items.FEATHER, Items.FEATHER, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.BowArrowSpeed.setLevel(3), UpgradeRegistry.BowArrowSpeed.setLevel(2), Items.SLIME_BALL, Items.SLIME_BALL, Items.FEATHER, Items.FEATHER, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.BowArrowSpeed.setLevel(4), UpgradeRegistry.BowArrowSpeed.setLevel(3), Items.SLIME_BALL, Items.SLIME_BALL, Items.FEATHER, Items.FEATHER, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.BowArrowSpeed.setLevel(5), UpgradeRegistry.BowArrowSpeed.setLevel(4), Items.SLIME_BALL, Items.SLIME_BALL, Items.FEATHER, Items.FEATHER, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(bow, UpgradeRegistry.BowDrawSpeed.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SUGAR, Items.SUGAR, Items.STRING, Items.STRING, ModItems.infusedDiamond, ModItems.infusedDiamond); // Sugar + String
		addRecipe(bow, UpgradeRegistry.BowDrawSpeed.setLevel(2), UpgradeRegistry.BowDrawSpeed.setLevel(1), Items.SUGAR, Items.SUGAR, Items.STRING, Items.STRING, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.BowDrawSpeed.setLevel(3), UpgradeRegistry.BowDrawSpeed.setLevel(2), Items.SUGAR, Items.SUGAR, Items.STRING, Items.STRING, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.BowDrawSpeed.setLevel(4), UpgradeRegistry.BowDrawSpeed.setLevel(3), Items.SUGAR, Items.SUGAR, Items.STRING, Items.STRING, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.BowDrawSpeed.setLevel(5), UpgradeRegistry.BowDrawSpeed.setLevel(4), Items.SUGAR, Items.SUGAR, Items.STRING, Items.STRING, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(bow, UpgradeRegistry.WeaponKnockback.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SLIME_BALL, Items.SLIME_BALL, ModItems.infusedDiamond, ModItems.infusedDiamond);// Slime Ball
		addRecipe(bow, UpgradeRegistry.WeaponKnockback.setLevel(2), UpgradeRegistry.WeaponKnockback.setLevel(1), Blocks.PISTON, Blocks.PISTON, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponKnockback.setLevel(3), UpgradeRegistry.WeaponKnockback.setLevel(2), Blocks.PISTON, Blocks.PISTON, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponKnockback.setLevel(4), UpgradeRegistry.WeaponKnockback.setLevel(3), Blocks.STICKY_PISTON, Blocks.STICKY_PISTON, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponKnockback.setLevel(5), UpgradeRegistry.WeaponKnockback.setLevel(4), Blocks.STICKY_PISTON, Blocks.STICKY_PISTON, ModItems.platedDiamond, ModItems.platedDiamond);

		ItemStack inkSac = new ItemStack(Items.DYE, 1, 0);
		addRecipe(bow, UpgradeRegistry.WeaponBlind.setLevel(1), UpgradeRegistry.BaseUpgrade, inkSac, inkSac, inkSac, inkSac, ModItems.infusedStar, ModItems.infusedStar); // Soul Sand + Ink Sac
		addRecipe(bow, UpgradeRegistry.WeaponBlind.setLevel(2), UpgradeRegistry.WeaponBlind.setLevel(1), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(bow, UpgradeRegistry.WeaponBlind.setLevel(3), UpgradeRegistry.WeaponBlind.setLevel(2), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(bow, UpgradeRegistry.WeaponBlind.setLevel(4), UpgradeRegistry.WeaponBlind.setLevel(3), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedStar, ModItems.platedStar);
		addRecipe(bow, UpgradeRegistry.WeaponBlind.setLevel(5), UpgradeRegistry.WeaponBlind.setLevel(4), inkSac, inkSac, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		addRecipe(bow, UpgradeRegistry.WeaponSlow.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedDiamond, ModItems.infusedDiamond); // CobWEB
		addRecipe(bow, UpgradeRegistry.WeaponSlow.setLevel(2), UpgradeRegistry.WeaponSlow.setLevel(1), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponSlow.setLevel(3), UpgradeRegistry.WeaponSlow.setLevel(2), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.WeaponSlow.setLevel(4), UpgradeRegistry.WeaponSlow.setLevel(3), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponSlow.setLevel(5), UpgradeRegistry.WeaponSlow.setLevel(4), Blocks.WEB, Blocks.WEB, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(bow, UpgradeRegistry.WeaponPhysicalDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.infusedDiamond, ModItems.infusedDiamond);// Quartz
		addRecipe(bow, UpgradeRegistry.WeaponPhysicalDamage.setLevel(2), UpgradeRegistry.WeaponPhysicalDamage.setLevel(1), Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponPhysicalDamage.setLevel(3), UpgradeRegistry.WeaponPhysicalDamage.setLevel(2), Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, Items.QUARTZ, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.WeaponPhysicalDamage.setLevel(4), UpgradeRegistry.WeaponPhysicalDamage.setLevel(3), Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponPhysicalDamage.setLevel(5), UpgradeRegistry.WeaponPhysicalDamage.setLevel(4), Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		ItemStack witherSkull = new ItemStack(Items.SKULL, 1, 1);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, witherSkull, witherSkull, witherSkull, ModItems.infusedStar, ModItems.infusedStar, ModItems.infusedStar);// Wither Skeleton Skull
		addRecipe(bow, UpgradeRegistry.WeaponWitherDamage.setLevel(2), UpgradeRegistry.WeaponWitherDamage.setLevel(1), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDamage.setLevel(3), UpgradeRegistry.WeaponWitherDamage.setLevel(2), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDamage.setLevel(4), UpgradeRegistry.WeaponWitherDamage.setLevel(3), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedStar, ModItems.platedStar);
		addRecipe(bow, UpgradeRegistry.WeaponWitherDamage.setLevel(5), UpgradeRegistry.WeaponWitherDamage.setLevel(4), witherSkull, witherSkull, witherSkull, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		ItemStack potionHarming = new ItemStack(Items.POTIONITEM, 1, 8204);
		ItemStack potionHarmingII = new ItemStack(Items.POTIONITEM, 1, 8236);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, potionHarming, potionHarming, potionHarming, ModItems.infusedDiamond, ModItems.infusedDiamond, ModItems.infusedDiamond);// Potion of Harming
		addRecipe(bow, UpgradeRegistry.WeaponMagicDamage.setLevel(2), UpgradeRegistry.WeaponMagicDamage.setLevel(1), potionHarming, potionHarming, potionHarming, ModItems.crystalDiamond, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDamage.setLevel(3), UpgradeRegistry.WeaponMagicDamage.setLevel(2), potionHarming, potionHarming, potionHarming, ModItems.infusedIngot, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDamage.setLevel(4), UpgradeRegistry.WeaponMagicDamage.setLevel(3), potionHarmingII, potionHarmingII, potionHarmingII, ModItems.platedDiamond, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponMagicDamage.setLevel(5), UpgradeRegistry.WeaponMagicDamage.setLevel(4), potionHarmingII, potionHarmingII, potionHarmingII, ModBlocks.infusedBlock, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		addRecipe(bow, UpgradeRegistry.WeaponFireDamage.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.infusedDiamond, ModItems.infusedDiamond, ModItems.infusedDiamond);// Blaze Rod
		addRecipe(bow, UpgradeRegistry.WeaponFireDamage.setLevel(2), UpgradeRegistry.WeaponFireDamage.setLevel(1), Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.crystalDiamond, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponFireDamage.setLevel(3), UpgradeRegistry.WeaponFireDamage.setLevel(2), Items.BLAZE_ROD, Items.BLAZE_ROD, Items.BLAZE_ROD, ModItems.infusedIngot, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(bow, UpgradeRegistry.WeaponFireDamage.setLevel(4), UpgradeRegistry.WeaponFireDamage.setLevel(3), Items.FIRE_CHARGE, Items.FIRE_CHARGE, Items.FIRE_CHARGE, ModItems.platedDiamond, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(bow, UpgradeRegistry.WeaponFireDamage.setLevel(5), UpgradeRegistry.WeaponFireDamage.setLevel(4), Items.FIRE_CHARGE, Items.FIRE_CHARGE, Items.FIRE_CHARGE, ModBlocks.infusedBlock, ModBlocks.infusedBlock, ModBlocks.infusedBlock);
	}

	private static void armorRecipes()
	{
		ArrayList<ItemStack> armorSet = new ArrayList<ItemStack>();
		armorSet.add(new ItemStack(ModArmory.infusedHelm));
		armorSet.add(new ItemStack(ModArmory.infusedPlate));
		armorSet.add(new ItemStack(ModArmory.infusedPants));
		armorSet.add(new ItemStack(ModArmory.infusedBoots));

		for (ItemStack armor : armorSet)
		{
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalThorns.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.CACTUS, Blocks.CACTUS, ModItems.infusedDiamond, ModItems.infusedDiamond); // Cactus
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalThorns.setLevel(2), UpgradeRegistry.ArmorPhysicalThorns.setLevel(1), Blocks.CACTUS, Blocks.CACTUS, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalThorns.setLevel(3), UpgradeRegistry.ArmorPhysicalThorns.setLevel(2), Blocks.CACTUS, Blocks.CACTUS, ModItems.platedDiamond, ModItems.platedDiamond);

			ItemStack poisonPotion = new ItemStack(Items.POTIONITEM, 1, 8260);
			addRecipe(armor, UpgradeRegistry.ArmorMagicThorns.setLevel(1), UpgradeRegistry.BaseUpgrade, poisonPotion, poisonPotion, ModItems.infusedDiamond, ModItems.infusedDiamond); // Potion of Poison
			addRecipe(armor, UpgradeRegistry.ArmorMagicThorns.setLevel(2), UpgradeRegistry.ArmorMagicThorns.setLevel(1), poisonPotion, poisonPotion, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorMagicThorns.setLevel(3), UpgradeRegistry.ArmorMagicThorns.setLevel(2), poisonPotion, poisonPotion, ModItems.platedDiamond, ModItems.platedDiamond);

			addRecipe(armor, UpgradeRegistry.ArmorBlindThorns.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.infusedStar, ModItems.infusedStar); // Soul Sand
			addRecipe(armor, UpgradeRegistry.ArmorBlindThorns.setLevel(2), UpgradeRegistry.ArmorBlindThorns.setLevel(1), Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(armor, UpgradeRegistry.ArmorBlindThorns.setLevel(3), UpgradeRegistry.ArmorBlindThorns.setLevel(2), Blocks.SOUL_SAND, Blocks.SOUL_SAND, ModItems.platedStar, ModItems.platedStar);

			ItemStack notchApple = new ItemStack(Items.GOLDEN_APPLE, 1, 1);
			addRecipe(armor, UpgradeRegistry.ArmorAbsorption.setLevel(1), UpgradeRegistry.BaseUpgrade, notchApple, notchApple, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModItems.infusedDiamond, ModItems.infusedDiamond); // Notch Apple + Glowstone
			addRecipe(armor, UpgradeRegistry.ArmorAbsorption.setLevel(2), UpgradeRegistry.ArmorAbsorption.setLevel(1), notchApple, notchApple, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorAbsorption.setLevel(3), UpgradeRegistry.ArmorAbsorption.setLevel(2), notchApple, notchApple, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModItems.infusedIngot, ModItems.infusedIngot);
			addRecipe(armor, UpgradeRegistry.ArmorAbsorption.setLevel(4), UpgradeRegistry.ArmorAbsorption.setLevel(3), notchApple, notchApple, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorAbsorption.setLevel(5), UpgradeRegistry.ArmorAbsorption.setLevel(4), notchApple, notchApple, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			addRecipe(armor, UpgradeRegistry.ArmorHealthBoost.setLevel(1), UpgradeRegistry.BaseUpgrade, notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.infusedDiamond, ModItems.infusedDiamond); // Notch Apple + Milk Bucket
			addRecipe(armor, UpgradeRegistry.ArmorHealthBoost.setLevel(2), UpgradeRegistry.ArmorHealthBoost.setLevel(1), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorHealthBoost.setLevel(3), UpgradeRegistry.ArmorHealthBoost.setLevel(2), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.infusedIngot, ModItems.infusedIngot);
			addRecipe(armor, UpgradeRegistry.ArmorHealthBoost.setLevel(4), UpgradeRegistry.ArmorHealthBoost.setLevel(3), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorHealthBoost.setLevel(5), UpgradeRegistry.ArmorHealthBoost.setLevel(4), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModBlocks.infusedStarmetal, ModBlocks.infusedBlock);

			addRecipe(armor, UpgradeRegistry.ArmorResistance.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, ModItems.infusedStar, ModItems.infusedStar); // Diamond
			addRecipe(armor, UpgradeRegistry.ArmorResistance.setLevel(2), UpgradeRegistry.ArmorResistance.setLevel(1), Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(armor, UpgradeRegistry.ArmorResistance.setLevel(3), UpgradeRegistry.ArmorResistance.setLevel(2), Items.DIAMOND, Items.DIAMOND, Items.NETHER_STAR, Items.NETHER_STAR, ModItems.infusedIngot, ModItems.infusedIngot);
			addRecipe(armor, UpgradeRegistry.ArmorResistance.setLevel(4), UpgradeRegistry.ArmorResistance.setLevel(3), Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, ModItems.platedStar, ModItems.platedStar);
			addRecipe(armor, UpgradeRegistry.ArmorResistance.setLevel(5), UpgradeRegistry.ArmorResistance.setLevel(4), Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, Items.DIAMOND, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

			addRecipe(armor, UpgradeRegistry.ArmorPhysicalProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.infusedDiamond, ModItems.infusedDiamond);// Iron
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalProtection.setLevel(2), UpgradeRegistry.ArmorPhysicalProtection.setLevel(1), Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalProtection.setLevel(3), UpgradeRegistry.ArmorPhysicalProtection.setLevel(2), Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorPhysicalProtection.setLevel(4), UpgradeRegistry.ArmorPhysicalProtection.setLevel(3), Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			addRecipe(armor, UpgradeRegistry.ArmorFireProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, ModItems.infusedDiamond, ModItems.infusedDiamond);// Magma Cream
			addRecipe(armor, UpgradeRegistry.ArmorFireProtection.setLevel(2), UpgradeRegistry.ArmorFireProtection.setLevel(1), Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorFireProtection.setLevel(3), UpgradeRegistry.ArmorFireProtection.setLevel(2), Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorFireProtection.setLevel(4), UpgradeRegistry.ArmorFireProtection.setLevel(3), Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, Items.MAGMA_CREAM, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			addRecipe(armor, UpgradeRegistry.ArmorBlastProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, ModItems.infusedDiamond, ModItems.infusedDiamond);// Obsidian
			addRecipe(armor, UpgradeRegistry.ArmorBlastProtection.setLevel(2), UpgradeRegistry.ArmorBlastProtection.setLevel(1), Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorBlastProtection.setLevel(3), UpgradeRegistry.ArmorBlastProtection.setLevel(2), Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorBlastProtection.setLevel(4), UpgradeRegistry.ArmorBlastProtection.setLevel(3), Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			addRecipe(armor, UpgradeRegistry.ArmorProjectileProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, ModItems.infusedDiamond, ModItems.infusedDiamond);// Arrow
			addRecipe(armor, UpgradeRegistry.ArmorProjectileProtection.setLevel(2), UpgradeRegistry.ArmorProjectileProtection.setLevel(1), Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorProjectileProtection.setLevel(3), UpgradeRegistry.ArmorProjectileProtection.setLevel(2), Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(armor, UpgradeRegistry.ArmorProjectileProtection.setLevel(4), UpgradeRegistry.ArmorProjectileProtection.setLevel(3), Items.ARROW, Items.ARROW, Items.ARROW, Items.ARROW, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			ItemStack witherSkull = new ItemStack(Items.SKULL, 1, 1);
			addRecipe(armor, UpgradeRegistry.ArmorWitherProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, witherSkull, witherSkull, witherSkull, witherSkull, ModItems.infusedStar, ModItems.infusedStar);// Wither Skeleton Skull
			addRecipe(armor, UpgradeRegistry.ArmorWitherProtection.setLevel(2), UpgradeRegistry.ArmorWitherProtection.setLevel(1), witherSkull, witherSkull, witherSkull, witherSkull, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(armor, UpgradeRegistry.ArmorWitherProtection.setLevel(3), UpgradeRegistry.ArmorWitherProtection.setLevel(2), witherSkull, witherSkull, witherSkull, witherSkull, ModItems.platedStar, ModItems.platedStar);
			addRecipe(armor, UpgradeRegistry.ArmorWitherProtection.setLevel(4), UpgradeRegistry.ArmorWitherProtection.setLevel(3), witherSkull, witherSkull, witherSkull, witherSkull, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

			ItemStack potionHealth = new ItemStack(Items.POTIONITEM, 1, 8197);
			addRecipe(armor, UpgradeRegistry.ArmorMagicProtection.setLevel(1), UpgradeRegistry.BaseUpgrade, potionHealth, potionHealth, potionHealth, potionHealth, ModItems.infusedStar, ModItems.infusedStar);// Potion of Instant Health
			addRecipe(armor, UpgradeRegistry.ArmorMagicProtection.setLevel(2), UpgradeRegistry.ArmorMagicProtection.setLevel(1), potionHealth, potionHealth, potionHealth, potionHealth, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(armor, UpgradeRegistry.ArmorMagicProtection.setLevel(3), UpgradeRegistry.ArmorMagicProtection.setLevel(2), potionHealth, potionHealth, potionHealth, potionHealth, ModItems.platedStar, ModItems.platedStar);
			addRecipe(armor, UpgradeRegistry.ArmorMagicProtection.setLevel(4), UpgradeRegistry.ArmorMagicProtection.setLevel(3), potionHealth, potionHealth, potionHealth, potionHealth, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

			addRecipe(armor, EntityEquipmentSlot.LEGS, UpgradeRegistry.ArmorStepAssist.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.LEATHER, Items.LEATHER, Items.SLIME_BALL, Items.SLIME_BALL, Items.STRING, Items.STRING, ModItems.infusedIngot, ModItems.infusedIngot);

			addRecipe(armor, EntityEquipmentSlot.FEET, UpgradeRegistry.ArmorRunSpeed.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.SUGAR, Items.SUGAR, Items.GLOWSTONE_DUST, Items.GLOWSTONE_DUST, ModItems.infusedDiamond, ModItems.infusedDiamond);
			addRecipe(armor, EntityEquipmentSlot.FEET, UpgradeRegistry.ArmorRunSpeed.setLevel(2), UpgradeRegistry.ArmorRunSpeed.setLevel(1), Items.SUGAR, Items.SUGAR, Blocks.GLOWSTONE, Blocks.GLOWSTONE, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(armor, EntityEquipmentSlot.FEET, UpgradeRegistry.ArmorRunSpeed.setLevel(3), UpgradeRegistry.ArmorRunSpeed.setLevel(2), Items.SUGAR, Items.SUGAR, Blocks.ICE, Blocks.ICE, ModItems.platedDiamond, ModItems.platedDiamond);
		}
	}

	private static void amuletRecipes()
	{
		ItemStack amulet = new ItemStack(ModArmory.infusedAmulet);

		addRecipe(amulet, UpgradeRegistry.AmuletFlight.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.FEATHER, Items.FEATHER, Items.FEATHER, Blocks.DRAGON_EGG, ModItems.platedDiamond, ModItems.platedDiamond);

		ItemStack lapis = new ItemStack(Items.DYE, 1, 4);
		addRecipe(amulet, UpgradeRegistry.AmuletLooting.setLevel(1), UpgradeRegistry.BaseUpgrade, lapis, lapis, ModItems.infusedShard);
		addRecipe(amulet, UpgradeRegistry.AmuletLooting.setLevel(2), UpgradeRegistry.AmuletLooting.setLevel(1), lapis, lapis, ModBlocks.shardBlock);
		addRecipe(amulet, UpgradeRegistry.AmuletLooting.setLevel(3), UpgradeRegistry.AmuletLooting.setLevel(2), lapis, lapis, ModItems.infusedDiamond);
		addRecipe(amulet, UpgradeRegistry.AmuletLooting.setLevel(4), UpgradeRegistry.AmuletLooting.setLevel(3), Blocks.LAPIS_BLOCK, Blocks.LAPIS_BLOCK, ModItems.crystalDiamond);
		addRecipe(amulet, UpgradeRegistry.AmuletLooting.setLevel(5), UpgradeRegistry.AmuletLooting.setLevel(4), Blocks.LAPIS_BLOCK, Blocks.LAPIS_BLOCK, ModItems.infusedIngot);

		if (!Loader.isModLoaded("TravellersGear"))
		{
			addRecipe(amulet, UpgradeRegistry.BaubleFireImmunity.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.GUNPOWDER, Items.GUNPOWDER, ModItems.infusedDiamond, ModItems.infusedDiamond);
			addRecipe(amulet, UpgradeRegistry.BaubleFireImmunity.setLevel(1), UpgradeRegistry.BaubleFireImmunity.setLevel(1), Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.GUNPOWDER, Items.GUNPOWDER, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(amulet, UpgradeRegistry.BaubleFireImmunity.setLevel(1), UpgradeRegistry.BaubleFireImmunity.setLevel(1), Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.GUNPOWDER, Items.GUNPOWDER, ModItems.infusedIngot, ModItems.infusedIngot);
			addRecipe(amulet, UpgradeRegistry.BaubleFireImmunity.setLevel(1), UpgradeRegistry.BaubleFireImmunity.setLevel(1), Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.GUNPOWDER, Items.GUNPOWDER, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(amulet, UpgradeRegistry.BaubleFireImmunity.setLevel(1), UpgradeRegistry.BaubleFireImmunity.setLevel(1), Items.BLAZE_POWDER, Items.BLAZE_POWDER, Items.GUNPOWDER, Items.GUNPOWDER, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			addRecipe(amulet, UpgradeRegistry.BaublePoisonImmunity.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.FERMENTED_SPIDER_EYE, Items.FERMENTED_SPIDER_EYE, Items.NETHER_WART, Items.NETHER_WART, ModItems.infusedDiamond, ModItems.infusedDiamond);
			addRecipe(amulet, UpgradeRegistry.BaublePoisonImmunity.setLevel(1), UpgradeRegistry.BaublePoisonImmunity.setLevel(1), Items.FERMENTED_SPIDER_EYE, Items.FERMENTED_SPIDER_EYE, Items.NETHER_WART, Items.NETHER_WART, ModItems.crystalDiamond, ModItems.crystalDiamond);
			addRecipe(amulet, UpgradeRegistry.BaublePoisonImmunity.setLevel(1), UpgradeRegistry.BaublePoisonImmunity.setLevel(1), Items.FERMENTED_SPIDER_EYE, Items.FERMENTED_SPIDER_EYE, Items.NETHER_WART, Items.NETHER_WART, ModItems.infusedIngot, ModItems.infusedIngot);
			addRecipe(amulet, UpgradeRegistry.BaublePoisonImmunity.setLevel(1), UpgradeRegistry.BaublePoisonImmunity.setLevel(1), Items.FERMENTED_SPIDER_EYE, Items.FERMENTED_SPIDER_EYE, Items.NETHER_WART, Items.NETHER_WART, ModItems.platedDiamond, ModItems.platedDiamond);
			addRecipe(amulet, UpgradeRegistry.BaublePoisonImmunity.setLevel(1), UpgradeRegistry.BaublePoisonImmunity.setLevel(1), Items.FERMENTED_SPIDER_EYE, Items.FERMENTED_SPIDER_EYE, Items.NETHER_WART, Items.NETHER_WART, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

			ItemStack witherSkull = new ItemStack(Items.SKULL, 1, 1);
			addRecipe(amulet, UpgradeRegistry.BaubleWitherImmunity.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.SOUL_SAND, Blocks.SOUL_SAND, witherSkull, witherSkull, ModItems.infusedStar, ModItems.infusedStar);
			addRecipe(amulet, UpgradeRegistry.BaubleWitherImmunity.setLevel(1), UpgradeRegistry.BaubleWitherImmunity.setLevel(1), Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, witherSkull, witherSkull, witherSkull, ModItems.infusedStar, ModItems.infusedStar);
			addRecipe(amulet, UpgradeRegistry.BaubleWitherImmunity.setLevel(1), UpgradeRegistry.BaubleWitherImmunity.setLevel(1), Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, witherSkull, witherSkull, witherSkull, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(amulet, UpgradeRegistry.BaubleWitherImmunity.setLevel(1), UpgradeRegistry.BaubleWitherImmunity.setLevel(1), Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, witherSkull, witherSkull, witherSkull, ModItems.platedStar, ModItems.platedStar);
			addRecipe(amulet, UpgradeRegistry.BaubleWitherImmunity.setLevel(1), UpgradeRegistry.BaubleWitherImmunity.setLevel(1), Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, Blocks.SOUL_SAND, witherSkull, witherSkull, witherSkull, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

			// if (Loader.isModLoaded("Thaumcraft")) ThaumcraftHandler.addTaintInfusionRecipes(amulet);
		}
	}

	private static void ringRecipes()
	{
		ItemStack ring = new ItemStack(ModArmory.infusedRing);

		addRecipe(ring, UpgradeRegistry.RingPotionSwiftness.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.SUGAR, Items.SUGAR, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionSwiftness.setLevel(2), UpgradeRegistry.RingPotionSwiftness.setLevel(1), Items.NETHER_WART, Items.NETHER_WART, Items.SUGAR, Items.SUGAR, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionSwiftness.setLevel(3), UpgradeRegistry.RingPotionSwiftness.setLevel(2), Items.NETHER_WART, Items.NETHER_WART, Items.SUGAR, Items.SUGAR, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionHaste.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.REDSTONE, Items.REDSTONE, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionHaste.setLevel(2), UpgradeRegistry.RingPotionHaste.setLevel(1), Items.NETHER_WART, Items.NETHER_WART, Items.REDSTONE, Items.REDSTONE, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionHaste.setLevel(3), UpgradeRegistry.RingPotionHaste.setLevel(2), Items.NETHER_WART, Items.NETHER_WART, Items.REDSTONE, Items.REDSTONE, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionStrength.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.BLAZE_POWDER, Items.BLAZE_POWDER, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionStrength.setLevel(2), UpgradeRegistry.RingPotionStrength.setLevel(1), Items.NETHER_WART, Items.NETHER_WART, Items.BLAZE_POWDER, Items.BLAZE_POWDER, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionStrength.setLevel(3), UpgradeRegistry.RingPotionStrength.setLevel(2), Items.NETHER_WART, Items.NETHER_WART, Items.BLAZE_POWDER, Items.BLAZE_POWDER, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionJumpBoost.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.FEATHER, Items.FEATHER, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionJumpBoost.setLevel(2), UpgradeRegistry.RingPotionJumpBoost.setLevel(1), Items.NETHER_WART, Items.NETHER_WART, Items.FEATHER, Items.FEATHER, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionJumpBoost.setLevel(3), UpgradeRegistry.RingPotionJumpBoost.setLevel(2), Items.NETHER_WART, Items.NETHER_WART, Items.FEATHER, Items.FEATHER, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionRegeneration.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.GHAST_TEAR, Items.GHAST_TEAR, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionRegeneration.setLevel(2), UpgradeRegistry.RingPotionRegeneration.setLevel(1), Items.NETHER_WART, Items.NETHER_WART, Items.GHAST_TEAR, Items.GHAST_TEAR, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(ring, UpgradeRegistry.RingPotionRegeneration.setLevel(3), UpgradeRegistry.RingPotionRegeneration.setLevel(2), Items.NETHER_WART, Items.NETHER_WART, Items.GHAST_TEAR, Items.GHAST_TEAR, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionNightVision.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.GOLDEN_CARROT, Items.GOLDEN_CARROT, ModItems.platedDiamond, ModItems.platedDiamond);

		ItemStack pufferfish = new ItemStack(Items.FISH, 1, 3);
		addRecipe(ring, UpgradeRegistry.RingPotionWaterBreathing.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, pufferfish, pufferfish, ModItems.platedDiamond, ModItems.platedDiamond);

		addRecipe(ring, UpgradeRegistry.RingPotionFireResistance.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.NETHER_WART, Items.NETHER_WART, Items.MAGMA_CREAM, Items.MAGMA_CREAM, ModItems.platedDiamond, ModItems.platedDiamond);
	}

	private static void beltRecipes()
	{
		ItemStack belt = new ItemStack(ModArmory.infusedBelt);

		addRecipe(belt, UpgradeRegistry.BeltCleave.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.STONE_SWORD, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(belt, UpgradeRegistry.BeltCleave.setLevel(2), UpgradeRegistry.BeltCleave.setLevel(1), Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.IRON_SWORD, ModItems.infusedStar, ModItems.infusedStar);
		addRecipe(belt, UpgradeRegistry.BeltCleave.setLevel(3), UpgradeRegistry.BeltCleave.setLevel(2), Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.IRON_SWORD, ModItems.crystalStar, ModItems.crystalStar);
		addRecipe(belt, UpgradeRegistry.BeltCleave.setLevel(4), UpgradeRegistry.BeltCleave.setLevel(3), Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.IRON_SWORD, ModItems.platedStar, ModItems.platedStar);
		addRecipe(belt, UpgradeRegistry.BeltCleave.setLevel(5), UpgradeRegistry.BeltCleave.setLevel(4), Items.GUNPOWDER, Items.GUNPOWDER, Items.GUNPOWDER, Items.IRON_SWORD, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

		addRecipe(belt, UpgradeRegistry.BeltKnockback.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.OBSIDIAN, Blocks.PISTON, Blocks.PISTON, Blocks.PISTON, ModItems.infusedDiamond, ModItems.infusedDiamond);
		addRecipe(belt, UpgradeRegistry.BeltKnockback.setLevel(2), UpgradeRegistry.BeltKnockback.setLevel(1), Blocks.OBSIDIAN, Blocks.PISTON, Blocks.PISTON, Blocks.PISTON, ModItems.crystalDiamond, ModItems.crystalDiamond);
		addRecipe(belt, UpgradeRegistry.BeltKnockback.setLevel(3), UpgradeRegistry.BeltKnockback.setLevel(2), Blocks.OBSIDIAN, Blocks.PISTON, Blocks.PISTON, Blocks.PISTON, ModItems.infusedIngot, ModItems.infusedIngot);
		addRecipe(belt, UpgradeRegistry.BeltKnockback.setLevel(4), UpgradeRegistry.BeltKnockback.setLevel(3), Blocks.OBSIDIAN, Blocks.PISTON, Blocks.PISTON, Blocks.PISTON, ModItems.platedDiamond, ModItems.platedDiamond);
		addRecipe(belt, UpgradeRegistry.BeltKnockback.setLevel(5), UpgradeRegistry.BeltKnockback.setLevel(4), Blocks.OBSIDIAN, Blocks.PISTON, Blocks.PISTON, Blocks.PISTON, ModBlocks.infusedBlock, ModBlocks.infusedBlock);

		if (!Loader.isModLoaded("TravellersGear"))
		{
			ItemStack notchApple = new ItemStack(Items.GOLDEN_APPLE, 1, 1);
			addRecipe(belt, UpgradeRegistry.BaubleHealthBoost.setLevel(1), UpgradeRegistry.BaseUpgrade, Items.GOLDEN_APPLE, Items.GOLDEN_APPLE, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.infusedStar, ModItems.infusedStar);
			addRecipe(belt, UpgradeRegistry.BaubleHealthBoost.setLevel(2), UpgradeRegistry.BaubleHealthBoost.setLevel(1), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.infusedStar, ModItems.infusedStar);
			addRecipe(belt, UpgradeRegistry.BaubleHealthBoost.setLevel(3), UpgradeRegistry.BaubleHealthBoost.setLevel(2), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.crystalStar, ModItems.crystalStar);
			addRecipe(belt, UpgradeRegistry.BaubleHealthBoost.setLevel(4), UpgradeRegistry.BaubleHealthBoost.setLevel(3), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModItems.platedStar, ModItems.platedStar);
			addRecipe(belt, UpgradeRegistry.BaubleHealthBoost.setLevel(5), UpgradeRegistry.BaubleHealthBoost.setLevel(4), notchApple, notchApple, Items.MILK_BUCKET, Items.MILK_BUCKET, ModBlocks.infusedStarmetal, ModBlocks.infusedStarmetal);

			addRecipe(belt, UpgradeRegistry.BaubleMiningLimiter.setLevel(1), UpgradeRegistry.BaseUpgrade, Blocks.GOLD_BLOCK, Items.STRING, Items.STRING, Items.STRING, Items.STRING, ModItems.infusedIngot);
		}
	}

	private static void regeantRecipes()
	{
		ItemStack diamond = new ItemStack(Items.DIAMOND);
		ItemStack netherStar = new ItemStack(Items.NETHER_STAR);

		ItemStack infusedDiamond = new ItemStack(ModItems.infusedDiamond);
		ItemStack infusedStar = new ItemStack(ModItems.infusedStar);
		ItemStack crystalDiamond = new ItemStack(ModItems.crystalDiamond);
		ItemStack crystalStar = new ItemStack(ModItems.crystalStar);
		ItemStack platedDiamond = new ItemStack(ModItems.platedDiamond);
		ItemStack platedStar = new ItemStack(ModItems.platedStar);
		ItemStack infusedStarmetal = new ItemStack(ModBlocks.infusedStarmetal);

		addRecipe(infusedDiamond, 36, diamond, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET);
		addRecipe(infusedStar, 36, netherStar, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET);
		addRecipe(crystalDiamond, 52, diamond, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		addRecipe(crystalDiamond, 16, infusedDiamond, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		addRecipe(crystalStar, 52, netherStar, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		addRecipe(crystalStar, 16, infusedStar, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT);
		addRecipe(platedDiamond, 232, diamond, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK);
		addRecipe(platedDiamond, 180, crystalDiamond, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK);
		addRecipe(platedStar, 232, netherStar, Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK);
		addRecipe(platedStar, 180, crystalStar, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK, Blocks.IRON_BLOCK);
		addRecipe(infusedStarmetal, 1304, netherStar, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK);
		addRecipe(infusedStarmetal, 144, platedStar, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, Blocks.OBSIDIAN, platedDiamond, platedDiamond, platedDiamond, platedDiamond, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK);
	}

	/**
	 * Adds a recipe for an upgrade using the Infusion structure.
	 * 
	 * @param item
	 *            The item that the upgrade can be applied to. item.getItem().getClass() is used to differentiate between items.
	 * @param outerItems
	 *            The list of items that must be in the outer ring of the Infusion structure, starting at the North, and going clockwise. All items must be non-null and have a non-zero stack size.
	 * @param upgrade
	 *            The upgrade that results from the recipe.
	 * @param upgradeReq
	 *            The prerequisite upgrade for the recipe's upgrade, i.e. Fire II requiring Fire I. If set to null, there is no prerequisite.
	 * @param recipeItems
	 *            The list of items that must be in the inner ring of the Infusion structure, starting at the North, and going clockwise. All items must be non-null and have a non-zero stack size.
	 */
	public static void addRecipe(ItemStack item, Upgrade upgrade, Upgrade upgradeReq, Object... recipeItems)
	{
		addRecipe(item, upgrade, new Upgrade[] { upgradeReq }, recipeItems);
	}

	public static void addRecipe(ItemStack item, EntityEquipmentSlot armorType, Upgrade upgrade, Upgrade upgradeReq, Object... recipeItems)
	{
		addRecipe(item, armorType, upgrade, new Upgrade[] { upgradeReq }, recipeItems);
	}

	public static void addRecipe(ItemStack item, Upgrade upgrade, Upgrade[] upgradeReqs, Object... recipeItems)
	{
		addRecipe(item, null, upgrade, upgradeReqs, recipeItems);
	}

	public static void addRecipe(ItemStack item, EntityEquipmentSlot armorType, Upgrade upgrade, Upgrade[] upgradeReqs, Object... recipeItems)
	{
		UpgradeRecipe recipe = new UpgradeRecipe(item, armorType, upgrade, upgradeReqs, recipeItems);
		ArrayList<UpgradeRecipe> recipeList = upgradeRecipes.get(item.getItem().getClass());
		if (recipeList == null) recipeList = new ArrayList<UpgradeRecipe>();
		recipeList.add(recipe);
		upgradeRecipes.put(item.getItem().getClass(), recipeList);
	}

	public static void addRecipe(ItemStack output, int shardCount, ItemStack center, Object... items)
	{
		ItemRecipe recipe = new ItemRecipe(output, shardCount, center, items);
		itemRecipes.add(recipe);
	}

	@SuppressWarnings("unchecked")
	public static ItemRecipe getItemRecipe(ItemStack item, ArrayList<ItemStack> pylonItems)
	{
		if (item == null || pylonItems == null) return null;

		Iterator<ItemStack> iter = pylonItems.iterator();
		while (iter.hasNext())
			if (iter.next() == null) iter.remove();
		if (pylonItems.size() == 0) return null;

		for (ItemRecipe recipe : itemRecipes)
		{
			if (!recipe.getCenter().isItemEqual(item)) continue;

			int numShards = recipe.getShardCount();

			ArrayList<ItemStack> requiredItems = (ArrayList<ItemStack>) recipe.getItems().clone();
			ArrayList<ItemStack> currentItems = (ArrayList<ItemStack>) pylonItems.clone();
			ArrayList<ItemStack> shardContainers = new ArrayList<ItemStack>();
			for (int i = 0; i < currentItems.size(); i++)
			{
				if (i < 0) continue;
				if (currentItems.get(i).isItemEqual(new ItemStack(ModItems.infusedShard)))
				{
					currentItems.remove(i);
					numShards--;
					i--;
				}
				else if (currentItems.get(i).isItemEqual(new ItemStack(ModBlocks.shardBlock)))
				{
					currentItems.remove(i);
					numShards -= 9;
					i--;
				}
				else if (currentItems.get(i).getItem() instanceof ItemShardContainer)
				{
					shardContainers.add(currentItems.get(i));
					currentItems.remove(i);
					i--;
				}
				else
				{
					for (int j = 0; j < requiredItems.size(); j++)
					{
						if (i < 0) break;
						if (j < 0) continue;

						if (currentItems.get(i).isItemEqual(requiredItems.get(j)))
						{
							currentItems.remove(i);
							requiredItems.remove(j);
							i--;
							j--;
						}
					}
				}
			}

			if (currentItems.size() != 0) continue;
			if (requiredItems.size() != 0) continue;

			while (numShards > 0 && shardContainers.size() > 0)
			{
				ItemStack container = shardContainers.remove(0);
				numShards = ItemShardContainer.checkShards(container, numShards);
			}
			if (numShards != 0) continue;

			return recipe;
		}

		return null;
	}

	public static ItemStack checkItemRecipe(ItemStack item, ArrayList<ItemStack> pylonItems)
	{
		ItemRecipe recipe = getItemRecipe(item, pylonItems);
		if (recipe != null) return recipe.getOutput();
		return null;
	}

	/**
	 * Returns the upgrade that would be applied to the item in the central infuser slot, based on the items around it
	 * 
	 * @param item
	 *            The item being upgraded
	 * @param pylonItems
	 *            An ArrayList of the items found in the pylons found within 5 blocks of the infuser.
	 * @return The Upgrade resulting from the given item and ArrayLists. Returns null if any parts are null, the item contains an incompatible upgrade, or the item does not meet any prerequisites for upgrades.
	 */
	@SuppressWarnings("unchecked")
	public static Upgrade checkUpgradeRecipe(ItemStack item, ArrayList<ItemStack> pylonItems)
	{
		if (item == null || pylonItems == null) return null;

		Iterator<ItemStack> iter = pylonItems.iterator();
		while (iter.hasNext())
			if (iter.next() == null) iter.remove();
		if (pylonItems.size() == 0) return null;

		ArrayList<UpgradeRecipe> availableUpgrades = upgradeRecipes.get(item.getItem().getClass());
		if (availableUpgrades == null || availableUpgrades.size() == 0) return null; // Of course, if there are none, the check fails

		ArrayList<Upgrade> currentUpgrades = getCurrentUpgrades(item);

		for (UpgradeRecipe recipe : availableUpgrades)
		{
			Upgrade upgrade = recipe.getUpgrade();
			if (upgrade == null) continue;
			ArrayList<Upgrade> requirements = recipe.getRequirements();
			if (requirements == null) continue;
			EntityEquipmentSlot slot = recipe.getSlot();
			if (slot != null && item.getItem() instanceof ItemArmor && ((ItemArmor) item.getItem()).armorType != slot) continue;

			boolean hasBetter = false;
			for (Upgrade current : currentUpgrades)
			{
				if (current == null) continue;
				if (current.name.equals(upgrade.name) && upgrade.level <= current.level)
				{
					hasBetter = true;
					break;
				}
			}
			if (hasBetter) continue;

			currentUpgrades.add(UpgradeRegistry.BaseUpgrade);
			int reqsMet = 0;
			for (int i = 0; i < currentUpgrades.size(); i++)
			{
				for (int j = 0; j < requirements.size(); j++)
				{
					if (reqsMet == requirements.size()) break;

					Upgrade has = currentUpgrades.get(i);
					Upgrade needs = requirements.get(j);
					if (has == null) reqsMet++;
					else if (has.name.equals("BaseUpgrade")) reqsMet++;
					else if (has.name.equals(needs.name) && has.level >= needs.level) reqsMet++;
				}
			}
			if (reqsMet != requirements.size()) continue;

			boolean incompatible = false;
			for (Upgrade compat : currentUpgrades)
			{
				if (compat == null) continue;
				if (upgrade.incompatibleUpgrades.contains(compat.name))
				{
					incompatible = true;
					break;
				}
			}
			if (incompatible) continue;

			ArrayList<ItemStack> requiredItems = (ArrayList<ItemStack>) recipe.getRecipeItems().clone();
			ArrayList<ItemStack> currentItems = (ArrayList<ItemStack>) pylonItems.clone();
			for (int i = 0; i < currentItems.size(); i++)
			{
				for (int j = 0; j < requiredItems.size(); j++)
				{
					if (i < 0 || j < 0) continue;
					if (currentItems.get(i).isItemEqual(requiredItems.get(j)))
					{
						currentItems.remove(i);
						requiredItems.remove(j);
						i--;
						j--;
					}
				}
			}
			if (currentItems.size() != 0) continue;
			if (requiredItems.size() != 0) continue;

			return upgrade;
		}
		return null;
	}

	/**
	 * Gets the list of all upgrades currently on the item
	 * 
	 * @param item
	 *            The item from which the list of upgrades is being retrieved
	 * @return The list of empty upgrades found on the item. These upgrades have no data beyond name and level. The list ends with one null upgrade, to allow easier checking for upgrades with no prerequisite
	 */
	public static ArrayList<Upgrade> getCurrentUpgrades(ItemStack item)
	{
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		if (!item.hasTagCompound()) return upgrades;
		NBTTagCompound itemCompound = item.getTagCompound();
		if (!itemCompound.hasKey("Upgrades")) return upgrades;
		NBTTagList upgradeList = itemCompound.getTagList("Upgrades", NBT.TAG_COMPOUND);

		// Go through the upgradeList tag to figure out what upgrades the item
		// has
		for (int i = 0; i < upgradeList.tagCount(); i++)
		{
			NBTTagCompound tag = upgradeList.getCompoundTagAt(i);
			upgrades.add(new Upgrade(tag.getString("Name"), tag.getInteger("Level")));
		}

		// Add in the "null upgrade" to allow level 1 upgrades to be added to
		// the item
		upgrades.add(null);

		return upgrades;
	}

	/**
	 * Adds the given upgrade to the given item.
	 * 
	 * @param item
	 *            The item being given the upgrade.
	 * @param upgrade
	 *            The upgrade being added to the item.
	 * @return The item with the upgrade added. If the upgrade did not exist, the initial item is returned instead.
	 */
	public static ItemStack addUpgrade(ItemStack item, Upgrade upgrade)
	{
		// If the upgrade is blank, change nothing
		if (upgrade == null) return item;

		if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());

		// Get the TagList holding the upgrade data
		NBTTagList upgradeList = item.getTagCompound().getTagList("Upgrades", NBT.TAG_COMPOUND);

		// Make a new TagCompound for the upgrade being added, and store the name and level of the upgrade
		NBTTagCompound newTag = new NBTTagCompound();
		newTag.setString("Name", upgrade.name);
		newTag.setInteger("Level", upgrade.level);

		for (int i = 0; i < upgradeList.tagCount(); i++)
		{
			// If the upgrade already exists on the item, remove the current version and add the new one
			NBTTagCompound oldUpgrade = upgradeList.getCompoundTagAt(i);
			if (oldUpgrade.getString("Name").equals(upgrade.name))
			{
				upgradeList.removeTag(i);
				if (upgrade.level != 0)
				{
					upgradeList.appendTag(newTag);
					NBTTagCompound compound = item.getTagCompound();
					int itemLevel = compound.getInteger("Level");
					compound.setInteger("Level", itemLevel + newTag.getInteger("Level") - oldUpgrade.getInteger("Level"));
					item.setTagCompound(compound);
				}
				else
				{
					NBTTagCompound compound = item.getTagCompound();
					compound.setInteger("Level", compound.getInteger("Level") - oldUpgrade.getInteger("Level"));
					item.setTagCompound(compound);
				}
				NBTTagCompound compound = item.getTagCompound();
				compound.setTag("Upgrades", upgradeList);
				item.setTagCompound(compound);
				return item;
			}
		}

		// Otherwise just add the new one
		if (upgrade.level != 0)
		{
			upgradeList.appendTag(newTag);
			NBTTagCompound compound = item.getTagCompound();
			int itemLevel = compound.getInteger("Level");
			compound.setInteger("Level", itemLevel + newTag.getInteger("Level"));
			item.setTagCompound(compound);
		}
		NBTTagCompound compound = item.getTagCompound();
		compound.setTag("Upgrades", upgradeList);
		item.setTagCompound(compound);
		return item;
	}
}
