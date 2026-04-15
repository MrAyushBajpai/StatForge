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
import com.mrayushbajpai.statforge.domain.model.Skill
import com.mrayushbajpai.statforge.domain.model.Stat
import com.mrayushbajpai.statforge.ui.viewmodel.StatViewModel

@Composable
fun StatsScreen(viewModel: StatViewModel, onBack: () -> Unit) {
    val stats by viewModel.stats.collectAsState()
    val skills by viewModel.skills.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onBack) {
                Text("Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Stats & Skills", style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(stats) { stat ->
                StatCard(stat = stat, skills = skills.filter { it.statId == stat.id })
            }
        }
    }
}

@Composable
fun StatCard(stat: Stat, skills: List<Skill>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stat.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            skills.forEach { skill ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = skill.name, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "${skill.totalXp} XP", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
