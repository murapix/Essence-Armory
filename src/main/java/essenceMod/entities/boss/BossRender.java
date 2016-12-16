package essenceMod.entities.boss;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import essenceMod.utility.Reference;

public class BossRender extends RenderLiving<EntityBoss>
{
	protected ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/entity/boss/base.png");
	
	public BossRender()
	{
		super(Minecraft.getMinecraft().getRenderManager(), new ModelBoss(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBoss boss)
	{
		return texture;
	}
}
