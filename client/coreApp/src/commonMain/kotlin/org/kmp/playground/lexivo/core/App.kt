package org.kmp.playground.lexivo.core

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import lexivo.client.coreapp.generated.resources.Res
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

    }
}

fun String.test(): String = this + "test"