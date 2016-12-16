package essenceMod.handlers.compatibility.jei;

import java.util.ArrayList;
import javax.annotation.Nonnull;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.Item;
import essenceMod.handlers.compatibility.jei.item.InfuserItemRecipeCategory;
import essenceMod.handlers.compatibility.jei.item.InfuserItemRecipeHandler;
import essenceMod.handlers.compatibility.jei.upgrade.InfuserUpgradeRecipeCategory;
import essenceMod.handlers.compatibility.jei.upgrade.InfuserUpgradeRecipeHandler;
import essenceMod.registry.crafting.InfuserRecipes;
import essenceMod.registry.crafting.upgrades.UpgradeRecipe;

@JEIPlugin
public class JEIEssencePlugin implements IModPlugin
{
	@Override
	public void register(@Nonnull IModRegistry registry)
	{
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
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime arg0)
	{}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
	{}

	@Override
	public void registerIngredients(IModIngredientRegistration registry)
	{}
}