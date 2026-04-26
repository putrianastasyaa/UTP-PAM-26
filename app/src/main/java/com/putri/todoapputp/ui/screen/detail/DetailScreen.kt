package com.putri.todoapputp.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.putri.todoapputp.data.TaskRepository

@Composable
fun DetailScreen(navController: NavController, taskId: Int?) {

    val task = taskId?.let { TaskRepository.getTaskById(it) }

    var title by remember { mutableStateOf(task?.title ?: "") }
    var desc by remember { mutableStateOf(task?.description ?: "") }
    var deadline by remember { mutableStateOf(task?.deadline ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, start = 16.dp, end = 16.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Back")
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "DETAIL TUGAS",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        HorizontalDivider()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Spacer(modifier = Modifier.height(12.dp))

                // JUDUL
                Text("Judul Tugas")

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // DESKRIPSI
                Text("Deskripsi")

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // DEADLINE
                Text("Deadline")

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // STATUS
                Text("Status")

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = if (task?.isDone == true) "Selesai" else "Belum Selesai",
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Column {

                // UPDATE + SELESAI
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = {
                            task?.let {

                                val updated = it.copy(
                                    title = title,
                                    description = desc,
                                    deadline = deadline
                                )

                                TaskRepository.updateTask(updated)
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Update")
                    }

                    Button(
                        onClick = {
                            task?.let {

                                val updated = it.copy(
                                    title = title,
                                    description = desc,
                                    deadline = deadline,
                                    isDone = true
                                )

                                TaskRepository.updateTask(updated)
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Selesai")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // DELETE
                OutlinedButton(
                    onClick = {
                        task?.let {
                            TaskRepository.deleteTask(it)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Delete")
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}