package essenceMod.entities.tileEntities;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class TileEntityEssencePylon extends TileEntityEssenceInfuser implements IInventory, ITickable
{	
	public TileEntityEssencePylon()
	{
		super();
		slots = new ItemStack[1];
	}
	
	@Override
	public void update()
	{
		markDirty();
		worldObj.markBlockRangeForRenderUpdate(pos, pos.add(1, 1, 1));
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return true;
	}
	
	public void infuse()
	{
		slots[0] = null;
		markDirty();
		worldObj.markBlockRangeForRenderUpdate(pos, pos.add(1, 1, 1));
	}
}