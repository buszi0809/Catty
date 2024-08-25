package com.kwdev.catty.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.kwdev.catty.ui.home.HomeViewModel.ViewEvent.OnGetRandomClick
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

    if (viewState.isLoading) {
        ProgressDialog()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            FilledTonalButton(
                onClick = { viewModel.onViewEvent(OnGetRandomClick) },
                content = { Text(text = "Get new random image") },
            )
            Text(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                text = viewState.imageUrl ?: "not loaded yet",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
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
