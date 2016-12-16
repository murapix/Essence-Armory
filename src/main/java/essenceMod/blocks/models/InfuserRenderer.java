package essenceMod.blocks.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import org.lwjgl.opengl.GL11;
import essenceMod.entities.tileEntities.TileEntityEssenceInfuser;

public class InfuserRenderer extends TileEntitySpecialRenderer<TileEntityEssenceInfuser>
{
	@Override
	public void renderTileEntityAt(TileEntityEssenceInfuser te, double x, double y, double z, float scale, int i)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 1.0, z + 0.5);
		if (te.getStackInSlot(0) != null)
		{
			EntityItem item = new EntityItem(Minecraft.getMinecraft().theWorld, x, y, z, te.getStackInSlot(0));
			item.hoverStart = 0;
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, true);
		}
		GL11.glPopMatrix();
	}
}
