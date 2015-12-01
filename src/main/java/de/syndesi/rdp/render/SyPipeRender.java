package de.syndesi.rdp.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.syndesi.rdp.SyClientProxy;

public class SyPipeRender implements ISimpleBlockRenderingHandler {
	
	private float px, py, pz, px2, py2, pz2;
	private byte num_segments;
	
	Tessellator tess;
	
	public SyPipeRender setPipeCoords(Vec3 tag1, Vec3 tag2, byte seg){
		px = (float) tag1.xCoord;
		py = (float) tag1.yCoord;
		pz = (float) tag1.zCoord;
		px2 = (float) tag2.xCoord;
		py2 = (float) tag2.yCoord;
		pz2 = (float) tag2.zCoord;
		num_segments = seg;
		return this;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean renderWorldBlock(IBlockAccess world, int rx, int ry, int rz,
			Block block, int modelId, RenderBlocks renderer) {
		px += 0.5f;
		py += 0.5f;
		pz += 0.5f;
		px2 += 0.5f;
		py2 += 0.5f;
		pz2 += 0.5f;
		float theta = (float) (2 * 3.1415926 / num_segments * 0.5);
		float tangential_factor = (float) Math.tan(theta);
		float radial_factor = (float) Math.cos(theta);
		float dx = (px2 - px)/num_segments;
		float dy = (py2 - py)/num_segments;
		float dz = (pz2 - pz)/num_segments;
		float r = (float) Math.sqrt(Math.pow(px2 - px, 2) + Math.pow(py2 - py, 2) + Math.pow(pz2 - pz, 2)) / 2;
		float x = 0;
		float y = 0;
		float z = 0;
		float cx = r;
		float cy = 0;
		GL11.glBegin(GL11.GL_LINE_STRIP);
		GL11.glLineWidth(6);
		for(int ii = 0; ii <= num_segments; ii++){
			GL11.glVertex3f(px + x, (float) (py + y - cy*0.175), pz + z);
			float tx = -cy;
			float ty = cx;
			cx += tx * tangential_factor;
			cy += ty * tangential_factor;
			cx *= radial_factor;
			cy *= radial_factor;
			x += dx;
			y += dy;
			z += dz;
		}
		GL11.glEnd();
		return false;
	}


	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return SyClientProxy.exampleRenderID;
	}
}
