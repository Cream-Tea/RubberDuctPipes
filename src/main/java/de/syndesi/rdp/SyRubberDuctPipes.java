package de.syndesi.rdp;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import de.syndesi.rdp.blocks.SyBlocks;
//import de.syndesi.rdp.items.SyItems;

/*
 * Author: Sören Klein, Germany
 * Contact: soerenklein98@gmail.com
 *          http://www.github.com/Syndesi
 * 
 * Thanks to Flenix! He helped me a lot with ISBRH!
 * Website: http://www.silvania.co.uk/
 */


@Mod(modid = SyVariables.MODID, name = SyVariables.NAME, version = SyVariables.VERSION)

public class SyRubberDuctPipes {
	
	@Instance(SyVariables.MODID)
	public static SyRubberDuctPipes instance;
	
	@SidedProxy(clientSide = "de.syndesi.rdp.SyClientProxy", serverSide = "de.syndesi.rdp.SyCommonProxy")
	public static SyCommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent PreEvent){
		SyVariables.init(PreEvent);
		SyCreativeTabs.init();
		//SyCoItems.init();
		SyBlocks.init();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent Event){
		proxy.init();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent PostEvent){
		
	}
	
}
