package com.kwdev.catty.ui.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute {

    @Serializable
    data object Home : NavRoute

    @Serializable
    data object Favorites : NavRoute
}
