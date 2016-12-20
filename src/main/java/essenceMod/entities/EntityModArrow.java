package essenceMod.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import essenceMod.handlers.ConfigHandler;
import essenceMod.items.ItemModBow;
import essenceMod.registry.crafting.upgrades.Upgrade;
import essenceMod.registry.crafting.upgrades.UpgradeRegistry;

public class EntityModArrow extends EntityTippedArrow
{
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;
	protected int ticksInGround;
	protected int ticksInAir;
	protected double damage = 2.0D;
	protected int knockbackStrength;

	protected ItemStack bow;
	
	public static EntityArrow createArrow(World world, ItemStack bow, ItemStack potion, EntityLivingBase shooter)
	{
		EntityModArrow arrow = new EntityModArrow(world, shooter, bow);
		arrow.setPotionEffect(potion);
		return arrow;
	}
	
	public EntityModArrow(World world, EntityLivingBase entity, ItemStack bow)
	{
		super(world, entity);

		this.bow = bow;

		setDamage(getDamage() + (ItemModBow.getLevel(bow) / 5));
		float physDamage = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponPhysicalDamage);
		physDamage *= ConfigHandler.isNormalDamagePercent ? getDamage() * ConfigHandler.normalDamageMulti : ConfigHandler.normalDamageAmount;
		physDamage *= ConfigHandler.normalBowMulti;
		setDamage(getDamage() + physDamage);

		int punch = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponKnockback);
		if (punch > 0)
			setKnockbackStrength(punch);

		if (Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponFireDoT) > 0)
			setFire(100);
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn)
	{
		Entity entity = raytraceResultIn.entityHit;
		if (entity != null)
		{
			float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			int k = MathHelper.ceiling_double_int((double) f2 * getDamage());

			if (this.getIsCritical())
			{
				k += k / 4 + 1;
			}

			DamageSource source = null;

			if (this.shootingEntity == null)
				source = DamageSource.causeArrowDamage(this, this);
			else source = DamageSource.causeArrowDamage(this, this.shootingEntity);

			if (this.isBurning() && !(entity instanceof EntityEnderman))
			{
				int fire = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponFireDoT);
				entity.setFire(fire);
			}

			if (entity.attackEntityFrom(source, (float) k))
			{
				if (entity instanceof EntityLivingBase)
				{
					EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

					if (!this.worldObj.isRemote)
						entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);

					if (this.knockbackStrength > 0)
					{
						float f9 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

						if (f9 > 0.0F)
							entitylivingbase.addVelocity(this.motionX * (double) this.knockbackStrength * 0.6D / (double) f9, 0.1D, this.motionZ * (double) this.knockbackStrength * 0.6D / (double) f9);
					}

					if (this.shootingEntity instanceof EntityPlayer)
					{
						EntityPlayer player = (EntityPlayer) this.shootingEntity;
						float weaponDamage = k;
						DamageSource fireDamage = new EntityDamageSource("onFire", player);
						DamageSource magicDamage = new EntityDamageSource("magic", player);
						DamageSource witherDamage = new EntityDamageSource("wither", player);

						int pierce = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponArmorPiercing);
						int enemyArmor = entitylivingbase.getTotalArmorValue();
						if (enemyArmor >= 25)
							source = DamageSource.causeArrowDamage(this, player).setDamageBypassesArmor();

						float pierceMultiplier = ((1F / (1F - (entitylivingbase.getTotalArmorValue() * 0.04F)) - 1F) * pierce / 5F);
						if (pierce != 0)
						{
							entitylivingbase.hurtResistantTime = 0;
							entitylivingbase.attackEntityFrom(source, weaponDamage * pierceMultiplier);
						}

						float fire = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponFireDamage);
						fire *= ConfigHandler.isFireDamagePercent ? ConfigHandler.fireDamageMulti * weaponDamage : ConfigHandler.fireDamageAmount;
						fire *= ConfigHandler.fireBowMulti;
						if (fire != 0)
						{
							entitylivingbase.hurtResistantTime = 0;
							entitylivingbase.attackEntityFrom(fireDamage, Math.round(fire * 4) / 4F);
						}

						float magic = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponMagicDamage);
						magic *= ConfigHandler.isMagicDamagePercent ? ConfigHandler.magicDamageMulti * weaponDamage : ConfigHandler.magicDamageAmount;
						magic *= ConfigHandler.magicBowMulti;
						if (magic != 0)
						{
							entitylivingbase.hurtResistantTime = 0;
							entitylivingbase.attackEntityFrom(magicDamage, Math.round(magic * 4) / 4F);
						}

						float wither = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponWitherDamage);
						wither *= ConfigHandler.isWitherDamagePercent ? ConfigHandler.witherDamageMulti * weaponDamage : ConfigHandler.witherDamageAmount;
						wither *= ConfigHandler.witherBowMulti;
						if (wither != 0)
						{
							entitylivingbase.hurtResistantTime = 0;
							entitylivingbase.attackEntityFrom(witherDamage, Math.round(wither * 4) / 4F);
						}

						int poison = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponMagicDoT);
						if (poison != 0)
							entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.POISON, 25 * poison, poison - 1));

						int decay = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponWitherDoT);
						if (decay != 0)
							entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.WITHER, 25 * decay, decay - 1));

						int slow = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponSlow);
						if (slow != 0)
							entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 25 * slow, slow - 1));

						int blind = Upgrade.getUpgradeLevel(bow, UpgradeRegistry.WeaponBlind);
						if (blind != 0)
							entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 25 * slow));
					}

					if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
					{
						((EntityPlayerMP) this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
					}
				}

				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

				if (!(entity instanceof EntityEnderman))
				{
					this.setDead();
				}
			}
			else
			{
				this.motionX *= -0.10000000149011612D;
				this.motionY *= -0.10000000149011612D;
				this.motionZ *= -0.10000000149011612D;
				this.rotationYaw += 180.0F;
				this.prevRotationYaw += 180.0F;
				this.ticksInAir = 0;

				if (!this.worldObj.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D)
				{
					if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED)
					{
						this.entityDropItem(this.getArrowStack(), 0.1F);
					}

					this.setDead();
				}
			}
		}
		else
		{
			BlockPos blockpos = raytraceResultIn.getBlockPos();
			this.xTile = blockpos.getX();
			this.yTile = blockpos.getY();
			this.zTile = blockpos.getZ();
			IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
			this.inTile = iblockstate.getBlock();
			this.inData = this.inTile.getMetaFromState(iblockstate);
			this.motionX = (double) ((float) (raytraceResultIn.hitVec.xCoord - this.posX));
			this.motionY = (double) ((float) (raytraceResultIn.hitVec.yCoord - this.posY));
			this.motionZ = (double) ((float) (raytraceResultIn.hitVec.zCoord - this.posZ));
			float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
			this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
			this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
			if (!this.inGround)
				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			this.inGround = true;
			this.arrowShake = 7;
			this.setIsCritical(false);

			if (iblockstate.getMaterial() != Material.AIR)
			{
				this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos, iblockstate, this);
			}
		}
	}

}
