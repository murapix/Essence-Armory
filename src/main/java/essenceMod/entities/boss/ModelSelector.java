package essenceMod.entities.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelSelector extends ModelBase
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
		
		wr.pos(0.5, 0.5, -1.0).tex(0.65625, 0.359375).normal(0.5F, 0.0F, -0.866F);
		wr.pos(0.5, 1.5, -1.0).tex(0.65625, 0.0).normal(0.5F, 0.0F, -0.866F);
		wr.pos(0.933013, 1.5, -0.75).tex(0.828125, 0.0).normal(0.5F, 0.0F, -0.866F);

		wr.pos(0.933013, 0.5, -0.75).tex(0.828125, 0.359375).normal(1.0F, 0.0F, 0.0F);
		wr.pos(0.933013, 1.5, -0.75).tex(0.828125, 0.0).normal(1.0F, 0.0F, 0.0F);
		wr.pos(0.933013, 1.5, -0.25).tex(1.0, 0.0).normal(1.0F, 0.0F, 0.0F);

		wr.pos(0.933013, 0.5, -0.25).tex(0.0, 0.359375).normal(0.5F, 0.0F, 0.866F);
		wr.pos(0.933013, 1.5, -0.25).tex(0.0, 0.0).normal(0.5F, 0.0F, 0.866F);
		wr.pos(0.5, 1.5, 0.0).tex(0.15625, 0.0).normal(0.5F, 0.0F, 0.866F);

		wr.pos(0.5, 0.5, 0.0).tex(0.15625, 0.359375).normal(-0.5F, 0.0F, 0.866F);
		wr.pos(0.5, 1.5, 0.0).tex(0.15625, 0.0).normal(-0.5F, 0.0F, 0.866F);
		wr.pos(0.066987, 1.5, -0.25).tex(0.328125, 0.0).normal(-0.5F, 0.0F, 0.866F);

		wr.pos(0.066987, 0.5, -0.25).tex(0.328125, 0.359375).normal(-1.0F, 0.0F, 0.0F);
		wr.pos(0.066987, 1.5, -0.25).tex(0.328125, 0.0).normal(-1.0F, 0.0F, 0.0F);
		wr.pos(0.066987, 1.5, -0.75).tex(0.5, 0.0).normal(-1.0F, 0.0F, 0.0F);

		wr.pos(0.066987, 0.5, -0.75).tex(0.5, 0.359375).normal(-0.5F, -0.0F, -0.866F);
		wr.pos(0.066987, 1.5, -0.75).tex(0.5, 0.0).normal(-0.5F, -0.0F, -0.866F);
		wr.pos(0.5, 1.5, -1.0).tex(0.65625, 0.0).normal(-0.5F, -0.0F, -0.866F);

		wr.pos(0.5, 1.5, -1.0).tex(0.453125, 0.671875).normal(0.378F, 0.6547F, -0.6547F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(0.378F, 0.6547F, -0.6547F);
		wr.pos(0.933013, 1.5, -0.75).tex(0.46875, 0.515625).normal(0.378F, 0.6547F, -0.6547F);

		wr.pos(0.933013, 1.5, -0.75).tex(0.46875, 0.515625).normal(0.7559F, 0.6547F, 0.0F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(0.7559F, 0.6547F, 0.0F);
		wr.pos(0.933013, 1.5, -0.25).tex(0.390625, 0.359375).normal(0.7559F, 0.6547F, 0.0F);

		wr.pos(0.933013, 1.5, -0.25).tex(0.0, 0.515625).normal(0.378F, 0.6547F, 0.6547F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(0.378F, 0.6547F, 0.6547F);
		wr.pos(0.5, 1.5, 0.0).tex(0.015625, 0.671875).normal(0.378F, 0.6547F, 0.6547F);

		wr.pos(0.5, 1.5, 0.0).tex(0.015625, 0.671875).normal(-0.378F, 0.6547F, 0.6547F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(-0.378F, 0.6547F, 0.6547F);
		wr.pos(0.066987, 1.5, -0.25).tex(0.15625, 0.765625).normal(-0.378F, 0.6547F, 0.6547F);

		wr.pos(0.066987, 1.5, -0.25).tex(0.15625, 0.765625).normal(-0.7559F, 0.6547F, 0.0F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(-0.7559F, 0.6547F, 0.0F);
		wr.pos(0.066987, 1.5, -0.75).tex(0.328125, 0.765625).normal(-0.7559F, 0.6547F, 0.0F);

		wr.pos(0.066987, 1.5, -0.75).tex(0.328125, 0.765625).normal(-0.378F, 0.6547F, -0.6547F);
		wr.pos(0.5, 2.0, -0.5).tex(0.234375, 0.546875).normal(-0.378F, 0.6547F, -0.6547F);
		wr.pos(0.5, 1.5, -1.0).tex(0.453125, 0.671875).normal(-0.378F, 0.6547F, -0.6547F);

		wr.pos(0.5, 0.5, -1.0).tex(0.9375, 0.453125).normal(0.378F, -0.6547F, -0.6547F);
		wr.pos(0.933013, 0.5, -0.75).tex(0.953125, 0.609375).normal(0.378F, -0.6547F, -0.6547F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(0.378F, -0.6547F, -0.6547F);

		wr.pos(0.933013, 0.5, -0.75).tex(0.953125, 0.609375).normal(0.7559F, -0.6547F, 0.0F);
		wr.pos(0.933013, 0.5, -0.25).tex(0.859375, 0.765625).normal(0.7559F, -0.6547F, 0.0F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(0.7559F, -0.6547F, 0.0F);

		wr.pos(0.933013, 0.5, -0.25).tex(0.46875, 0.609375).normal(0.378F, -0.6547F, 0.6547F);
		wr.pos(0.5, 0.5, 0.0).tex(0.5, 0.453125).normal(0.378F, -0.6547F, 0.6547F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(0.378F, -0.6547F, 0.6547F);

		wr.pos(0.5, 0.5, 0.0).tex(0.5, 0.453125).normal(-0.378F, -0.6547F, 0.6547F);
		wr.pos(0.066987, 0.5, -0.25).tex(0.625, 0.359375).normal(-0.378F, -0.6547F, 0.6547F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(-0.378F, -0.6547F, 0.6547F);

		wr.pos(0.066987, 0.5, -0.25).tex(0.625, 0.359375).normal(-0.7559F, -0.6547F, 0.0F);
		wr.pos(0.066987, 0.5, -0.75).tex(0.796875, 0.359375).normal(-0.7559F, -0.6547F, 0.0F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(-0.7559F, -0.6547F, 0.0F);

		wr.pos(0.066987, 0.5, -0.75).tex(0.796875, 0.359375).normal(-0.378F, -0.6547F, -0.6547F);
		wr.pos(0.5, 0.5, -1.0).tex(0.9375, 0.453125).normal(-0.378F, -0.6547F, -0.6547F);
		wr.pos(0.5, 0.0, -0.5).tex(0.71875, 0.578125).normal(-0.378F, -0.6547F, -0.6547F);
		
		Tessellator.getInstance().draw();

		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
}
