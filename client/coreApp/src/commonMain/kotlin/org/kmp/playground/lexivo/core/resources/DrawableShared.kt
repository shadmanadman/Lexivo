package org.kmp.playground.lexivo.core.resources

import androidx.compose.runtime.Composable
import lexivo.client.coreapp.generated.resources.Res
import lexivo.client.coreapp.generated.resources.empty
import lexivo.client.coreapp.generated.resources.icon_profile
import org.jetbrains.compose.resources.DrawableResource

object DrawableShared {
    val empty = Res.drawable.empty
    val profile = Res.drawable.icon_profile
}

@Composable
fun DrawableResource.asDrawable() = org.jetbrains.compose.resources.painterResource(this)