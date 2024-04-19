package com.anonymous.app_english.model

data class SubjectModel(
    val id: String = "${System.currentTimeMillis()}",
    val nameSubject : String = "",
    val points: Int = 0,
    val status: Int
)
