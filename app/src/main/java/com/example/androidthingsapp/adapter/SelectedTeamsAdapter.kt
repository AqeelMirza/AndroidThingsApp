package com.example.androidthingsapp.adapter

import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidthingsapp.R
import com.example.androidthingsapp.databinding.ItemTeamBinding
import com.example.androidthingsapp.viewmodel.TeamViewModel

class SelectedTeamsAdapter(private val selectedItems: List<TeamViewModel>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTeamBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_team, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(selectedItems[position])
    }
}

class MyViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(selectedItems: TeamViewModel) {
        binding.selectedTeamName.visibility = VISIBLE
        binding.selectedTeamName.text = "${selectedItems.name}"
    }
}
