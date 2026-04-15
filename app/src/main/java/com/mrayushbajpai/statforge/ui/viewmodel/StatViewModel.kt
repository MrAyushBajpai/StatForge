package com.mrayushbajpai.statforge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrayushbajpai.statforge.domain.model.Quest
import com.mrayushbajpai.statforge.domain.repository.StatRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatViewModel(private val repository: StatRepository) : ViewModel() {

    val totalXp: StateFlow<Int> = repository.getTotalXp()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val quests: StateFlow<List<Quest>> = repository.getQuests()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun completeQuest(questId: String) {
        viewModelScope.launch {
            repository.completeQuest(questId)
        }
    }
}
