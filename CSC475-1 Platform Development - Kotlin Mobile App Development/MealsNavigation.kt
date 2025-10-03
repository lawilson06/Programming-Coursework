package com.example.recipeapp2


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp2.data.SavedRecipeScreen
import com.example.recipeapp2.data.SavedRecipeViewModel
import com.example.recipeapp2.details.MealDetailsViewModel
import com.example.recipeapp2.details.MealsDetailsScreen

@Composable
fun MealsNavigation(
    categoriesViewModel: MealsCategoriesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val detailsViewModel: MealDetailsViewModel = viewModel()
    val savedRecipeViewModel: SavedRecipeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = MealsScreen.SavedRecipesScreen.route
    ) {
        composable(MealsScreen.CategoriesScreen.route) {
            MealsCategoriesScreen(navController, categoriesViewModel)
        }

        composable(MealsScreen.SavedRecipesScreen.route) {
            SavedRecipeScreen(navController, savedRecipeViewModel)
        }

        composable(MealsScreen.DetailsScreen.route + "/{mealId}",
            arguments = listOf(navArgument("mealId") {
                type = NavType.StringType
                defaultValue = ""
                nullable = false
            })
        ) { entry ->
            val mealId = if (entry.arguments != null) entry.arguments!!.getString("mealId")
            else ""
            MealsDetailsScreen(mealId ?: "", detailsViewModel, navController, savedRecipeViewModel)
        }

    }

}