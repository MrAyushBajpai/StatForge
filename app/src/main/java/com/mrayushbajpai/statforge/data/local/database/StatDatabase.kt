package com.mrayushbajpai.statforge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrayushbajpai.statforge.data.local.dao.StatDao
import com.mrayushbajpai.statforge.data.local.entity.*

@Database(
    entities = [StatEntity::class, SkillEntity::class, QuestEntity::class, XpLogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StatForgeDatabase : RoomDatabase() {
    abstract fun statDao(): StatDao
}
