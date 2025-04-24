package org.kmp.playground.lexivo.model.team.request

import org.kmp.playground.lexivo.model.team.response.StyleGuide

data class TeamRequest(
    val name: String,
    val ownerId: String,
    val members: List<String>,
    val styleGuide: StyleGuide
)
