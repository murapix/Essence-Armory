package essenceMod.entities.boss.attacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.Constants.NBT;
import essenceMod.EssenceMod;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.utility.Reference;
import essenceMod.utility.UtilityHelper;

public class Flameblast extends BossAttack
{
	protected int delayTimer;
	protected int diameter;
	protected ArrayList<BlockPos> sources = new ArrayList<BlockPos>();
	
	public Flameblast(EntityBoss boss)
	{
		super(boss);
	}
	
	/**
	 * Generates the flameblast attack pattern with the default cooldown and delay. Default cooldown is 200 ticks. Default delay is 60 ticks. Suggested diameter of 9
	 * 
	 * @param boss
	 *            The boss entity using this attack pattern
	 * @param damageType
	 *            The damage type dealt by this attack pattern
	 * @param damage
	 *            The amount of damage done by a hit from this pattern
	 */
	public Flameblast(EntityBoss boss, float damage, int diameter)
	{
		this(boss, damage, diameter, 200, 60);
	}

	public Flameblast(EntityBoss boss, float damage, int diameter, int attackTimer, int duration)
	{
		super(boss, DamageSource.onFire, damage, attackTimer, duration);
		this.diameter = diameter;
	}

	@Override
	public void update(List<EntityPlayer> players)
	{
		if (cooldown <= 0)
		{
			if (delayTimer <= 0)
			{
				delayTimer = 1;
				sources = new ArrayList<BlockPos>();
				for (EntityPlayer player : players)
					sources.add(new BlockPos(player.getPosition().getX(), boss.getSource().getY(), player.getPosition().getZ()));
			}
			else
			{
				delayTimer++;
				if (delayTimer > duration)
				{
					delayTimer = 0;
					cooldown = attackTimer;
					for (EntityPlayer player : players)
					{
						for (BlockPos pos : sources)
						{
							double playerX = player.posX;
							double playerZ = player.posZ;
							double sourceX = pos.getX() + 0.5;
							double sourceZ = pos.getZ() + 0.5;
							double dist = Math.pow(playerX - sourceX, 2) + Math.pow(playerZ - sourceZ, 2);
							if (dist < Math.pow(diameter / 2.0, 2)) player.attackEntityFrom(damageType, damage);
						}
					}
				}
			}
		}
		else cooldown--;
	}

	@Override
	public void drawParticles()
	{
		Random rand = new Random();
		if (delayTimer > 0)
		{
			for (BlockPos source : sources)
			{
				double x = source.getX() + 0.5;
				double y = source.getY();
				double z = source.getZ() + 0.5;
				double maxRadius = diameter / 2D;
				double radiusPerTick = maxRadius / (duration - 20);
				double currentRadius = Math.min(radiusPerTick * delayTimer, maxRadius);
				double anglePerFlame = 1 / currentRadius;
				double offset = rand.nextDouble() * 2 * Math.PI;
				for (double angle = offset; angle < 2 * Math.PI + offset; angle += anglePerFlame)
				{
					double xPos = x + Math.cos(angle) * currentRadius;
					double zPos = z + Math.sin(angle) * currentRadius;
					EssenceMod.proxy.spawnParticle(EnumParticleTypes.FLAME, xPos, y, zPos, 0, 0, 0);
				}
			}
		}
		if (delayTimer == duration)
		{
			for (BlockPos source : sources)
			{
				double x = source.getX() + 0.5;
				double y = source.getY();
				double z = source.getZ() + 0.5;
				for (double radius = diameter / 2D; radius > 0; radius -= diameter / 16D)
				{
					double anglePerFlame = 1D / radius;
					double offset = rand.nextDouble() * 2 * Math.PI;
					for (double angle = 0; angle < 2 * Math.PI; angle += anglePerFlame)
					{
						double xPos = x + Math.cos(angle + offset) * radius;
						double zPos = z + Math.sin(angle + offset) * radius;
						double yPos = y;
						double yVel = boss.height / diameter * (diameter - (radius * 2)) + boss.height;
						yVel /= 20;
						EssenceMod.proxy.spawnParticle(EnumParticleTypes.FLAME, xPos, yPos, zPos, 0, yVel, 0);
					}
				}
			}
		}
	}

	public int getCrystalColor()
	{
		return UtilityHelper.rgbToHex(255, 0, 0);
	}

	@Override
	public void onBossDeath()
	{}

	@Override
	public NBTTagCompound writeAttackToNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Name", getName());
		compound.setFloat("Damage", damage);
		compound.setInteger("Diameter", diameter);
		compound.setInteger("Attack Timer", attackTimer);
		compound.setInteger("Duration", duration);
		compound.setInteger("Delay Timer", delayTimer);
		compound.setInteger("Cooldown", cooldown);
		
		NBTTagList sourceTag = new NBTTagList();
		for (BlockPos pos : sources)
		{
			NBTTagCompound posTag = new NBTTagCompound();
			posTag.setIntArray("source", new int[] { pos.getX(), pos.getY(), pos.getZ() });
			sourceTag.appendTag(posTag);
		}
		compound.setTag("Sources", sourceTag);
		return compound;
	}

	@Override
	public BossAttack readAttackFromNBT(NBTTagCompound compound)
	{
		float damage = compound.getFloat("Damage");
		int diameter= compound.getInteger("Diameter");
		int attackTimer = compound.getInteger("Attack Timer");
		int duration = compound.getInteger("Duration");
		int delayTimer = compound.getInteger("Delay Timer");
		int cooldown = compound.getInteger("Cooldown");
		
		ArrayList<BlockPos> sources = new ArrayList<BlockPos>();
		NBTTagList sourceTag = compound.getTagList("Sources", NBT.TAG_INT_ARRAY);
		for (int i = 0; i < sourceTag.tagCount(); i++)
		{
			int[] data = sourceTag.getIntArrayAt(i);
			sources.add(new BlockPos(data[0], data[1], data[2]));
		}

		Flameblast flameblast = new Flameblast(boss, damage, diameter, attackTimer, duration);
		flameblast.delayTimer = delayTimer;
		flameblast.cooldown = cooldown;
		
		return flameblast;
	}
	
	@Override
	public String getName()
	{
		return Reference.ATTACKS[0];
	}
}
