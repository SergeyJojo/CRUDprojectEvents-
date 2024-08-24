package com.learning.crud_events_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.learning.crud_events_practice.ui.add_item.AddEditItemScreen
import com.learning.crud_events_practice.ui.items_list.ItemsListScreen
import com.learning.crud_events_practice.ui.theme.CRUD_Events_practiceTheme
import com.learning.crud_events_practice.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUD_Events_practiceTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.ITEMS_LIST
                ) {
                    composable(
                        route = Routes.ITEMS_LIST
                    ) {
                        ItemsListScreen(onNavigate = { navController.navigate(it.route) })
                    }
                    composable(route = Routes.ADD_EDIT_ITEMS + "?itemId={itemId}",
                        arguments = listOf(
                            navArgument(name = "itemId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )) {
                        AddEditItemScreen(onPopBackStack = { navController.popBackStack() })
                    }
                }

            }
        }
    }
}

