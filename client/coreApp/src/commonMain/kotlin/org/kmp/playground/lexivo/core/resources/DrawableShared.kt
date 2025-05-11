package org.kmp.playground.lexivo.core.resources

import androidx.compose.runtime.Composable
import lexivo.client.coreapp.generated.resources.Res
import lexivo.client.coreapp.generated.resources.empty
import lexivo.client.coreapp.generated.resources.ic_angry
import lexivo.client.coreapp.generated.resources.ic_happy
import lexivo.client.coreapp.generated.resources.ic_magic
import lexivo.client.coreapp.generated.resources.ic_smiley
import lexivo.client.coreapp.generated.resources.ic_suitcase
import lexivo.client.coreapp.generated.resources.icon_profile
import lexivo.client.coreapp.generated.resources.landing_background
import lexivo.client.coreapp.generated.resources.mesh_gradiant_background
import lexivo.client.coreapp.generated.resources.mesh_gradiant_red_blue
import lexivo.client.coreapp.generated.resources.mesh_gradiant_yellow_purple
import org.jetbrains.compose.resources.DrawableResource

object DrawableShared {
    val empty = Res.drawable.empty
    val profile = Res.drawable.icon_profile
    val mesh_gradiant_yellow_purple = Res.drawable.mesh_gradiant_yellow_purple
    val ic_happy = Res.drawable.ic_happy
    val ic_funny = Res.drawable.ic_smiley
    val ic_suitcase = Res.drawable.ic_suitcase
    val ic_mad = Res.drawable.ic_angry
    val mesh_gradiant_red_blue = Res.drawable.mesh_gradiant_red_blue
    val mesh_gradiant_background = Res.drawable.mesh_gradiant_background
    val landing_background = Res.drawable.landing_background
    val ic_magic = Res.drawable.ic_magic
}

@Composable
fun DrawableResource.asDrawable() = org.jetbrains.compose.resources.painterResource(this)