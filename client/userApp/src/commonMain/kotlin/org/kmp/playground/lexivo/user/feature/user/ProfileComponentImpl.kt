package org.kmp.playground.lexivo.user.feature.user

import com.arkivanov.decompose.ComponentContext

class ProfileComponentImpl(
    componentContext: ComponentContext
) : ComponentContext by componentContext, ProfileComponent {
}
