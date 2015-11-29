package de.syndesi.rdp;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.syndesi.construction.SyCoCreativeTabs;
import de.syndesi.construction.Variables;
import de.syndesi.construction.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SyRenderExample extends Block {

	public SyRenderExample() {
		super(Material.rock);
		this.setHardness(5F);		// Like the Block of Iron
		this.setCreativeTab(SyCoCreativeTabs.tabSyCo);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		this.setLightOpacity(0);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setBlockBoundsForItemRender();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
    }
	
	@Override
	public int getRenderType() {
		return ClientProxy.beamRenderID;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(Variables.MODID + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side){
		int meta = world.getBlockMetadata(x,  y,  z);
		return side == ForgeDirection.DOWN || side == ForgeDirection.UP;
	}
	

	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack){
		int meta = 0;
		Vec3 lookVec = par5EntityLivingBase.getLookVec();
		if(lookVec.yCoord < -0.5f){
			meta = 4;
		} else if(lookVec.yCoord > 0.5f){
			meta = 5;
		} else {
			meta = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		}
		par1World.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
}
