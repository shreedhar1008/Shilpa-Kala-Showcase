package com.shilpakala.showcase.data.model

data class WorkInProgress(
    val id: String,
    val title: String,
    val artistId: String,
    val currentStep: Int,
    val totalSteps: Int,
    val steps: List<TimelineStep>
)

data class TimelineStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val timestamp: Long,
    val isCompleted: Boolean
)
