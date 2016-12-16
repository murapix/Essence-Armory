package essenceMod.entities.boss.attacks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;
import essenceMod.EssenceMod;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.utility.Reference;

public class WitherCloud extends BossAttack
{
	private HashMap<BlockPos, Integer> sources;
	private int witherStrength;
	private int slowStrength;
	private float radius;

	private int cooldown;

	private Random rand = new Random();

	public WitherCloud(EntityBoss boss)
	{
		super(boss);
	}
	
	public WitherCloud(EntityBoss boss, int witherStrength, int slowStrength, float radius, int attackTimer, int duration)
	{
		super(boss, null, 0, attackTimer, duration);
		this.witherStrength = witherStrength;
		this.slowStrength = slowStrength;
		this.radius = radius;
		sources = new HashMap<BlockPos, Integer>();
	}

	@Override
	public void update(List<EntityPlayer> players)
	{
		if (cooldown <= 0)
		{
			cooldown = attackTimer;
			int x = rand.nextInt(EntityBoss.ARENA_RANGE * 2) - EntityBoss.ARENA_RANGE + boss.getSource().getX();
			int y = boss.getSource().getY();
			int z = rand.nextInt(EntityBoss.ARENA_RANGE * 2) - EntityBoss.ARENA_RANGE + boss.getSource().getZ();
			BlockPos pos = new BlockPos(x, y, z);
			sources.put(pos, duration);
		}
		else cooldown--;

		Iterator<BlockPos> iter = sources.keySet().iterator();
		while (iter.hasNext())
		{
			BlockPos pos = iter.next();
			sources.replace(pos, sources.get(pos) - 1);
			for (EntityPlayer player : players)
			{
				if (player.getPosition().distanceSq(pos) < radius * radius)
				{
					if (witherStrength >= 0) player.addPotionEffect(new PotionEffect(Potion.wither.id, 60, witherStrength));
					if (slowStrength >= 0) player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, slowStrength));
				}
			}
			if (sources.get(pos) < 0) iter.remove();
		}
	}

	@Override
	public void drawParticles()
	{
		for (BlockPos pos : sources.keySet())
		{
			double x, y, z;
			do
			{
				x = rand.nextDouble() * radius * 2 - radius;
				y = rand.nextDouble();
				z = rand.nextDouble() * radius * 2 - radius;
			}
			while (x * x + z * z > radius);
			EssenceMod.proxy.spawnGlow(boss.worldObj, pos.getX() + x, pos.getY() + y, pos.getZ() + z, 0x3D, 0x2B, 0x1F, 32);
		}
	}

	@Override
	public void onBossDeath()
	{
		sources = new HashMap<BlockPos, Integer>();
	}

	@Override
	public int getCrystalColor()
	{
		return 0x3D2B1F;
	}

	@Override
	public NBTTagCompound writeAttackToNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Name", getName());
		compound.setInteger("Wither Strength", witherStrength);
		compound.setInteger("Slow Strength", slowStrength);
		compound.setFloat("Radius", radius);
		compound.setInteger("Attack Timer", attackTimer);
		compound.setInteger("Duration", duration);
		compound.setInteger("Cooldown", cooldown);

		NBTTagList sourceTag = new NBTTagList();
		for (BlockPos pos : sources.keySet())
		{
			NBTTagCompound posTag = new NBTTagCompound();
			posTag.setIntArray("source", new int[] { pos.getX(), pos.getY(), pos.getZ(), sources.get(pos) });
			sourceTag.appendTag(posTag);
		}
		compound.setTag("Sources", sourceTag);
		return compound;
	}

	@Override
	public BossAttack readAttackFromNBT(NBTTagCompound compound)
	{
		int witherStrength = compound.getInteger("Wither Strength");
		int slowStrength = compound.getInteger("Slow Strength");
		float radius = compound.getFloat("Radius");
		int attackTimer = compound.getInteger("Attack Timer");
		int duration = compound.getInteger("Duration");
		int cooldown = compound.getInteger("Cooldown");
		HashMap<BlockPos, Integer> sources = new HashMap<BlockPos, Integer>();
		NBTTagList sourceTag = compound.getTagList("Sources", NBT.TAG_INT_ARRAY);
		for (int i = 0; i < sourceTag.tagCount(); i++)
		{
			int[] data = sourceTag.getIntArrayAt(i);
			sources.put(new BlockPos(data[0], data[1], data[2]), data[3]);
		}

		WitherCloud cloud = new WitherCloud(boss, witherStrength, slowStrength, radius, attackTimer, duration);
		cloud.cooldown = cooldown;
		
		return cloud;
	}
	
	@Override
	public String getName()
	{
		return Reference.ATTACKS[3];
	}
}