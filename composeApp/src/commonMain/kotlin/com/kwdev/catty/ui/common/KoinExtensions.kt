package com.kwdev.catty.ui.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified T : ViewModel> koinViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val koin = LocalKoinScope.current
    return viewModel<T> { koin.get(qualifier, parameters) }
}
