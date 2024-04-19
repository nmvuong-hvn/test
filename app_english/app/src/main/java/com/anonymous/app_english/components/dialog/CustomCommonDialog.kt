package com.anonymous.app_english.components.dialog

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.anonymous.app_english.R
import com.anonymous.app_english.databinding.ItemDialogVocabularyBinding
import com.anonymous.app_english.model.SubjectModel

class CustomCommonDialog (private val context: Context, private val binding: ItemDialogVocabularyBinding){

    private var dialog : Dialog? =null

    init {
        dialog = Dialog(context)
    }



    fun dismiss(){
        dialog?.dismiss()
    }
}