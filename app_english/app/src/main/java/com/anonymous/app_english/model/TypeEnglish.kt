package com.anonymous.app_english.model

import com.anonymous.app_english.R

interface TypeEnglish{
    val nameType : String
    val img : Int
}

class VocabularyEnglish() : TypeEnglish {
    override val nameType: String
        get() = "Vocabulary"
    override val img: Int
        get() = R.drawable.vocabulary

}

class GrammarEnglish() : TypeEnglish {
    override val nameType: String
        get() = "Grammar"
    override val img: Int
        get() = R.drawable.vocabulary
}

class ListenEnglish() : TypeEnglish {
    override val nameType: String
        get() = "Listening"
    override val img: Int
        get() = R.drawable.vocabulary
}

class SpeakingEnglish() : TypeEnglish {
    override val nameType: String
        get() = "Speaking"
    override val img: Int
        get() = R.drawable.vocabulary
}