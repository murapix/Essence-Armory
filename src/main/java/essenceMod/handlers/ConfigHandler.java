package essenceMod.handlers;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler
{
	//Config Categories
	public static final String CATEGORY_BASIC = "Essence Shard Basic Settings";
	public static final String CATEGORY_DROPS = "Essence Shard Drop Settings";
	public static final String CATEGORY_WEAPON = "Essence Infused Weapon Upgrade Settings";
	public static final String CATEGORY_ARMOR = "Essence Infused Armor Upgrade Setting";
	public static final String CATEGORY_COMPAT = "Mod Compatibility Settings";
	public static final String CATEGORY_BAUBLES = "Essence Infused Trinkets Settings";
	public static final String CATEGORY_TRAVELGEAR = "Essence Infused Traveller's Gear Settings";
	public static final String CATEGORY_TINKERS = "Tinkers' Construct Compatibility Settings";
	
	//CATEGORY_BASIC variables
	public static int shardEnchantID = 70;
	
	//CATEGORY_DROPS variables
	public static boolean useWhiteList;
	public static String[] whiteList;
	public static boolean useBlackList;
	public static String[] blackList;
	public static float dragonShardChance;
	public static int dragonShardCount;
	public static float witherShardChance;
	public static int witherShardCount;
	
	//CATEGORY_SWORD variables
	public static boolean isNormalDamagePercent;
	public static float normalDamageMulti;
	public static int normalDamageAmount;
	public static float normalBowMulti;
	public static boolean isFireDamagePercent;
	public static float fireDamageMulti;
	public static int fireDamageAmount;
	public static float fireBowMulti;
	public static boolean isMagicDamagePercent;
	public static float magicDamageMulti;
	public static int magicDamageAmount;
	public static float magicBowMulti;
	public static boolean isWitherDamagePercent;
	public static float witherDamageMulti;
	public static int witherDamageAmount;
	public static float witherBowMulti;
	
	//CATEGORY_ARMOR variables
	public static float thornsDamage;
	public static int poisonThornsDuration;
	public static int blindThornsDuration;
	public static int blindThornsChance;
	public static int healthBoostCount;
	public static int absorptionCount;
	public static int absorptionDelay;
	public static int absorptionRecharge = 20;
	public static float maxProtectionValue;
	
	//CATEGORY_BAUBLES variables
	
	public static void initProps(File location)
	{
		Configuration config = new Configuration(location);
		config.load();
		
		useWhiteList = config.getBoolean("useWhiteList", CATEGORY_DROPS, false, "If set to true, only the listed mobs will drop Essence Shards. Default: false");
		whiteList = config.getStringList("whiteList", CATEGORY_DROPS, new String[]{""}, "The array of mobs that can drop Essence Shards, separated with a ,");
		useBlackList = config.getBoolean("useBlackList", CATEGORY_DROPS, false, "If set to true, the following listed mobs will NOT drop Essence Shards. Default: false");
		blackList = config.getStringList("blackList", CATEGORY_DROPS, new String[]{""}, "The array of mobs that will NOT drop Essence Shards, separated with a ,");
		dragonShardChance = config.getFloat("dragonShardChance", CATEGORY_DROPS, 0, 0, 1, "The chance that the Ender Dragon will drop Essence Shards. Default: 0");
		dragonShardCount = config.getInt("dragonShardCount", CATEGORY_DROPS, 64, 0, Integer.MAX_VALUE, "The base number of Essence Shards the Ender Dragon can drop when slain. Default: 64");
		witherShardChance = config.getFloat("witherShardChance", CATEGORY_DROPS, 0, 0, 1, "The chance that the Wither will drop Essence Shards. Default: 0");
		witherShardCount = config.getInt("witherShardCount", CATEGORY_DROPS, 16, 0, Integer.MAX_VALUE, "The base number of Essence Shards the Wither can drop when slain. Default: 16");
		
		isNormalDamagePercent = config.getBoolean("isNormalDamagePercent", CATEGORY_WEAPON, true, "If set to true, the normal damage upgrade adds a percentage of the total damage as plain damage. If set to false, it adds a flat amount. Default: true");
		normalDamageMulti = config.getFloat("normalDamageMulti", CATEGORY_WEAPON, 0.05F, 0, Float.MAX_VALUE, "The percentage of the total damage added as normal damage per level of the normal damage upgrade. Default: 0.05");
		normalDamageAmount = config.getInt("normalDamageAmount", CATEGORY_WEAPON, 1, 0, Integer.MAX_VALUE, "The amount of damage gained per level of the normal damage upgrade. Default: 1");
		normalBowMulti = config.getFloat("normalBowMulti", CATEGORY_WEAPON, 1, 0, Float.MAX_VALUE, "The effectiveness of physical damage upgrades on the bow. Default: 1");
		isFireDamagePercent = config.getBoolean("isFireDamagePercent", CATEGORY_WEAPON, true, "If set to true, the fire damage upgrade adds a percentage of the total damage as fire damage. If set to false, it adds a flat amount. Default: true");
		fireDamageMulti = config.getFloat("fireDamageMulti", CATEGORY_WEAPON, 0.05F, 0, Float.MAX_VALUE, "The percentage of the total damage added as fire damage per level of the fire damage upgrade. Default: 0.05");
		fireDamageAmount = config.getInt("fireDamageAmount", CATEGORY_WEAPON, 1, 0, Integer.MAX_VALUE, "The amount of damage gained per level of the fire damage upgrade. Default: 1");
		fireBowMulti = config.getFloat("fireBowMulti", CATEGORY_WEAPON, 1, 0, Float.MAX_VALUE, "The effectiveness of fire damage upgrades on the bow. Default: 1");
		isMagicDamagePercent = config.getBoolean("isMagicDamagePercent", CATEGORY_WEAPON, true, "If set to true, the magic damage upgrade adds a percentage of the total damage as magic damage. If set to false, it adds a flat amount. Default: true");
		magicDamageMulti = config.getFloat("magicDamageMulti", CATEGORY_WEAPON, 0.05F, 0, Float.MAX_VALUE, "The percentage of the total damage added as magic damage per level of the magic damage upgrade. Default: 0.05");
		magicDamageAmount = config.getInt("magicDamageAmount", CATEGORY_WEAPON, 1, 0, Integer.MAX_VALUE, "The amount of damage gained per level of the magic damage upgrade. Default: 1");
		magicBowMulti = config.getFloat("magicBowMulti", CATEGORY_WEAPON, 1, 0, Float.MAX_VALUE, "The effectiveness of magic damage upgrades on the bow. Default: 1");
		isWitherDamagePercent = config.getBoolean("isWitherDamagePercent", CATEGORY_WEAPON, true, "If set to true, the wither damage upgrade adds a percentage of the total damage as wither damage. If set to false, it adds a flat amount. Default: true");
		witherDamageMulti = config.getFloat("witherDamageMulti", CATEGORY_WEAPON, 0.05F, 0, Float.MAX_VALUE, "The percentage of the total damage added as wither damage per level of the wither damage upgrade. Default: 0.05");
		witherDamageAmount = config.getInt("witherDamageAmount", CATEGORY_WEAPON, 1, 0, Integer.MAX_VALUE, "The amount of damage gained per level of the wither damage upgrade. Default: 1");
		witherBowMulti = config.getFloat("witherBowMulti", CATEGORY_WEAPON, 1, 0, Float.MAX_VALUE, "The effectiveness of wither damage upgrades on the bow. Default: 1");
		
		thornsDamage = config.getFloat("thornsDamage", CATEGORY_ARMOR, 0.25F, 0, Float.MAX_VALUE, "The amount of damage the thorns armor upgrade deals per level. Default: 0.25");
		poisonThornsDuration = config.getInt("poisonThornsDuration", CATEGORY_ARMOR, 20, 0, Integer.MAX_VALUE, "The duration in ticks for which the poisonous armor upgrade adds per level. Remember, 20 ticks is 1 second. Default: 10");
		blindThornsDuration = config.getInt("blindThornsDuration", CATEGORY_ARMOR, 10, 0, Integer.MAX_VALUE, "The duration in ticks for which the blinding armor upgrade adds per level. Rememeber, 20 ticks is 1 second. Default: 10");
		blindThornsChance = config.getInt("blindThornsChance", CATEGORY_ARMOR, 25, 0, 100, "The chance each piece of armor with the blinding armor upgrade adds to blind the enemy. Default: 25 (x4 is a 100% chance)");
		healthBoostCount = config.getInt("healthBoostCount", CATEGORY_ARMOR, 1, 1, Integer.MAX_VALUE, "The amount of health the health boost upgrade adds per level. Remember, each heart is two health. Default: 2");
		absorptionCount = config.getInt("absorptionCount", CATEGORY_ARMOR, 1, 1, Integer.MAX_VALUE, "The amount of temporary health the absorption upgrade adds per level. Remember, each heart is two health. Default: 2");
		absorptionDelay = config.getInt("absorptionDelay", CATEGORY_ARMOR, 200, 0, Integer.MAX_VALUE, "The number of ticks without taking any damage required for absorption hearts to return. Remember, 20 ticks is 1 second. Default: 200");
		maxProtectionValue = config.getFloat("maxProtectionValue", CATEGORY_ARMOR, 0.5F, 0, 1, "The percent of damage reduction gained from a full armor set with specialized protection. The protection upgrade gives 40% of this value, and the specialized protection upgrades give 60%.");
		
		config.save();
	}
}