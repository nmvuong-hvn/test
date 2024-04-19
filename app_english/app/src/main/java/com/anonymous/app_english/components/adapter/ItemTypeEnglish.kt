package com.anonymous.app_english.components.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anonymous.app_english.databinding.ItemTypeEnglishBinding
import com.anonymous.app_english.model.TypeEnglish

class ItemTypeEnglishAdapter (
    private val onClick :(TypeEnglish) -> Unit
) : RecyclerView.Adapter<ItemTypeEnglishAdapter.TypeEnglishViewHolder>(){
    private val listTypeEnglish = mutableListOf<TypeEnglish>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<TypeEnglish>){
        listTypeEnglish.clear()
        listTypeEnglish.addAll(data)
        notifyDataSetChanged()
    }

    class TypeEnglishViewHolder(private val binding: ItemTypeEnglishBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun setData(data: TypeEnglish, onClick: (TypeEnglish) -> Unit){
                binding.ivType.setBackgroundResource(data.img)
                binding.tvType.text = data.nameType
                binding.layoutItem.setOnClickListener { onClick(data) }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeEnglishViewHolder {
        return TypeEnglishViewHolder(ItemTypeEnglishBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return listTypeEnglish.size
    }

    override fun onBindViewHolder(holder: TypeEnglishViewHolder, position: Int) {
        holder.setData(listTypeEnglish[position]){
            onClick(it)
        }
    }
}