package de.syndesi.rdp.items;

import de.syndesi.rdp.SyCreativeTabs;
import de.syndesi.rdp.SyVariables;
import de.syndesi.rdp.render.SyPipeRender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemPipeLayer extends Item {
	public Vec3 tag_a;
	public Vec3 tag_b;
	public boolean tagged = false;
	private SyPipeRender pipe_render;
	
	public ItemPipeLayer() {
		setUnlocalizedName(SyVariables.MODID + "_" + "items/itemPipeLayer");
		setTextureName(SyVariables.MODID + ":" + "items/itemPipeLayer");
		setCreativeTab(SyCreativeTabs.tabRDP);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player, World world, int bx, int by, int bz, int side, float hitX, float hitY, float hitZ)
    {
		if (!tagged) {
			tagged = true;
			tag_a = Vec3.createVectorHelper(bx, by, bz);
			player.addChatComponentMessage(new ChatComponentText("You have tagged a block"));
		} else {
			tagged = false;
			tag_b = Vec3.createVectorHelper(bx, by, bz);
			pipe_render = new SyPipeRender().setPipeCoords(tag_a, tag_b, (byte) 8);
			player.addChatComponentMessage(new ChatComponentText("You have successfully linked two blocks together"));
		}
		
        return true;
    }
}
