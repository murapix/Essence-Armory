package essenceMod.handlers.compatibility.jei;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IRecipeLayoutDrawable;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import essenceMod.handlers.compatibility.jei.item.InfuserItemRecipeCategory;
import essenceMod.handlers.compatibility.jei.item.InfuserItemRecipeHandler;
import essenceMod.handlers.compatibility.jei.upgrade.InfuserUpgradeRecipeCategory;
import essenceMod.handlers.compatibility.jei.upgrade.InfuserUpgradeRecipeHandler;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.crafting.InfuserRecipes;
import essenceMod.registry.crafting.upgrades.UpgradeRecipe;

@JEIPlugin
public class JEIEssencePlugin extends BlankModPlugin
{
	private static IJeiRuntime jeiRuntime;
	
	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		System.out.println("Registering plugin: " + this.getClass().getName());
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		registry.addRecipeCategories(
				new InfuserUpgradeRecipeCategory(jeiHelpers.getGuiHelper()),
				new InfuserItemRecipeCategory(jeiHelpers.getGuiHelper())
				);
		registry.addRecipeHandlers(
				new InfuserUpgradeRecipeHandler(),
				new InfuserItemRecipeHandler()
				);
		
		ArrayList<UpgradeRecipe> upgradeRecipes = new ArrayList<UpgradeRecipe>();
		for (Class<? extends Item> c : InfuserRecipes.upgradeRecipes.keySet())
			upgradeRecipes.addAll(InfuserRecipes.upgradeRecipes.get(c));
		registry.addRecipes(upgradeRecipes);
		registry.addRecipes(InfuserRecipes.itemRecipes);
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.essenceInfuser), InfuserUpgradeRecipeCategory.UID);
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.essenceInfuser), InfuserItemRecipeCategory.UID);
	}
	
	public static IRecipeLayoutDrawable getDrawableFromItem(ItemStack stack)
	{
		if (stack != null)
		{
			IRecipeRegistry registry = JEIEssencePlugin.jeiRuntime.getRecipeRegistry();
			IFocus<ItemStack> focus = registry.createFocus(IFocus.Mode.OUTPUT, stack);
			for (IRecipeCategory<?> category : registry.getRecipeCategories(focus))
			{
				if (category.getUid().equals(InfuserUpgradeRecipeCategory.UID) || category.getUid().equals(InfuserItemRecipeCategory.UID) || category.getUid().equals(VanillaRecipeCategoryUid.CRAFTING))
				{
					List<IRecipeLayoutDrawable> layouts = getLayouts(registry, category, focus);
					if (!layouts.isEmpty())
						return layouts.get(0);
				}
			}
		}
		return null;
	}
	
	private static <T extends IRecipeWrapper> List<IRecipeLayoutDrawable> getLayouts(IRecipeRegistry registry, IRecipeCategory<T> category, IFocus<ItemStack> focus)
	{
		List<IRecipeLayoutDrawable> layouts = new ArrayList<>();
		List<T> wrappers = registry.getRecipeWrappers(category, focus);
		for (T wrapper : wrappers)
		{
			IRecipeLayoutDrawable layout = registry.createRecipeLayoutDrawable(category, wrapper, focus);
			layouts.add(layout);
		}
		return layouts;
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime runtime)
	{
		super.onRuntimeAvailable(jeiRuntime);
		JEIEssencePlugin.jeiRuntime = runtime;
	}
}