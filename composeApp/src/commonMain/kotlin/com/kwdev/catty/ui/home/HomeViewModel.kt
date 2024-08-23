package com.kwdev.catty.ui.home

import androidx.lifecycle.ViewModel
import com.kwdev.catty.Greeting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModel(
    greeting: Greeting,
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState(greeting.greet()))
    val viewState = mutableViewState.asStateFlow()

    data class ViewState(
        val greeting: String,
    )
}
