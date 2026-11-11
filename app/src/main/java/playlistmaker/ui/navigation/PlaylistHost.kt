package playlistmaker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import playlistmaker.domain.model.Track
import playlistmaker.ui.details.DetailsScreen
import playlistmaker.ui.main.DrawMainScreen
import playlistmaker.ui.search.SearchScreen
import playlistmaker.ui.settings.DrawSettingsScreen
import playlistmaker.ui.view_model.SearchViewModel

enum class Routes(val route: String){
    MAIN("playlistmaker/ui/main"),
    SEARCH("playlistmaker/ui/search"),
    SETTINGS("playlistmaker/ui/settings"),
    DETAILS("details/{trackJson}"),

}

@Composable
fun PlaylistHost(
    modifier: Modifier = Modifier,
    startDestination: String = Routes.MAIN.route,
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Routes.MAIN.route) {
            DrawMainScreen(
                navigateToSearch = { navController.navigate(Routes.SEARCH.route) },
                navigateToPlaylists = {  },
                navigateToFavourites = {  },
                navigateToSettings = { navController.navigate(Routes.SETTINGS.route) },
            )
        }




        composable(route = Routes.SEARCH.route) {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = gson.toJson(track)
                    navController.navigate("details/$trackJson")
                },
                navigateBack = { navController.popBackStack() }
            )
        }


        composable(
            route = Routes.DETAILS.route
        ) { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val track = gson.fromJson(trackJson, Track::class.java)
                DetailsScreen(track = track)
            }
        }

        composable(route = Routes.SETTINGS.route) {
            DrawSettingsScreen(

            )
        }



    }
}



