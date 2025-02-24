package com.example.event_planner_2_0.ui

sealed class Navigation(val route: String) {
    object Home: Navigation(route = "home")
    object Events: Navigation(route = "events")
    object SingleEvent: Navigation(route = "singleEvent")
    object Pdf: Navigation(route = "pdf")
    object NewEvent: Navigation(route = "newEvent")
    object NewTemplate: Navigation(route = "newTemplate")
    object NewTask: Navigation(route = "newTask")
}



