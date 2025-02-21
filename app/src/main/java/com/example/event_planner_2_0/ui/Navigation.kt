package com.example.event_planner_2_0.ui

sealed class Navigation(val route: String) {
    data object Home: Navigation(route = "home")
    data object Events: Navigation(route = "events")
    data object SingleEvent: Navigation(route = "singleEvent")
    data object Pdf: Navigation(route = "pdf")
    data object NewEvent: Navigation(route = "newEvent")
    data object NewTemplate: Navigation(route = "newTemplate")
    data object NewTask: Navigation(route = "newTask")
}


