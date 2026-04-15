package com.mrayushbajpai.statforge.data.repository

import com.mrayushbajpai.statforge.data.local.dao.StatDao
import com.mrayushbajpai.statforge.data.local.entity.*
import com.mrayushbajpai.statforge.domain.model.Quest
import com.mrayushbajpai.statforge.domain.model.Skill
import com.mrayushbajpai.statforge.domain.model.Stat
import com.mrayushbajpai.statforge.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class StatRepositoryImpl(private val statDao: StatDao) : StatRepository {

    override fun getTotalXp(): Flow<Int> = statDao.getTotalXp().map { it ?: 0 }

    override fun getQuests(): Flow<List<Quest>> = statDao.getAllQuests().map { entities ->
        entities.map { Quest(it.id, it.skillId, it.title, it.xpValue) }
    }

    override fun getSkills(): Flow<List<Skill>> = statDao.getAllSkills().map { entities ->
        entities.map { Skill(it.id, it.statId, it.name, it.totalXp) }
    }

    override fun getStats(): Flow<List<Stat>> = statDao.getAllStats().map { entities ->
        entities.map { Stat(it.id, it.name) }
    }

    override suspend fun completeQuest(questId: String) {
        val quests = statDao.getAllQuests().first()
        val quest = quests.find { it.id == questId }
        quest?.let {
            // Log XP
            statDao.insertXpLog(XpLogEntity(skillId = it.skillId, xpEarned = it.xpValue))
            // Update Skill XP
            statDao.updateSkillXp(it.skillId, it.xpValue)
        }
    }

    suspend fun seedDatabase() {
        val stats = listOf(
            StatEntity("1", "Physical"),
            StatEntity("2", "Intellect"),
            StatEntity("3", "Spiritual"),
            StatEntity("4", "Psyche"),
            StatEntity("5", "Core")
        )
        statDao.insertStats(stats)

        val skills = listOf(
            SkillEntity("1", "1", "Strength"),
            SkillEntity("2", "1", "Agility"),
            SkillEntity("3", "2", "Logic"),
            SkillEntity("4", "3", "Meditation"),
            SkillEntity("5", "4", "Willpower"),
            SkillEntity("6", "5", "Adaptability")
        )
        statDao.insertSkills(skills)

        val quests = listOf(
            QuestEntity("1", "1", "Morning Workout", 10),
            QuestEntity("2", "2", "30min Run", 15),
            QuestEntity("3", "3", "Solve a Puzzle", 20),
            QuestEntity("4", "4", "10min Mindfulness", 10),
            QuestEntity("5", "5", "Cold Shower", 15)
        )
        statDao.insertQuests(quests)
    }
}
