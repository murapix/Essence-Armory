package essenceMod.items;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import essenceMod.handlers.ConfigHandler;
import essenceMod.registry.ModArmory;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;
import essenceMod.tabs.ModTabs;
import essenceMod.utility.UtilityHelper;

@SuppressWarnings("deprecation")
public class ItemModSword extends ItemSword implements IUpgradeable
{
	public final ToolMaterial toolMaterial;
	protected float weaponDamage;

	int level;

	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean b)
	{
		if (!item.hasTagCompound()) onCreated(item, world, (EntityPlayer) entity);
		int level = item.getTagCompound().getInteger("Level");
		float damage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		weaponDamage = 4.0F + toolMaterial.getDamageVsEntity() + level;
		damage *= ConfigHandler.isNormalDamagePercent ? ConfigHandler.normalDamageMulti * weaponDamage : ConfigHandler.normalDamageAmount;
		weaponDamage += damage;
		NBTTagCompound compound = item.getTagCompound();
		compound.setFloat("weaponDamage", weaponDamage);
		item.setTagCompound(compound);
	}

	public ItemModSword(ToolMaterial material)
	{
		super(material);
		toolMaterial = material;
		setCreativeTab(ModTabs.tabEssence);
		setMaxDamage(0);
		level = 0;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack item)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (slot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", item.getTagCompound().getFloat("weaponDamage"), 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4, 0));
		}
		return multimap;
	}

	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer entityPlayer)
	{
		NBTTagCompound compound = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
		float damage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		weaponDamage = 4.0F + toolMaterial.getDamageVsEntity() + level;
		damage *= ConfigHandler.isNormalDamagePercent ? ConfigHandler.normalDamageMulti * weaponDamage : ConfigHandler.normalDamageAmount;
		weaponDamage += damage;
		compound.setInteger("Level", level);
		compound.setFloat("weaponDamage", weaponDamage);
		item.setTagCompound(compound);
		item.addEnchantment(ModArmory.shardLooter, 1);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean hitEntity(ItemStack item, EntityLivingBase enemy, EntityLivingBase player)
	{
		super.hitEntity(item, enemy, player);

		DamageSource playerDamage = new EntityDamageSource("player", player);
		DamageSource fireDamage = new EntityDamageSource("onFire", player).setDamageBypassesArmor().setFireDamage();
		DamageSource witherDamage = new EntityDamageSource("wither", player).setDamageBypassesArmor();
		DamageSource magicDamage = new EntityDamageSource("magic", player).setDamageBypassesArmor().setMagicDamage();

		float weaponDamage = item.getTagCompound().getFloat("weaponDamage");

		int pierce = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponArmorPiercing);
		int enemyArmor = enemy.getTotalArmorValue();
		if (enemyArmor >= 25) playerDamage = new EntityDamageSource("player", player).setDamageBypassesArmor();

		float pierceMultiplier = ((1F / (1F - (enemy.getTotalArmorValue() * 0.04F)) - 1F) * pierce / 5F);
		if (pierce != 0)
		{
			enemy.hurtResistantTime = 0;
			enemy.attackEntityFrom(playerDamage, weaponDamage * pierceMultiplier);
		}

		float fire = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		fire *= ConfigHandler.isFireDamagePercent ? ConfigHandler.fireDamageMulti * weaponDamage : ConfigHandler.fireDamageAmount;
		if (fire != 0)
		{
			enemy.hurtResistantTime = 0;
			enemy.attackEntityFrom(fireDamage, Math.round(fire * 4) / 4F);
		}

		float wither = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);
		wither *= ConfigHandler.isWitherDamagePercent ? ConfigHandler.witherDamageMulti * weaponDamage : ConfigHandler.witherDamageAmount;
		if (wither != 0)
		{
			enemy.hurtResistantTime = 0;
			enemy.attackEntityFrom(witherDamage, Math.round(wither * 4) / 4F);
		}

		float magic = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		magic *= ConfigHandler.isMagicDamagePercent ? ConfigHandler.magicDamageMulti * weaponDamage : ConfigHandler.magicDamageAmount;
		if (magic != 0)
		{
			enemy.hurtResistantTime = 0;
			enemy.attackEntityFrom(magicDamage, Math.round(magic * 4) / 4F);
		}

		int poison = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDoT);
		if (poison != 0) enemy.addPotionEffect(new PotionEffect(MobEffects.POISON, 25 * poison, poison - 1));

		int burn = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDoT);
		if (burn != 0) enemy.setFire(burn);

		int decay = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDoT);
		if (decay != 0) enemy.addPotionEffect(new PotionEffect(MobEffects.WITHER, 25 * decay, decay - 1));

		int lifesteal = Upgrade.getUpgradeLevel(item, UpgradeRegistry.SwordLifesteal);
		if (lifesteal != 0) player.heal(lifesteal * 0.25F);

		int slow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponSlow);
		if (slow != 0) enemy.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 25 * slow, slow - 1));

		int blind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponBlind);
		if (blind != 0) enemy.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 25 * slow));

		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponKnockback);
		if (knockback != 0) enemy.knockBack(player, weaponDamage, (player.posX - enemy.posX) * knockback, (player.posZ - enemy.posZ) * knockback);

		return true;
	}

	public static int getLevel(ItemStack item)
	{
		return item.getTagCompound().getInteger("Level");
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer entityPlayer, List<String> list, boolean bool)
	{
		if (!item.hasTagCompound()) onCreated(item, entityPlayer.worldObj, entityPlayer);
		if (GuiScreen.isShiftKeyDown()) list.addAll(addShiftInfo(item));
		else list.addAll(addNormalInfo(item));
	}

	private List<String> addNormalInfo(ItemStack item)
	{
		List<String> list = new ArrayList<>();

		int level = ItemModSword.getLevel(item);
		int burn = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDoT);
		int poison = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDoT);
		int decay = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDoT);
		int flux = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDoT);
		int lifesteal = Upgrade.getUpgradeLevel(item, UpgradeRegistry.SwordLifesteal);
		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponKnockback);
		int blind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponBlind);
		int slow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponSlow);
		int entangle = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponEntangled);
		int frostSlow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostSlow);
		int pierce = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponArmorPiercing);
		int damage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		int magic = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		int fire = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		int wither = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);
		int chaos = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponChaosDamage);
		int divine = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponDivineDamage);
		int taint = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDamage);
		int frost = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostDamage);
		int holy = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponHolyDamage);
		int lightning = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponLightningDamage);
		int wind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWindDamage);

		if (level != 0)
		{
			list.add("Hold SHIFT for more information");
			list.add("Level: " + UtilityHelper.toRoman(level));
		}
		if (burn != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDoT.name) + " " + UtilityHelper.toRoman(burn));
		if (poison != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDoT.name) + " " + UtilityHelper.toRoman(poison));
		if (decay != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDoT.name) + " " + UtilityHelper.toRoman(decay));
		if (flux != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDoT.name) + " " + UtilityHelper.toRoman(flux));
		if (lifesteal != 0) list.add(I18n.translateToLocal(UpgradeRegistry.SwordLifesteal.name) + " " + UtilityHelper.toRoman(lifesteal));
		if (knockback != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponKnockback.name) + " " + UtilityHelper.toRoman(knockback));
		if (blind != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponBlind.name) + " " + UtilityHelper.toRoman(blind));
		if (slow != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponSlow.name) + " " + UtilityHelper.toRoman(slow));
		if (entangle != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponEntangled.name) + " " + UtilityHelper.toRoman(entangle));
		if (frostSlow != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostSlow.name) + " " + UtilityHelper.toRoman(frostSlow));
		if (pierce != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponArmorPiercing.name) + " " + UtilityHelper.toRoman(pierce));
		if (damage != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + " " + UtilityHelper.toRoman(damage));
		if (magic != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + " " + UtilityHelper.toRoman(magic));
		if (fire != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + " " + UtilityHelper.toRoman(fire));
		if (wither != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + " " + UtilityHelper.toRoman(wither));
		if (chaos != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponChaosDamage.name) + " " + UtilityHelper.toRoman(chaos));
		if (divine != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponDivineDamage.name) + " " + UtilityHelper.toRoman(divine));
		if (taint != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDamage.name) + " " + UtilityHelper.toRoman(taint));
		if (frost != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostDamage.name) + " " + UtilityHelper.toRoman(frost));
		if (holy != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponHolyDamage.name) + " " + UtilityHelper.toRoman(holy));
		if (lightning != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponLightningDamage.name) + " " + UtilityHelper.toRoman(lightning));
		if (wind != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWindDamage.name) + " " + UtilityHelper.toRoman(wind));

		return list;
	}

	private List<String> addShiftInfo(ItemStack item)
	{
		List<String> list = new ArrayList<>();

		int level = ItemModSword.getLevel(item);
		int burn = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDoT);
		int poison = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDoT);
		int decay = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDoT);
		int flux = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponTaintDoT);
		int lifesteal = Upgrade.getUpgradeLevel(item, UpgradeRegistry.SwordLifesteal);
		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponKnockback);
		int blind = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponBlind);
		int slow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponSlow);
		int entangle = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponEntangled);
		int frostSlow = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFrostSlow);
		int pierce = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponArmorPiercing);
		int damage = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponPhysicalDamage);
		int magic = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponMagicDamage);
		int fire = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponFireDamage);
		int wither = Upgrade.getUpgradeLevel(item, UpgradeRegistry.WeaponWitherDamage);

		if (level != 0) list.add("Level: " + UtilityHelper.toRoman(level));
		if (burn != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDoT.name) + ": Attacks light enemies on fire for " + burn + " seconds.");
		if (poison != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDoT.name) + ": Attacks give Poison " + UtilityHelper.toRoman(poison) + " for 5 seconds.");
		if (decay != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDoT.name) + ": Attacks give Wither " + UtilityHelper.toRoman(decay) + " for 5 seconds.");
		if (flux != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponTaintDoT.name) + ": Attacks taint enemies for " + flux + " seconds.");
		if (lifesteal != 0) list.add(I18n.translateToLocal(UpgradeRegistry.SwordLifesteal.name) + ": Gain " + UtilityHelper.round(lifesteal * 0.25F, 2) + " hearts per attack.");
		if (knockback != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponKnockback.name) + ": Knock enemies away on hit.");
		if (blind != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponBlind.name) + ": Attacks blind enemies for " + blind + " seconds.");
		if (slow != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponSlow.name) + ": Attacks slow enemies for " + slow + " seconds.");
		if (entangle != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponEntangled.name) + ": Attacks entangle enemies for " + entangle * 2 + " ticks.");
		if (frostSlow != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFrostSlow.name) + ": Attacks heavily slow enemies for " + frostSlow + " seconds.");
		if (pierce != 0) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponArmorPiercing.name) + ": Attacks ignore " + pierce * 20 + "% of armor.");
		if (damage != 0)
		{
			if (ConfigHandler.isNormalDamagePercent) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + ": Attacks deal " + damage * ConfigHandler.normalDamageMulti * 100 + "% increased damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponPhysicalDamage.name) + ": Attacks deal " + damage * ConfigHandler.normalDamageAmount + " extra damage.");
		}
		if (magic != 0)
		{
			if (ConfigHandler.isMagicDamagePercent) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + ": Attacks deal " + magic * ConfigHandler.magicDamageMulti * 100 + "% more damage as magic damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponMagicDamage.name) + ": Attacks deal " + magic * ConfigHandler.magicDamageAmount + " extra damage as magic damage.");
		}
		if (fire != 0)
		{
			if (ConfigHandler.isFireDamagePercent) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + ": Attacks deal " + fire * ConfigHandler.fireDamageMulti * 100 + "% more damage as fire damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponFireDamage.name) + ": Attacks deal " + fire * ConfigHandler.fireDamageAmount + " extra damage as fire damage.");
		}
		if (wither != 0)
		{
			if (ConfigHandler.isWitherDamagePercent) list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + ": Attacks deal " + wither * ConfigHandler.witherDamageMulti * 100 + "% more damage as wither damage.");
			else list.add(I18n.translateToLocal(UpgradeRegistry.WeaponWitherDamage.name) + ": Attacks deal " + wither * ConfigHandler.witherDamageAmount + " extra damage as wither damage.");
		}
		return list;
	}
}