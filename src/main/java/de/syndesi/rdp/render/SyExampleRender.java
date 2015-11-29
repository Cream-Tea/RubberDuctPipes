package de.syndesi.rdp.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.syndesi.ISBRH.SubNode;
import de.syndesi.ISBRH.Vertex;
import de.syndesi.rdp.SyClientProxy;
import org.lwjgl.opengl.GL11;

public class SyExampleRender implements ISimpleBlockRenderingHandler {
	
	Tessellator tess;
	SubNode list;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks rendererBlocks) {
		int blockX = x;
		int blockY = y;
		int blockZ = z;
		
		if(x < 0){
			x = 16 - (Math.abs(x) % 16);
		} else {
			x = Math.abs(x) % 16;
		}
		if(y < 0){
			y = 16 - (Math.abs(y) % 16);
		} else {
			y = Math.abs(y) % 16;
		}
		if(z < 0){
			z = 16 - (Math.abs(z) % 16);
		} else {
			z = Math.abs(z) % 16;
		}
		
		
		/*
        tess = Tessellator.instance;
        list = new SubNode("quads");
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        */
        IIcon icon;
        int meta = rendererBlocks.blockAccess.getBlockMetadata(x, y, z);
        icon = block.getIcon(0, meta);
        /*list.translate(+0.5, +0.5, +0.5);	// move back
        list.translate(x,  y,  z);			// move to the final destination, to the block in the world
        
        list.parse(tess);*/
        
        
        /*
        GL11.glColor3f(1f,1f,0f);
        GL11.glPointSize(10);
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex3f((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f);
        System.out.println("#####" + x + "|" + y + "#####");
        GL11.glEnd();*/
        
        //drawCircle(x, y, z, 1.5f, 64);
        //drawArc(x, y, z, 5f, 0f, 0.5f, 256);
        
        GL11.glColor3f(1f,1f,0f);
        drawPipe(x, y, z, x + 32, y, z, 64);
        drawPipe(x, y, z, x, y, z + 32, 64);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glEnd();
        //drawCircle(x, y+0.5f, z, 2f, 32);

        //tess.draw();
		//tess.startDrawingQuads();
        
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return SyClientProxy.exampleRenderID;
	}
	
	public void drawCircle(float cx, float cy, float cz, float r, int num_segments){
		float theta = (float) (2 * 3.1415926 / num_segments);
		float tangential_factor = (float) Math.tan(theta);
		float radial_factor = (float) Math.cos(theta);
		float x = r;
		float y = 0;
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for(int ii = 0; ii < num_segments; ii++){
			float tx = -y;
			float ty = x;
			
			x += tx * tangential_factor;
			y += ty * tangential_factor;
			
			x *= radial_factor;
			y *= radial_factor;
			GL11.glVertex3f(x + cx, y/3 + cy, cz);
		}
		GL11.glEnd();
	}
	
	public void drawPipe(float px, float py, float pz, float px2, float py2, float pz2, int num_segments){
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
	}
	
	public void drawArc(float cx, float cy, float cz, float r, float start_angle, float arc_angle, int num_segments){
		float theta = arc_angle / num_segments - 1;//theta is now calculated from the arc angle instead, the - 1 bit comes from the fact that the arc is open

		float tangetial_factor = (float) Math.tan(theta);

		float radial_factor = (float) Math.cos(theta);

		
		float x = (float) (r * Math.cos(start_angle));//we now start at the start angle
		float y = (float) (r * Math.sin(start_angle)); 
	    
		GL11.glBegin(GL11.GL_LINE_STRIP);//since the arc is not a closed curve, this is a strip now
		for(int ii = 0; ii < num_segments; ii++)
		{ 
			GL11.glVertex3f(x + cx, y + cy, cz);

			float tx = -y; 
			float ty = x; 

			x += tx * tangetial_factor; 
			y += ty * tangetial_factor; 

			x *= radial_factor; 
			y *= radial_factor; 
		} 
		GL11.glEnd();
	}

}
