package com.mrayushbajpai.statforge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class StatEntity(
    @PrimaryKey val id: String,
    val name: String
)

@Entity(tableName = "skills")
data class SkillEntity(
    @PrimaryKey val id: String,
    val statId: String,
    val name: String,
    val totalXp: Int = 0
)

@Entity(tableName = "quests")
data class QuestEntity(
    @PrimaryKey val id: String,
    val skillId: String,
    val title: String,
    val xpValue: Int
)

@Entity(tableName = "xp_logs")
data class XpLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val skillId: String,
    val xpEarned: Int,
    val timestamp: Long = System.currentTimeMillis()
)
