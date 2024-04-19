package com.anonymous.app_english.components.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anonymous.app_english.databinding.ItemSubjectVocabularyBinding
import com.anonymous.app_english.model.SubjectModel

class ItemSubjectVocabulary : RecyclerView.Adapter<ItemSubjectVocabulary.SubjectViewHolder>() {

    private val dataSubjectList = mutableListOf<SubjectModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<SubjectModel>){
        dataSubjectList.clear()
        dataSubjectList.addAll(data)
        notifyDataSetChanged()
    }

    class SubjectViewHolder(private val binding: ItemSubjectVocabularyBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(subjectModel: SubjectModel){
            binding.tvNameSubject.text = subjectModel.nameSubject
            binding.tvPoints.text = "${subjectModel.points}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(ItemSubjectVocabularyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return dataSubjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subjectModel = dataSubjectList[position]
        holder.setData(subjectModel)
    }
}