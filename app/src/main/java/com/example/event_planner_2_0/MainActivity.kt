package com.example.event_planner_2_0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.event_planner_2_0.ui.Navigation
import com.example.event_planner_2_0.ui.screens.EventsScreen
import com.example.event_planner_2_0.ui.screens.HomeScreen
import com.example.event_planner_2_0.ui.screens.NewEventScreen
import com.example.event_planner_2_0.ui.screens.NewTaskScreen
import com.example.event_planner_2_0.ui.screens.NewTemplateScreen
import com.example.event_planner_2_0.ui.screens.PdfScreen
import com.example.event_planner_2_0.ui.screens.SingleEventScreen
import com.example.event_planner_2_0.ui.theme.EventPlanner20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()

            EventPlanner20Theme {
                NavHost(
                    navController = navController,
                    startDestination = Navigation.Pdf.route,
                    modifier = Modifier
                        .padding(50.dp)
                        .fillMaxWidth()
                ) {
                    composable(route = Navigation.Home.route) {
                        HomeScreen(navController)
                    }
                    composable(route = Navigation.Events.route) {
                        EventsScreen(navController)
                    }
                    composable(route = Navigation.SingleEvent.route) {
                        SingleEventScreen(navController)
                    }
                    composable(route = Navigation.Pdf.route) {
                        PdfScreen(navController)
                    }
                    composable(route = Navigation.NewTemplate.route) {
                        NewTemplateScreen(navController)
                    }
                    composable(route = Navigation.NewEvent.route) {
                        NewEventScreen(navController)
                    }
                    composable(route = Navigation.NewTask.route) {
                        NewTaskScreen(navController)
                    }
                }
            }
        }
    }
}