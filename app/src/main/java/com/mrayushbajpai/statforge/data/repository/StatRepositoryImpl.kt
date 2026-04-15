package com.mrayushbajpai.statforge.data.repository

import com.mrayushbajpai.statforge.domain.model.Quest
import com.mrayushbajpai.statforge.domain.model.Skill
import com.mrayushbajpai.statforge.domain.model.Stat
import com.mrayushbajpai.statforge.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class StatRepositoryImpl : StatRepository {
    private val _stats = MutableStateFlow(listOf(
        Stat("1", "Physical"),
        Stat("2", "Mental"),
        Stat("3", "Social")
    ))

    private val _skills = MutableStateFlow(listOf(
        Skill("1", "1", "Strength"),
        Skill("2", "1", "Agility"),
        Skill("3", "2", "Intelligence"),
        Skill("4", "3", "Charisma")
    ))

    private val _quests = MutableStateFlow(listOf(
        Quest("1", "1", "Lift Weights", 10),
        Quest("2", "2", "Go for a Run", 15),
        Quest("3", "3", "Read a Book", 20),
        Quest("4", "4", "Talk to a Stranger", 5)
    ))

    private val _totalXp = MutableStateFlow(0)

    override fun getTotalXp(): Flow<Int> = _totalXp

    override fun getQuests(): Flow<List<Quest>> = _quests

    override fun getSkills(): Flow<List<Skill>> = _skills

    override fun getStats(): Flow<List<Stat>> = _stats

    override suspend fun completeQuest(questId: String) {
        val quest = _quests.value.find { it.id == questId }
        quest?.let {
            _totalXp.value += it.xpValue
            
            // Update skill XP (simple simulation)
            val updatedSkills = _skills.value.map { skill ->
                if (skill.id == it.skillId) {
                    skill.copy(totalXp = skill.totalXp + it.xpValue)
                } else {
                    skill
                }
            }
            _skills.value = updatedSkills
        }
    }
}
