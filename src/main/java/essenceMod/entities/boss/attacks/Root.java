package essenceMod.entities.boss.attacks;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import essenceMod.EssenceMod;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.utility.Reference;
import essenceMod.utility.UtilityHelper;

public class Root extends BossAttack
{
	protected int timer;
	protected List<EntityPlayer> players;
	
	public Root(EntityBoss boss)
	{
		super(boss);
	}
	
	public Root(EntityBoss boss, int attackTimer, int duration)
	{
		super(boss, null, 0, attackTimer, duration);
	}

	@Override
	public void update(List<EntityPlayer> players)
	{
		this.players = players;
		if (cooldown <= 0)
		{
			if (timer <= 0)
				timer = 1;
			else
			{
				timer++;
				for (EntityPlayer player : this.players)
					player.capabilities.setPlayerWalkSpeed(0);
				if (timer > duration)
				{
					timer = 0;
					cooldown = attackTimer;
					for (EntityPlayer player : this.players)
						player.capabilities.setPlayerWalkSpeed(0.1F);
				}
			}
		}
		else cooldown--;
	}

	@Override
	public void drawParticles()
	{
		if (cooldown <= 0)
		{
			for (EntityPlayer player : players)
			{
				double x = player.posX;
				double y = player.posY;
				double z = player.posZ;
				double anglePerParticle = Math.PI / 16;
				double offset = cooldown / 10 * Math.PI;
				double radius = 1;
				for (double angle = offset; angle < 2 * Math.PI + offset; angle += anglePerParticle)
				{
					double xPos = x + Math.cos(angle) * radius;
					double yPos = y + 1.5;
					double zPos = z + Math.sin(angle) * radius;
					EssenceMod.proxy.spawnGlow(boss.worldObj, xPos, yPos, zPos, 192, 5, 248, 10);
				}
			}
		}
		else if (cooldown < 20)
		{
			for (EntityPlayer player : players)
			{
				double x = player.posX;
				double y = player.posY;
				double z = player.posZ;
				double anglePerParticle = Math.PI / 16;
				double offset = cooldown / 10 * Math.PI;
				double radius = 1 + cooldown / 20;
				for (double angle = offset; angle < 2 * Math.PI + offset; angle += anglePerParticle)
				{
					double xPos = x + Math.cos(angle) * radius;
					double yPos = y + 1.5;
					double zPos = z + Math.sin(angle) * radius;
					EssenceMod.proxy.spawnGlow(boss.worldObj, xPos, yPos, zPos, 192, 5, 248, 1);
				}
			}
		}
	}

	@Override
	public int getCrystalColor()
	{
		return UtilityHelper.rgbToHex(192, 5, 248);
	}

	@Override
	public void onBossDeath()
	{
		for (EntityPlayer player : players)
			player.capabilities.setPlayerWalkSpeed(0.1F);
	}

	@Override
	public NBTTagCompound writeAttackToNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Name", getName());
		compound.setInteger("Attack Timer", attackTimer);
		compound.setInteger("Duration", duration);
		compound.setInteger("Timer", timer);
		compound.setInteger("Cooldown", cooldown);
		return compound;
	}

	@Override
	public BossAttack readAttackFromNBT(NBTTagCompound compound)
	{
		int attackTimer = compound.getInteger("Attack Timer");
		int duration = compound.getInteger("Duration");
		int timer = compound.getInteger("Timer");
		int cooldown = compound.getInteger("Cooldown");
		
		Root root = new Root(boss, attackTimer, duration);
		root.timer = timer;
		root.cooldown = cooldown;
		return root;
	}
	
	@Override
	public String getName()
	{
		return Reference.ATTACKS[2];
	}
}