package essenceMod.entities.boss.attacks;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.utility.Reference;
import essenceMod.utility.UtilityHelper;

public class MobSpawn extends BossAttack
{
	protected int number;
	protected int strength;
	
	public MobSpawn(EntityBoss boss)
	{
		super(boss);
	}
	
	public MobSpawn(EntityBoss boss, int number, int strength)
	{
		this(boss, number, strength, 100);
	}
	
	public MobSpawn(EntityBoss boss, int number, int strength, int attackTimer)
	{
		super(boss, null, 0, attackTimer, 0);
		this.number = number;
		this.strength = strength;
	}

	@Override
	public void update(List<EntityPlayer> players)
	{
		if (cooldown <= 0)
		{
			cooldown = attackTimer;
			EntityMob mob;
			World world = boss.worldObj;
			for (int i = 0; i < players.size(); i++)
			{
				EntityPlayer player = players.get(i);
				for (int j = 0; j < number; j++)
				{
					Random rand = new Random();
					float offsetX = (rand.nextFloat() * 2 + 3);
					float offsetZ = (rand.nextFloat() * 2 + 3);
					offsetX *= rand.nextInt(2) == 1 ? 1 : -1;
					offsetZ *= rand.nextInt(2) == 1 ? 1 : -1;
					switch(rand.nextInt(13))
					{
						case 0: // Zombie
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
							mob = new EntityZombie(world);
							mob.setPosition(player.posX + offsetX, player.posY + 2, player.posZ + offsetZ);
							world.spawnEntityInWorld(mob);
							mob.addPotionEffect(new PotionEffect(Potion.healthBoost.id, Integer.MAX_VALUE, strength * 5, false, false));
							mob.addPotionEffect(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, strength, false, false));
							mob.addPotionEffect(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 0, false, false));
							mob.setHealth(mob.getMaxHealth());
							mob.setAttackTarget(player);
							mob.setLastAttacker(player);
							break;
						case 8: // Skeleton, 90% normal, 10% wither
						case 9:
						case 10:
						case 11:
							mob = new EntitySkeleton(world);
							EntitySkeleton skele = (EntitySkeleton) mob;
							skele.setSkeletonType(rand.nextInt(10) == 0 ? 1 : 0);
							if (skele.getSkeletonType() == 0) skele.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
							if (skele.getSkeletonType() == 1) skele.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
							skele.setPosition(player.posX + offsetX, player.posY + 2, player.posZ + offsetZ);
							world.spawnEntityInWorld(skele);
							skele.addPotionEffect(new PotionEffect(Potion.healthBoost.id, Integer.MAX_VALUE, strength * 5, false, false));
							skele.addPotionEffect(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, strength, false, false));
							mob.addPotionEffect(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 0, false, false));
							mob.setHealth(mob.getMaxHealth());
							mob.setAttackTarget(player);
							mob.setLastAttacker(player);
							break;
						case 12: // Witch
							mob = new EntityWitch(world);
							mob.setPosition(player.posX + offsetX, player.posY + 2, player.posZ + offsetZ);
							world.spawnEntityInWorld(mob);
							mob.addPotionEffect(new PotionEffect(Potion.healthBoost.id, Integer.MAX_VALUE, strength * 5, false, false));
							mob.addPotionEffect(new PotionEffect(Potion.damageBoost.id, Integer.MAX_VALUE, strength, false, false));
							mob.setHealth(mob.getMaxHealth());
							mob.setAttackTarget(player);
							mob.setLastAttacker(player);
							break;
					}
				}
			}
		}
		else cooldown--;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawParticles()
	{}

	@Override
	public int getCrystalColor()
	{
		return UtilityHelper.rgbToHex(55, 94, 50);
	}

	@Override
	public void onBossDeath()
	{}

	@Override
	public NBTTagCompound writeAttackToNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Name", getName());
		compound.setInteger("Number", number);
		compound.setInteger("Strength", strength);
		compound.setInteger("Attack Timer", attackTimer);
		compound.setInteger("Cooldown", cooldown);
		return compound;
	}

	@Override
	public BossAttack readAttackFromNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		int number = compound.getInteger("Number");
		int strength = compound.getInteger("Strength");
		int attackTimer = compound.getInteger("Attack Timer");
		int cooldown = compound.getInteger("Cooldown");
		
		MobSpawn spawn = new MobSpawn(boss, number, strength, attackTimer);
		spawn.cooldown = cooldown;
		return spawn;
	}
	
	@Override
	public String getName()
	{
		return Reference.ATTACKS[1];
	}
}
