package org.kmp.playground.lexivo.user.feature.landing

import com.arkivanov.decompose.ComponentContext

class LandingComponentImpl(val componentContext: ComponentContext) :
    ComponentContext by componentContext,
    LandingComponent     {
}