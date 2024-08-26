package com.kwdev.catty.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kwdev.catty.ui.common.CollectFlowWithLifecycleEffect
import com.kwdev.catty.ui.common.component.ProgressDialog
import com.kwdev.catty.ui.common.koinViewModel
import com.kwdev.catty.ui.home.HomeViewModel.ViewAction
import com.kwdev.catty.ui.home.HomeViewModel.ViewAction.ShowSnackbar
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent.OnGetRandomGifClick
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent.OnGetRandomImageClick
import com.kwdev.catty.ui.home.HomeViewModel.ViewState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    CollectFlowWithLifecycleEffect(viewModel.viewAction) { action ->
        onViewAction(action, snackbarState, coroutineScope)
    }

    ScreenContent(
        viewState = viewState,
        snackbarState = snackbarState,
        onViewEvent = viewModel::onViewEvent,
    )
}

private fun onViewAction(
    action: ViewAction,
    snackbarState: SnackbarHostState,
    coroutineScope: CoroutineScope,
) {
    when (action) {
        is ShowSnackbar -> coroutineScope.launch {
            snackbarState.showSnackbar(message = action.message)
        }
    }
}

@Composable
private fun ScreenContent(
    viewState: ViewState,
    snackbarState: SnackbarHostState,
    onViewEvent: (ViewEvent) -> Unit,
) {
    if (viewState.isLoading) {
        ProgressDialog()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { innerPadding ->
        ScreenScaffoldContent(
            modifier = Modifier.padding(innerPadding),
            viewState = viewState,
            onViewEvent = onViewEvent,
        )
    }
}

@Composable
private fun ScreenScaffoldContent(
    modifier: Modifier,
    viewState: ViewState,
    onViewEvent: (ViewEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        FilledTonalButton(
            onClick = { onViewEvent(OnGetRandomImageClick) },
            content = { Text(text = "Get new random image") },
        )
        FilledTonalButton(
            onClick = { onViewEvent(OnGetRandomGifClick) },
            content = { Text(text = "Get new random gif") },
        )
        if (viewState.imageUrl != null) {
            KamelImage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(20.dp),
                resource = { asyncPainterResource(data = viewState.imageUrl) },
                contentDescription = null,
                onLoading = {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                },
                onFailure = { cause ->
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = cause.message ?: "Could not download image",
                    )
                },
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = viewState.imageUrl ?: "not loaded yet",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}
