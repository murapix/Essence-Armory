package essenceMod.items.baubles;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import essenceMod.registry.crafting.InfuserRecipes;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;
import essenceMod.utility.UtilityHelper;

@SuppressWarnings("deprecation")
public class ItemBelt extends ItemBauble
{
	int level;
	public static final int numSubTypes = 17;
	// public IIcon[] icons = new IIcon[17];

	private final AttributeModifier health = new AttributeModifier(UUID.fromString("BD4FE64C-9E37-4391-9D21-88F273020B0F"), "EssenceArmoryHealthBoost", 0.5D, 2);
	private final AttributeModifier knockbackRes = new AttributeModifier(UUID.randomUUID(), "EssenceArmoryKnockbackResistance", 0.2, 0);

	public ItemBelt()
	{
		this(0);
	}

	public ItemBelt(int level)
	{
		super();
		this.level = level;
		MinecraftForge.EVENT_BUS.register(this);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < numSubTypes; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		for (int i = 0; i < numSubTypes; i++)
		{
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, i, new ModelResourceLocation(getRegistryName(), "inventory"));
		}
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack)
	{
		return BaubleType.BELT;
	}

	public static int getLevel(ItemStack item)
	{
		return item.getTagCompound().getInteger("Level");
	}

	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);
		int meta = item.getItemDamage();
		NBTTagCompound compound = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
		compound.setInteger("Level", 0);
		item.setTagCompound(compound);
		if (meta == 0) return;
		else if (meta <= 5) InfuserRecipes.addUpgrade(item, UpgradeRegistry.BeltCleave.setLevel(meta));
		else if (meta <= 10) InfuserRecipes.addUpgrade(item, UpgradeRegistry.BeltKnockback.setLevel(meta - 5));
		else if (meta <= 15) InfuserRecipes.addUpgrade(item, UpgradeRegistry.BaubleHealthBoost.setLevel(meta - 10));
		else if (meta == 16) InfuserRecipes.addUpgrade(item, UpgradeRegistry.BaubleMiningLimiter.setLevel(1));
	}

	@Override
	public void onWornTick(ItemStack item, EntityLivingBase player)
	{
		if (!item.hasTagCompound()) onCreated(item, player.worldObj, (EntityPlayer) player);
		NBTTagCompound compound = item.getTagCompound();
		int cooldown = compound.getInteger("Cooldown");
		cooldown = (cooldown > 0) ? cooldown - 1 : 0;
		compound.setInteger("Cooldown", cooldown);
		item.setTagCompound(compound);
	}

	@Override
	public void onUnequipped(ItemStack item, EntityLivingBase player)
	{
		super.onUnequipped(item, player);
		if (player instanceof EntityPlayer)
		{
			IAttributeInstance attribute = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
			if (attribute != null) attribute.removeModifier(health);
			attribute = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			if (attribute != null) attribute.removeModifier(knockbackRes);
		}
	}

	@SubscribeEvent
	public void updatePlayerHealth(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack belt = BaublesApi.getBaubles(player).getStackInSlot(3);
			if (belt != null && belt.getItem() instanceof ItemBelt)
			{
				IAttributeInstance attribute = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
				if (attribute != null)
				{
					attribute.removeModifier(health);
					int level = Upgrade.getUpgradeLevel(belt, UpgradeRegistry.BaubleHealthBoost);
					attribute.applyModifier(new AttributeModifier(health.getID(), health.getName() + level, health.getAmount() * level, health.getOperation()));
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event)
	{
		EntityPlayer attacker = event.getEntityPlayer();
		ItemStack belt = BaublesApi.getBaubles(attacker).getStackInSlot(3);
		if (belt != null && belt.getItem() instanceof ItemBelt)
		{
			int level = Upgrade.getUpgradeLevel(belt, UpgradeRegistry.BeltCleave);
			if (level != 0 && event.getTarget() instanceof EntityLivingBase)
			{
				EntityLivingBase target = (EntityLivingBase) event.getTarget();
				World world = target.worldObj;
				AxisAlignedBB axis = new AxisAlignedBB(target.posX - level, target.posY - 1, target.posZ - level, target.posX + level, target.posY + 1, target.posZ + level);
				List<Entity> list;
				if (target instanceof EntityMob) list = world.getEntitiesWithinAABB(EntityMob.class, axis);
				else if (target instanceof EntityAmbientCreature) list = world.getEntitiesWithinAABB(EntityAmbientCreature.class, axis);
				else if (target instanceof EntityPlayer) list = world.getEntitiesWithinAABB(EntityPlayer.class, axis);
				else list = world.getEntitiesWithinAABB(EntityLivingBase.class, axis);
				list.remove(attacker);
				list.remove(target);

				ItemStack weapon = attacker.getHeldItem(attacker.getActiveHand());
				double damage = 0;
				Iterator<AttributeModifier> iterator = weapon.getItem().getAttributeModifiers(EntityEquipmentSlot.MAINHAND, weapon).get(SharedMonsterAttributes.ATTACK_DAMAGE.getAttributeUnlocalizedName()).iterator();
				while (iterator.hasNext())
					damage += ((AttributeModifier) iterator.next()).getAmount();
				for (Object obj : list)
				{
					EntityLivingBase entity = (EntityLivingBase) obj;
					double xDiff = Math.abs(target.posX - entity.posX);
					double zDiff = Math.abs(target.posZ - entity.posZ);
					int distance = MathHelper.floor_double(Math.max(xDiff, zDiff));
					entity.attackEntityFrom(DamageSource.causePlayerDamage(attacker), (float) damage * 0.2F * (level - distance));
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onBlockBreak(BreakSpeed event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack belt = BaublesApi.getBaubles(player).getStackInSlot(3);
		if (belt != null && belt.getItem() instanceof ItemBelt && Upgrade.getUpgradeLevel(belt, UpgradeRegistry.BaubleMiningLimiter) != 0)
		{
			float hardness = event.getState().getBlockHardness(player.worldObj, event.getPos());
			float blockHealth = hardness * 30;
			event.setNewSpeed(Math.min(event.getNewSpeed(), blockHealth - 1));
		}
	}

	@SubscribeEvent
	public void updateKnockbackRes(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack belt = BaublesApi.getBaubles(player).getStackInSlot(3);
			if (belt != null && belt.getItem() instanceof ItemBelt)
			{
				IAttributeInstance attribute = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
				if (attribute != null)
				{
					attribute.removeModifier(knockbackRes);
					int level = Upgrade.getUpgradeLevel(belt, UpgradeRegistry.BeltKnockback);
					attribute.applyModifier(new AttributeModifier(knockbackRes.getID(), knockbackRes.getName() + level, knockbackRes.getAmount() * level, knockbackRes.getOperation()));
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer entityPlayer, List<String> list, boolean bool)
	{
		int level = 0;
		if (!item.hasTagCompound()) onCreated(item, entityPlayer.worldObj, entityPlayer);

		level = item.getTagCompound().getInteger("Level");
		if (level != 0) list.add("Level " + UtilityHelper.toRoman(level));

		int cleave = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BeltCleave);
		int knockback = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BeltKnockback);
		int miningLimit = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BaubleMiningLimiter);
		int healthBoost = Upgrade.getUpgradeLevel(item, UpgradeRegistry.BaubleHealthBoost);

		if (cleave != 0) list.add(I18n.translateToLocal(UpgradeRegistry.BeltCleave.name) + " " + UtilityHelper.toRoman(cleave));
		if (knockback != 0) list.add(I18n.translateToLocal(UpgradeRegistry.BeltKnockback.name) + " " + UtilityHelper.toRoman(knockback));
		if (miningLimit != 0) list.add(I18n.translateToLocal(UpgradeRegistry.BaubleMiningLimiter.name));
		if (healthBoost != 0) list.add(I18n.translateToLocal(UpgradeRegistry.BaubleHealthBoost.name) + " " + UtilityHelper.toRoman(healthBoost));
		if (knockback != 0) list.add(TextFormatting.BLUE + "+" + UtilityHelper.round(knockback * 0.2F, 1) + " Knockback Resistance");
	}
}
