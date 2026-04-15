package com.mrayushbajpai.statforge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrayushbajpai.statforge.domain.model.Quest
import com.mrayushbajpai.statforge.ui.viewmodel.StatViewModel

@Composable
fun QuestScreen(viewModel: StatViewModel, onBack: () -> Unit) {
    val quests by viewModel.quests.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onBack) {
                Text("Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Quests", style = MaterialTheme.typography.headlineMedium)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn {
            items(quests) { quest ->
                QuestItem(quest = quest, onComplete = { viewModel.completeQuest(quest.id) })
            }
        }
    }
}

@Composable
fun QuestItem(quest: Quest, onComplete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = quest.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "XP: ${quest.xpValue}", style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = onComplete) {
                Text("Complete")
            }
        }
    }
}
