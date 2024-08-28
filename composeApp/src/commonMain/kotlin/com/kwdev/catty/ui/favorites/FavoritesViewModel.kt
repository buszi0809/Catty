package com.kwdev.catty.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwdev.catty.domain.model.FavoriteCatImage
import com.kwdev.catty.domain.model.LocalFavoriteCatImagesRepository
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewAction.NavigateBack
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewEvent.OnBackClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val localFavoriteCatImagesRepository: LocalFavoriteCatImagesRepository,
) : ViewModel() {

    private val mutableViewState = MutableStateFlow(ViewState())
    val viewState = mutableViewState.asStateFlow()

    private val mutableViewAction = Channel<ViewAction>(Channel.BUFFERED)
    val viewAction = mutableViewAction.receiveAsFlow()

    fun onViewEvent(event: ViewEvent) {
        when (event) {
            OnBackClick -> mutableViewAction.trySend(NavigateBack)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = localFavoriteCatImagesRepository.getAll()
            mutableViewState.update { it.copy(items = items) }
        }
    }

    data class ViewState(
        val items: List<FavoriteCatImage> = emptyList(),
    )

    sealed interface ViewEvent {
        data object OnBackClick : ViewEvent
    }

    sealed interface ViewAction {
        data object NavigateBack : ViewAction
    }
}
