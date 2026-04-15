package com.mrayushbajpai.statforge.domain.repository

import com.mrayushbajpai.statforge.domain.model.Quest
import com.mrayushbajpai.statforge.domain.model.Skill
import com.mrayushbajpai.statforge.domain.model.Stat
import kotlinx.coroutines.flow.Flow

interface StatRepository {
    fun getTotalXp(): Flow<Int>
    fun getQuests(): Flow<List<Quest>>
    fun getSkills(): Flow<List<Skill>>
    fun getStats(): Flow<List<Stat>>
    suspend fun completeQuest(questId: String)
}
