package fi.metropolia.bibeks.presidents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fi.metropolia.bibeks.presidents.screens.DetailScreen
import fi.metropolia.bibeks.presidents.screens.MainScreen
import fi.metropolia.bibeks.presidents.ui.theme.PresidentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PresidentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val presidentList = DataProvider.presidents

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable(route = "MainScreen") {
                            MainScreen(presidentList, navController)
                        }
                        composable(
                            route = "DetailScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val index = backStackEntry.arguments?.getInt("index")
                            DetailScreen(
                                presidentList = presidentList,
                                itemIndex = index ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}



