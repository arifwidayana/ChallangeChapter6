package com.arifwidayana.challangechapter6.model.preference

data class UserPreference(
    val username: String,
    val password: String,
    val loginSession: Boolean,
    val onBoardSession: Boolean
)
