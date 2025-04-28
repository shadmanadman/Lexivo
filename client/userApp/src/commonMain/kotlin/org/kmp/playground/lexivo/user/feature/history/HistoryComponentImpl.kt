package org.kmp.playground.lexivo.user.feature.history

import com.arkivanov.decompose.ComponentContext

class HistoryComponentImpl(
    componentContext: ComponentContext
) : ComponentContext by componentContext, HistoryComponent {
}
