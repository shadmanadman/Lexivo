package org.kmp.playground.lexivo.user.feature.team

import com.arkivanov.decompose.ComponentContext

class TeamComponentImpl(
    componentContext: ComponentContext
) : ComponentContext by componentContext, TeamComponent {
}
