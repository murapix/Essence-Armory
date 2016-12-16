package essenceMod.registry;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.entities.boss.attacks.Flameblast;
import essenceMod.entities.boss.attacks.MobSpawn;
import essenceMod.entities.boss.attacks.Root;
import essenceMod.entities.boss.attacks.WitherCloud;
import essenceMod.utility.Reference;

public class AttackRegistry
{
	public static HashMap<String, Class> attacks = new HashMap<String, Class>();
	
	public static void init()
	{
		attacks.put(Reference.ATTACKS[0], Flameblast.class);
		attacks.put(Reference.ATTACKS[1], MobSpawn.class);
		attacks.put(Reference.ATTACKS[2], Root.class);
		attacks.put(Reference.ATTACKS[3], WitherCloud.class);
	}

	public static Constructor getAttack(String name)
	{
		try
		{
			return attacks.get(name).getConstructor(EntityBoss.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
