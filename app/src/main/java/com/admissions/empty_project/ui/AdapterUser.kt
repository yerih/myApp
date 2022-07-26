package com.admissions.empty_project.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.admissions.domain.User
import com.admissions.empty_project.common.basicDiffUtil
import com.admissions.empty_project.common.inflate
import com.admissions.empty_project.data.R
import com.admissions.empty_project.data.databinding.LayoutItemBinding

class AdapterUser (
    private val listener: (User, Int) -> Unit
) : ListAdapter<User, AdapterUser.ViewHolder>(basicDiffUtil{ old, new -> old.id == new.id }){

    class ViewHolder(view: View, val listener: (User, Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = LayoutItemBinding.bind(view)
        fun bind(user: User, position: Int){
            binding.user = user
            binding.btnDelete.setOnClickListener { listener(user, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.layout_item, false), listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user =getItem(position)
        holder.bind(user, position)
    }
}