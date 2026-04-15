package com.mrayushbajpai.statforge.domain.model

data class Stat(
    val id: String,
    val name: String
)

data class Skill(
    val id: String,
    val statId: String,
    val name: String,
    val totalXp: Int = 0
)

data class Quest(
    val id: String,
    val skillId: String,
    val title: String,
    val xpValue: Int
)
