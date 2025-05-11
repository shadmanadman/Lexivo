package org.kmp.playground.lexivo.user.feature.auth

import com.arkivanov.decompose.ComponentContext

class AuthComponentImpl(
    componentContext: ComponentContext
) : ComponentContext by componentContext, AuthComponent {
}
