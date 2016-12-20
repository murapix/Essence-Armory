package essenceMod.items;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.entities.EntityModArrow;
import essenceMod.handlers.ConfigHandler;
import essenceMod.registry.ModArmory;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;
import essenceMod.tabs.ModTabs;
import essenceMod.utility.UtilityHelper;

@SuppressWarnings("deprecation")
public class ItemModBow extends ItemBow implements IUpgradeable
{
	int level;

	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!item.hasTagCompound())
			onCreated(item, world, (EntityPlayer) entity);
	}

	public ItemModBow()
	{
		super();
		setCreativeTab(ModTabs.tabEssence);
		setMaxDamage(0);
		level = 0;
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    ItemStack itemstack = entityIn.getActiveItemStack();
                    return itemstack != null && itemstack.getItem() == ModArmory.infusedBow ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}

	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		NBTTagCompound compound = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
		compound.setInteger("Level", level);
		item.setTagCompound(compound);
		item.addEnchantment(ModArmory.shardLooter, 1);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelBakery.registerItemVariants(this, new ModelResourceLocation(getRegistryName(), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		for (int i = 0; i < 3; i++)
		{
			ModelBakery.registerItemVariants(this, new ModelResourceLocation(getRegistryName() + "_" + i, "inventory"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, i + 1, new ModelResourceLocation(getRegistryName() + "_" + i, "inventory"));
		}
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, itemstack != null || flag);
            if (i < 0) return;

            if (itemstack != null || flag)
            {
                if (itemstack == null)
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);

                if ((double)f >= 0.1D)
                {
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemArrow ? ((ItemArrow)itemstack.getItem()).isInfinite(itemstack, stack, entityplayer) : false);

                    if (!worldIn.isRemote)
                    {
                        EntityArrow entityarrow = EntityModArrow.createArrow(worldIn, stack, itemstack, entityplayer);
                        entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setFire(100);
                        }

                        stack.damageItem(1, entityplayer);

                        if (flag1)
                        {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.spawnEntityInWorld(entityarrow);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1)
                    {
                        --itemstack.stackSize;

                        if (itemstack.stackSize == 0)
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }

	protected ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return null;
        }
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
	{
		return EnumAction.BOW;
	}

	public static int getLevel(ItemStack item)
	{
		if (!item.hasTagCompound())
		{
			NBTTagCompound compound = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
			compound.setInteger("Level", 0);
			compound.setBoolean("ItemInUse", false);
			item.setTagCompound(compound);
			item.addEnchantment(ModArmory.shardLooter, 1);
		}
		return item.getTagCompound().getInteger("Level");
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer entityPlayer, List<String> list, boolean bool)
	{
		if (!item.hasTagCompound())
			onCreated(item, entityPlayer.worldObj, entityPlayer);
		if (GuiScreen.isShiftKeyDown())
			list.addAll(addShiftInfo(item));
		else list.addAll(addNormalInfo(item));

		int phys = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		int fire = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		int magic = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		int wither = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);
		int divine = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponDivineDamage);
		int chaos = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponChaosDamage);
		int taint = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDamage);
		int frost = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostDamage);
		int holy = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponHolyDamage);
		int lightning = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponLightningDamage);
		int wind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWindDamage);

		list.add("Arrows deal up to:");

		float weaponDamage = 2.0F + (getLevel(item) / 5);
		weaponDamage *= 4.125F * (1 + Upgrade.getUpgradeLevel(item, UpgradeRegistry.BowArrowSpeed) * 0.05F);
		phys *= ConfigHandler.isNormalDamagePercent ? weaponDamage * ConfigHandler.normalDamageMulti : ConfigHandler.normalDamageAmount * ConfigHandler.normalBowMulti;
		fire *= ConfigHandler.isFireDamagePercent ? weaponDamage * ConfigHandler.fireDamageMulti : ConfigHandler.fireDamageAmount;
		wither *= ConfigHandler.isWitherDamagePercent ? weaponDamage * ConfigHandler.witherDamageMulti : ConfigHandler.witherDamageAmount;
		magic *= ConfigHandler.isMagicDamagePercent ? weaponDamage * ConfigHandler.magicDamageMulti : ConfigHandler.magicDamageAmount;

		phys *= ConfigHandler.normalBowMulti;
		fire *= ConfigHandler.fireBowMulti;
		magic *= ConfigHandler.magicBowMulti;
		wither *= ConfigHandler.witherBowMulti;

		phys += weaponDamage;

		double physText = Math.round(phys * 4) / 4D;
		double fireText = Math.round(fire * 4) / 4D;
		double witherText = Math.round(wither * 4) / 4D;
		double magicText = Math.round(magic * 4) / 4D;
		double chaosText = Math.round(chaos * 4) / 4D;
		double divineText = Math.round(divine * 4) / 4D;
		double taintText = Math.round(taint * 4) / 4D;
		double frostText = Math.round(frost * 4) / 4D;
		double holyText = Math.round(holy * 4) / 4D;
		double lightningText = Math.round(lightning * 4) / 4D;
		double windText = Math.round(wind * 4) / 4D;

		if (physText != 0)
		{
			if (physText == (int) physText)
				list.add(TextFormatting.BLUE + "+" + ((int) physText) + " Damage");
			else list.add(TextFormatting.BLUE + "+" + physText + " Damage");
		}
		if (fireText != 0)
		{
			if (fireText == (int) fireText)
				list.add(TextFormatting.BLUE + "+" + ((int) fireText) + " Fire Damage");
			else list.add(TextFormatting.BLUE + "+" + fireText + " Fire Damage");
		}
		if (witherText != 0)
		{
			if (witherText == (int) witherText)
				list.add(TextFormatting.BLUE + "+" + ((int) witherText) + " Wither Damage");
			else list.add(TextFormatting.BLUE + "+" + witherText + " Wither Damage");
		}
		if (magicText != 0)
		{
			if (magicText == (int) magicText)
				list.add(TextFormatting.BLUE + "+" + ((int) magicText) + " Magic Damage");
			else list.add(TextFormatting.BLUE + "+" + magicText + " Magic Damage");
		}
		if (chaosText != 0)
		{
			if (chaosText == (int) chaosText)
				list.add(TextFormatting.BLUE + "+" + ((int) chaosText) + " Chaos Damage");
			else list.add(TextFormatting.BLUE + "+" + chaosText + " Chaos Damage");
		}
		if (divineText != 0)
		{
			if (divineText == (int) divineText)
				list.add(TextFormatting.BLUE + "+" + ((int) divineText) + " Divine Damage");
			else list.add(TextFormatting.BLUE + "+" + divineText + " Divine Damage");
		}
		if (taintText != 0)
		{
			if (taintText == (int) taintText)
				list.add(TextFormatting.BLUE + "+" + ((int) taintText) + " Flux Damage");
			else list.add(TextFormatting.BLUE + "+" + taintText + " Flux Damage");
		}
		if (frostText != 0)
		{
			if (frostText == (int) frostText)
				list.add(TextFormatting.BLUE + "+" + ((int) frostText) + " Frost Damage");
			else list.add(TextFormatting.BLUE + "+" + frostText + " Frost Damage");
		}
		if (holyText != 0)
		{
			if (holyText == (int) holyText)
				list.add(TextFormatting.BLUE + "+" + ((int) holyText) + " Holy Damage");
			else list.add(TextFormatting.BLUE + "+" + holyText + " Holy Damage");
		}
		if (lightningText != 0)
		{
			if (lightningText == (int) lightningText)
				list.add(TextFormatting.BLUE + "+" + ((int) lightningText) + " Lightning Damage");
			else list.add(TextFormatting.BLUE + "+" + lightningText + " Lightning Damage");
		}
		if (windText != 0)
		{
			if (windText == (int) windText)
				list.add(TextFormatting.BLUE + "+" + ((int) windText) + " Wind Damage");
			else list.add(TextFormatting.BLUE + "+" + windText + " Wind Damage");
		}
	}

	private List<String> addNormalInfo(ItemStack item)
	{
		List<String> list = new ArrayList<>();

		int fireDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDoT);
		int magicDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDoT);
		int witherDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDoT);
		int taintDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDoT);
		int armorPierce = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponArmorPiercing);
		int arrowSpeed = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BowArrowSpeed);
		int drawSpeed = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BowDrawSpeed);
		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponKnockback);
		int blind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponBlind);
		int slow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponSlow);
		int entangled = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponEntangled);
		int frostSlow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostSlow);
		int physDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		int fireDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		int magicDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		int witherDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);
		int divineDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponDivineDamage);
		int chaosDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponChaosDamage);
		int taintDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDamage);
		int frostDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostDamage);
		int holyDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponHolyDamage);
		int lightningDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponLightningDamage);
		int windDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWindDamage);

		int level = getLevel(item);
		if (level != 0)
			list.add("Level " + UtilityHelper.toRoman(level));

		if (fireDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDoT.name) + " " + UtilityHelper.toRoman(fireDot));
		if (magicDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDoT.name) + " " + UtilityHelper.toRoman(magicDot));
		if (witherDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDoT.name) + " " + UtilityHelper.toRoman(witherDot));
		if (taintDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDoT.name) + " " + UtilityHelper.toRoman(taintDot));
		if (armorPierce != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponArmorPiercing.name) + " " + UtilityHelper.toRoman(armorPierce));
		if (arrowSpeed != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.BowArrowSpeed.name) + " " + UtilityHelper.toRoman(arrowSpeed));
		if (drawSpeed != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.BowDrawSpeed.name) + " " + UtilityHelper.toRoman(drawSpeed));
		if (knockback != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponKnockback.name) + " " + UtilityHelper.toRoman(knockback));
		if (blind != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponBlind.name) + " " + UtilityHelper.toRoman(blind));
		if (slow != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponSlow.name) + " " + UtilityHelper.toRoman(slow));
		if (entangled != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponEntangled.name) + " " + UtilityHelper.toRoman(entangled));
		if (frostSlow != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostSlow.name) + " " + UtilityHelper.toRoman(frostSlow));
		if (physDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + " " + UtilityHelper.toRoman(physDamage));
		if (fireDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + " " + UtilityHelper.toRoman(fireDamage));
		if (magicDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + " " + UtilityHelper.toRoman(magicDamage));
		if (witherDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + " " + UtilityHelper.toRoman(witherDamage));
		if (divineDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponDivineDamage.name) + " " + UtilityHelper.toRoman(divineDamage));
		if (chaosDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponChaosDamage.name) + " " + UtilityHelper.toRoman(chaosDamage));
		if (taintDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDamage.name) + " " + UtilityHelper.toRoman(taintDamage));
		if (frostDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostDamage.name) + " " + UtilityHelper.toRoman(frostDamage));
		if (holyDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponHolyDamage.name) + " " + UtilityHelper.toRoman(holyDamage));
		if (lightningDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponLightningDamage.name) + " " + UtilityHelper.toRoman(lightningDamage));
		if (windDamage != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWindDamage.name) + " " + UtilityHelper.toRoman(windDamage));

		return list;
	}

	private List<String> addShiftInfo(ItemStack item)
	{
		List<String> list = new ArrayList<>();

		int fireDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDoT);
		int magicDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDoT);
		int witherDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDoT);
		int taintDot = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDoT);
		int armorPierce = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponArmorPiercing);
		int arrowSpeed = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BowArrowSpeed);
		int drawSpeed = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BowDrawSpeed);
		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponKnockback);
		int blind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponBlind);
		int slow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponSlow);
		int entangled = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponEntangled);
		int frostSlow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostSlow);
		int physDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		int fireDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		int magicDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		int witherDamage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);

		int level = getLevel(item);
		if (level != 0)
			list.add("Level " + UtilityHelper.toRoman(level));

		if (fireDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDoT.name) + ": Shots light enemies on fire for " + fireDot + " seconds.");
		if (magicDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDoT.name) + ": Shots give Poison " + UtilityHelper.toRoman(magicDot) + " for 5 seconds.");
		if (witherDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDoT.name) + ": Shots give Wither " + UtilityHelper.toRoman(witherDot) + " for 5 seconds.");
		if (taintDot != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDoT.name) + ": Shots taint enemies for " + taintDot + " seconds.");
		if (armorPierce != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponArmorPiercing.name) + ": Shots ignore " + armorPierce * 20 + "% of armor.");
		if (arrowSpeed != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.BowArrowSpeed.name) + ": Draw time and arrow speed increased by " + arrowSpeed * 5 + "%.");
		if (drawSpeed != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.BowDrawSpeed.name) + ": Draw time decreased by " + drawSpeed * 5 + "%.");
		if (knockback != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponKnockback.name) + ": Knock enemies away on hit.");
		if (blind != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponBlind.name) + ": Shots blind enemies for " + blind + " seconds.");
		if (slow != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponSlow.name) + ": Shots slow enemies for " + slow + " seconds.");
		if (entangled != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponEntangled.name) + ": Shots entangle enemies for " + entangled * 2 + " ticks.");
		if (frostSlow != 0)
			list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostSlow.name) + ": Shots heavily slow enemies for " + frostSlow + " seconds.");
		if (physDamage != 0)
		{
			if (ConfigHandler.isNormalDamagePercent)
				list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + ": Shots deal " + physDamage * ConfigHandler.normalDamageMulti * ConfigHandler.normalBowMulti * 100 + "% increased damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + ": Shots deal " + physDamage * ConfigHandler.normalDamageAmount * ConfigHandler.normalBowMulti + " extra damage.");
		}
		if (fireDamage != 0)
		{
			if (ConfigHandler.isFireDamagePercent)
				list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + ": Shots deal " + fireDamage * ConfigHandler.fireDamageMulti * ConfigHandler.fireBowMulti * 100 + "% more damage as fire damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + ": Shots deal " + fireDamage * ConfigHandler.fireDamageAmount * ConfigHandler.fireBowMulti + " extra damage as fire damage.");
		}
		if (magicDamage != 0)
		{
			if (ConfigHandler.isMagicDamagePercent)
				list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + ": Shots deal " + magicDamage * ConfigHandler.magicDamageMulti * ConfigHandler.magicBowMulti * 100 + "% more damage as magic damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + ": Shots deal " + magicDamage * ConfigHandler.magicDamageAmount * ConfigHandler.magicBowMulti + " extra damage as magic damage.");
		}
		if (witherDamage != 0)
		{
			if (ConfigHandler.isWitherDamagePercent)
				list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + ": Shots deal " + witherDamage * ConfigHandler.witherDamageMulti * ConfigHandler.witherBowMulti * 100 + "% more damage as wither damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + ": Shots deal " + witherDamage * ConfigHandler.witherDamageAmount * ConfigHandler.witherBowMulti + " extra damage as wither damage.");
		}

		return list;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack old, ItemStack newStack, boolean slotChanged)
	{
		return slotChanged;
	}
}