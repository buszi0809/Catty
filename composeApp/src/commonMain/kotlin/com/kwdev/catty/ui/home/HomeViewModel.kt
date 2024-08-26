package com.kwdev.catty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwdev.catty.domain.GetRandomImageUseCase
import com.kwdev.catty.ui.home.HomeViewModel.ViewAction.ShowSnackbar
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent.OnGetRandomGifClick
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent.OnGetRandomImageClick
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getRandomImageUseCase: GetRandomImageUseCase,
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState())
    val viewState = mutableViewState.asStateFlow()

    private val mutableViewAction = Channel<ViewAction>(Channel.BUFFERED)
    val viewAction = mutableViewAction.receiveAsFlow()

    fun onViewEvent(event: ViewEvent) {
        when (event) {
            OnGetRandomImageClick -> onGetRandomClick(gif = false)
            OnGetRandomGifClick -> onGetRandomClick(gif = true)
        }
    }

    private fun onGetRandomClick(gif: Boolean) {
        mutableViewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getRandomImageUseCase(gif)
                .onSuccess { url ->
                    mutableViewState.update {
                        it.copy(
                            isLoading = false,
                            imageUrl = url,
                        )
                    }
                }
                .onFailure { cause ->
                    mutableViewState.update { it.copy(isLoading = false) }
                    mutableViewAction.trySend(ShowSnackbar(cause.message ?: "Something went wrong"))
                }
        }
    }

    data class ViewState(
        val imageUrl: String? = null,
        val isLoading: Boolean = false,
    )

    sealed interface ViewEvent {
        data object OnGetRandomImageClick : ViewEvent
        data object OnGetRandomGifClick : ViewEvent
    }

    sealed interface ViewAction {
        data class ShowSnackbar(val message: String) : ViewAction
    }
}
