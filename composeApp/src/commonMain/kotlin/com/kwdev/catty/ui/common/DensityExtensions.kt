package com.kwdev.catty.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

private data class CompoundPaddingValues(
    val others: List<PaddingValues>,
) : PaddingValues {

    override fun calculateBottomPadding(): Dp =
        others.sumOf { it.calculateBottomPadding() }

    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp =
        others.sumOf { it.calculateLeftPadding(layoutDirection) }

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp =
        others.sumOf { it.calculateRightPadding(layoutDirection) }

    override fun calculateTopPadding(): Dp =
        others.sumOf { it.calculateTopPadding() }
}

operator fun PaddingValues.plus(other: PaddingValues): PaddingValues =
    when (this) {
        is CompoundPaddingValues -> CompoundPaddingValues(others + other)
        else -> CompoundPaddingValues(listOf(this, other))
    }

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Dp): Dp {
    var dp = 0.dp
    for (item in this) {
        dp += selector(item)
    }
    return dp
}
