package de.syndesi.rdp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SyCreativeTabs {
	
	public static CreativeTabs tabRDP;
	
	public static void init(){
		
		tabRDP = new CreativeTabs("tabRDP") {
			@Override
			public Item getTabIconItem() {
				return new ItemStack(Items.apple, 1, 0).getItem();
			}
		};
		
	}
	
}
