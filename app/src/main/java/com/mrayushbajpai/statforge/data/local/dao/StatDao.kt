package com.mrayushbajpai.statforge.data.local.dao

import androidx.room.*
import com.mrayushbajpai.statforge.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StatDao {
    @Query("SELECT * FROM stats")
    fun getAllStats(): Flow<List<StatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>)

    @Query("SELECT * FROM skills")
    fun getAllSkills(): Flow<List<SkillEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkills(skills: List<SkillEntity>)

    @Query("UPDATE skills SET totalXp = totalXp + :xp WHERE id = :skillId")
    suspend fun updateSkillXp(skillId: String, xp: Int)

    @Query("SELECT * FROM quests")
    fun getAllQuests(): Flow<List<QuestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuests(quests: List<QuestEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertXpLog(log: XpLogEntity)

    @Query("SELECT SUM(totalXp) FROM skills")
    fun getTotalXp(): Flow<Int?>
}
