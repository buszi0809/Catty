package com.kwdev.catty.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kwdev.catty.ui.common.koinViewModel

@Composable
internal fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        Text(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            text = viewState.greeting,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}
