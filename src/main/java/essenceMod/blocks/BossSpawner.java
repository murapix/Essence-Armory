package essenceMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import essenceMod.entities.boss.EntityBoss;
import essenceMod.entities.boss.attacks.BossAttack;
import essenceMod.entities.boss.attacks.Flameblast;
import essenceMod.entities.boss.attacks.MobSpawn;
import essenceMod.entities.boss.attacks.Root;
import essenceMod.entities.boss.attacks.WitherCloud;
import essenceMod.registry.ModItems;
import essenceMod.tabs.ModTabs;

public class BossSpawner extends Block
{
	public BossSpawner()
	{
		super(Material.iron);
		setCreativeTab(ModTabs.tabEssence);
		setStepSound(soundTypeMetal);
		setHardness(5.0F);
		setResistance(10.0F);
		setLightLevel(1.0F);
		setHarvestLevel("pickaxe", 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state)
	{
		return getDefaultState();
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack item = player.getCurrentEquippedItem();
		if (item != null && item.stackSize > 0 && new ItemStack(ModItems.infusedWand).isItemEqual(item))
		{
			EntityBoss boss = new EntityBoss(world);
			if (!boss.hasProperArena(world, pos, player)) return false;
			
			BossAttack mobSpawn = new MobSpawn(boss, 4, 2);
			BossAttack flameblast = new Flameblast(boss, EntityBoss.FB_DEF_DMG, EntityBoss.FB_DEF_SIZE);
			BossAttack root = new Root(boss, EntityBoss.ROOT_DEF_TIMER, EntityBoss.ROOT_DEF_DUR);
			BossAttack cloud = new WitherCloud(boss, EntityBoss.WC_DEF_WITHER, EntityBoss.WC_DEF_SLOW, 3, EntityBoss.WC_DEF_TIMER, EntityBoss.WC_DEF_DUR);
			world.destroyBlock(pos, false);
			
			boss.setLevel(1);
			boss.setAttacks(mobSpawn, flameblast, root, cloud);
			boss.spawn(player, world, pos);
		}
		return false;
	}
}