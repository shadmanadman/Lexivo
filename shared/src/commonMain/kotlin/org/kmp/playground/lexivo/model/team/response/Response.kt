package org.kmp.playground.lexivo.model.team.response

data class StyleGuide(
    val preferredTone: String,
    val writingDo: List<String>,
    val `writingDon't`: List<String>
)

data class TeamResponse(
    val id: String,
    val name: String,
    val ownerId: String,
    val members: List<String>,
    val styleGuide: StyleGuide
)
