package com.kwdev.catty.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kwdev.catty.ui.common.CollectFlowWithLifecycleEffect
import com.kwdev.catty.ui.common.koinViewModel
import com.kwdev.catty.ui.common.plus
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewAction
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewAction.NavigateBack
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewEvent
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewEvent.OnBackClick
import com.kwdev.catty.ui.favorites.FavoritesViewModel.ViewState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun FavoritesScreen(navController: NavController) {
    val viewModel = koinViewModel<FavoritesViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CollectFlowWithLifecycleEffect(viewModel.viewAction) { action ->
        onViewAction(action, navController)
    }

    ScreenContent(
        viewState = viewState,
        onViewEvent = viewModel::onViewEvent,
    )
}

private fun onViewAction(
    action: ViewAction,
    navController: NavController,
) {
    when (action) {
        NavigateBack -> navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    viewState: ViewState,
    onViewEvent: (ViewEvent) -> Unit,
) {
    val behavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Favorite cat images") },
                scrollBehavior = behavior,
                navigationIcon = {
                    IconButton(onClick = { onViewEvent(OnBackClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.statusBars,
    ) { innerPadding ->
        ScreenScaffoldContent(
            modifier = Modifier
                .padding(innerPadding)
                .nestedScroll(behavior.nestedScrollConnection),
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
    // TODO make items take more space vertically or horizontally if possible
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = WindowInsets.navigationBars.asPaddingValues() + PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = viewState.items,
            key = { it.id },
        ) { item ->
            KamelImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                resource = { asyncPainterResource(item.imageUrl) },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                onLoading = {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                },
                onFailure = {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
