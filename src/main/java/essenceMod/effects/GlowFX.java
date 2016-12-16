package essenceMod.effects;

import java.util.ConcurrentModificationException;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import essenceMod.utility.Reference;

public class GlowFX extends Particle
{
	Random rand = new Random();
	public static final int LIFETIME = 10;
	public ResourceLocation texture = new ResourceLocation(Reference.MODID + ":" + Reference.SPRITES[0]);
	
	public GlowFX(World world, double x, double y, double z, double red, double green, double blue)
	{
		this(world, x, y, z, red, green, blue, LIFETIME);
	}
	
	public GlowFX(World world, double x, double y, double z, double red, double green, double blue, int maxAge)
	{
		this(world, x, y, z, 0, 0, 0, red, green, blue, maxAge);
	}
	
	public GlowFX(World world, double x, double y, double z, double vx, double vy, double vz, double red, double green, double blue)
	{
		this(world, x, y, z, vx, vy, vz, red, green, blue, LIFETIME);
	}
	
	public GlowFX(World world, double x, double y, double z, double vx, double vy, double vz, double red, double green, double blue, int maxAge)
	{
		super(world, x, y, z, vx, vy, vz);
		setRBGColorF((float) red / 255, (float) green / 255, (float) blue / 255);
		motionX = vx;
		motionY = vy;
		motionZ = vz;
		particleMaxAge = maxAge;
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		sprite.load(Minecraft.getMinecraft().getResourceManager(), texture);
		setParticleTexture(sprite);
		canCollide = false;
	}

	@Override
	public int getFXLayer()
	{
		return 1;
	}

	@Override
	public void onUpdate()
	{
		try
		{
			moveEntity(motionX, motionY, motionZ);
		}
		catch (ConcurrentModificationException e)
		{
			setExpired();
		}
		int random = rand.nextInt(4);
		if (random >= 2) particleAge++;
		if (particleAge > particleMaxAge)
		{
			setExpired();
		}
	}
}