package de.syndesi.rdp;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class SyVariables {
	
	public static final String MODID = "syRDP";
	public static final String NAME = "Rubber Duct Pipes";
	public static final String VERSION = "0.1";
	public static final String MCVERSION = "1.7.10";
	
	public static final float[] slope = {0.00f, 0.24f, 0.44f, 0.61f, 0.75f, 0.86f, 0.94f, 0.98f, 1.00f, 0.98f, 0.94f, 0.86f, 0.75f, 0.61f, 0.44f, 0.24f, 0.00f};
	
	public static boolean enableOpenGL_TRIANGLES;
	
	public static void init(FMLPreInitializationEvent PreEvent){
		Configuration config = new Configuration(PreEvent.getSuggestedConfigurationFile());
		config.load();
		/*
		config.setCategoryComment(" Rubber Duct Pipes", "This mod is written by Syndesi.\nContact me via Github: www.github.com/syndesi or via e-mail: soerenklein98@gmail.com\nThanks to Flenix!");
		config.get(" syconstruction", "minecraftVersion", "1.7.10");
		config.get(" syconstruction", "modVersion", "0.3 Alpha");
		enableOpenGL_TRIANGLES = 		config.get(" syconstruction", "enableOpenGL_TRIANGLES", true).getBoolean();
		
		config.setCategoryComment("pipelines", "This section enables/disables special types of pipelines.");
		*/
		config.save();
		
	}
	
}
