package org.kmp.playground.lexivo.core.resources

import androidx.compose.runtime.Composable
import lexivo.client.coreapp.generated.resources.Lexivo_Your_AI_Partner_for_Perfecting_Every_Message
import lexivo.client.coreapp.generated.resources.Res
import lexivo.client.coreapp.generated.resources.angry_tune
import lexivo.client.coreapp.generated.resources.app_name
import lexivo.client.coreapp.generated.resources.funny_tune
import lexivo.client.coreapp.generated.resources.happy_tune
import lexivo.client.coreapp.generated.resources.history
import lexivo.client.coreapp.generated.resources.home
import lexivo.client.coreapp.generated.resources.its_all_free_try_it
import lexivo.client.coreapp.generated.resources.professional_tune
import lexivo.client.coreapp.generated.resources.profile
import lexivo.client.coreapp.generated.resources.team
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

object StringShared {
    val app_name = Res.string.app_name
    val its_all_free_try_it = Res.string.its_all_free_try_it
    val Lexivo_Your_AI_Partner_for_Perfecting_Every_Message = Res.string.Lexivo_Your_AI_Partner_for_Perfecting_Every_Message

    val home = Res.string.home
    val profile = Res.string.profile
    val team = Res.string.team
    val history = Res.string.history

    val angry_tune = Res.string.angry_tune
    val happy_tune = Res.string.happy_tune
    val professional_tune = Res.string.professional_tune
    val funny_tune = Res.string.funny_tune
}

@Composable
fun StringResource.asString() = stringResource(this)