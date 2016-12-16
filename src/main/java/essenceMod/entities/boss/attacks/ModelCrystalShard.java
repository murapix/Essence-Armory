package essenceMod.entities.boss.attacks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelCrystalShard extends ModelBase
{
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		GlStateManager.translate(0, -1, 0);
		GlStateManager.color(1, 1, 1);
		WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
		wr.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);

		wr.pos(0.25, 0.75, -0.5).tex(0.46875, 0.90625).normal(0.378F, 0.6547F, -0.6547F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(0.378F, 0.6547F, -0.6547F);
		wr.pos(0.466506, 0.75, -0.375).tex(0.5, 0.734375).normal(0.378F, 0.6547F, -0.6547F);

		wr.pos(0.466506, 0.75, -0.375).tex(0.5, 0.734375).normal(0.7559F, 0.6547F, 0.0F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(0.7559F, 0.6547F, 0.0F);
		wr.pos(0.466506, 0.75, -0.125).tex(0.40625, 0.578125).normal(0.7559F, 0.6547F, 0.0F);

		wr.pos(0.466506, 0.75, -0.125).tex(0.0, 0.734375).normal(0.378F, 0.6547F, 0.6547F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(0.378F, 0.6547F, 0.6547F);
		wr.pos(0.25, 0.75, 0.0).tex(0.015625, 0.90625).normal(0.378F, 0.6547F, 0.6547F);

		wr.pos(0.25, 0.75, 0.0).tex(0.015625, 0.90625).normal(-0.378F, 0.6547F, 0.6547F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(-0.378F, 0.6547F, 0.6547F);
		wr.pos(0.033494, 0.75, -0.125).tex(0.15625, 1.0).normal(-0.378F, 0.6547F, 0.6547F);

		wr.pos(0.033494, 0.75, -0.125).tex(0.15625, 1.0).normal(-0.7559F, 0.6547F, 0.0F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(-0.7559F, 0.6547F, 0.0F);
		wr.pos(0.033494, 0.75, -0.375).tex(0.34375, 1.0).normal(-0.7559F, 0.6547F, 0.0F);

		wr.pos(0.033494, 0.75, -0.375).tex(0.34375, 1.0).normal(-0.378F, 0.6547F, -0.6547F);
		wr.pos(0.25, 1.0, -0.25).tex(0.25, 0.765625).normal(-0.378F, 0.6547F, -0.6547F);
		wr.pos(0.25, 0.75, -0.5).tex(0.46875, 0.90625).normal(-0.378F, 0.6547F, -0.6547F);

		wr.pos(0.25, 0.75, -0.5).tex(0.3125, 0.578125).normal(0.4804F, -0.2774F, -0.8321F);
		wr.pos(0.466506, 0.75, -0.375).tex(0.15625, 0.515625).normal(0.4804F, -0.2774F, -0.8321F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(0.4804F, -0.2774F, -0.8321F);

		wr.pos(0.466506, 0.75, -0.375).tex(0.15625, 0.515625).normal(0.9608F, -0.2774F, 0.0F);
		wr.pos(0.466506, 0.75, -0.125).tex(0.0, 0.40625).normal(0.9608F, -0.2774F, 0.0F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(0.9608F, -0.2774F, 0.0F);

		wr.pos(0.466506, 0.75, -0.125).tex(0.890625, 0.3125).normal(0.4804F, -0.2774F, 0.8321F);
		wr.pos(0.25, 0.75, 0.0).tex(0.796875, 0.4375).normal(0.4804F, -0.2774F, 0.8321F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(0.4804F, -0.2774F, 0.8321F);

		wr.pos(0.25, 0.75, 0.0).tex(0.796875, 0.4375).normal(-0.4804F, -0.2774F, 0.8321F);
		wr.pos(0.033494, 0.75, -0.125).tex(0.65625, 0.53125).normal(-0.4804F, -0.2774F, 0.8321F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(-0.4804F, -0.2774F, 0.8321F);

		wr.pos(0.033494, 0.75, -0.125).tex(0.65625, 0.53125).normal(-0.9608F, -0.2774F, 0.0F);
		wr.pos(0.033494, 0.75, -0.375).tex(0.46875, 0.578125).normal(-0.9608F, -0.2774F, 0.0F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(-0.9608F, -0.2774F, 0.0F);

		wr.pos(0.033494, 0.75, -0.375).tex(0.46875, 0.578125).normal(-0.4804F, -0.2774F, -0.8321F);
		wr.pos(0.25, 0.75, -0.5).tex(0.3125, 0.578125).normal(-0.4804F, -0.2774F, -0.8321F);
		wr.pos(0.25, 0.0, -0.25).tex(0.40625, 0.0).normal(-0.4804F, -0.2774F, -0.8321F);

		Tessellator.getInstance().draw();

		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
}
