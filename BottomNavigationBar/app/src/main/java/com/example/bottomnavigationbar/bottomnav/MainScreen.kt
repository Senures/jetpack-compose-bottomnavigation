package com.example.bottomnavigationbar.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }

}

    @Composable
    fun BottomBar(navController: NavHostController) {

        val screens = listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Profile,
            BottomBarScreen.Settings
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen =screen , currentDestination = currentDestination, navController =navController )
            }
        }
    }


    @Composable
    fun RowScope.AddItem(
        screen: BottomBarScreen,
        currentDestination: NavDestination?,
        navController: NavController
    ) {
        BottomNavigationItem(
            label = {
                Text(text = screen.title)
            },
            icon = {
                Icon(imageVector = screen.icon, contentDescription = "navigation ıcon")
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,
            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            onClick = {
                navController.navigate(screen.route)
            }

        )
    }






