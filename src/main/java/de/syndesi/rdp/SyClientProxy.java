package de.syndesi.rdp;

import cpw.mods.fml.client.registry.RenderingRegistry;
import de.syndesi.rdp.SyCommonProxy;
import de.syndesi.rdp.render.SyExampleRender;

public class SyClientProxy extends SyCommonProxy {
	
	public static int exampleRenderID;

	@Override
	public void registerRenderers() {
		
		
		exampleRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(exampleRenderID, new SyExampleRender());
		
	}
	
}
