package essenceMod.entities.boss.attacks;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import essenceMod.entities.boss.EntityBoss;

public class SelectionAttack extends BossAttack
{
	public SelectionAttack(EntityBoss boss)
	{
		super(boss);
	}

	@Override
	public void update(List<EntityPlayer> players)
	{
		
	}

	@Override
	public void drawParticles()
	{
		
	}

	@Override
	public void onBossDeath()
	{
		
	}

	@Override
	public int getCrystalColor()
	{
		return 0;
	}

	@Override
	public NBTTagCompound writeAttackToNBT()
	{
		return null;
	}

	@Override
	public BossAttack readAttackFromNBT(NBTTagCompound compound)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return null;
	}

}
