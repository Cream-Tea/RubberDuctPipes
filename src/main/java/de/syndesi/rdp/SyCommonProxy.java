package de.syndesi.rdp;

import cpw.mods.fml.common.registry.GameRegistry;
import de.syndesi.rdp.blocks.SyBlocks;
import de.syndesi.rdp.blocks.SyRenderExample;

public class SyCommonProxy {
	
	public void init(){
		registerBlocks();
		registerItems();
		registerFluids();
		registerRenderers();
		registerTileEntities();
	}
	
	public void registerBlocks(){
		GameRegistry.registerBlock(SyBlocks.example, "Example Renderer");
	}
	
	public void registerItems(){}
	
	public void registerFluids(){}
	
	public void registerRenderers(){}
	
	public void registerTileEntities(){}
}
