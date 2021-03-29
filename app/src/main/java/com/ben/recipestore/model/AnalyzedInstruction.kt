package com.ben.recipestore.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)