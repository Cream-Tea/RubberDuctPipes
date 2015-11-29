package de.syndesi.rdp.blocks;

import de.syndesi.rdp.*;
import net.minecraft.block.Block;

public class SyBlocks {
	
	public static Block example;
	
	public static void init(){
		example();
	}
	
	public static void example(){
		example = new SyRenderExample().setBlockName("example_Render").setBlockTextureName("wood");
	}
}
