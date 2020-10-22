package com.example.androidthingsapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidthingsapp.R
import com.example.androidthingsapp.multipleselectable.SelectableAdapter
import com.example.androidthingsapp.viewmodel.TeamViewModel

class TeamAdapter(selectionMode: Selection) :
    SelectableAdapter<TeamViewModel, SelectableAdapter.SelectableViewHolderBinding<TeamViewModel>>(
        object : DiffUtil.ItemCallback<TeamViewModel>() {
            override fun areItemsTheSame(
                oldItem: TeamViewModel,
                newItem: TeamViewModel
            ): Boolean =
                oldItem == newItem


            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: TeamViewModel,
                newItem: TeamViewModel
            ): Boolean =
                oldItem.name == newItem.name && oldItem.type == newItem.type && oldItem.id == newItem.id && oldItem.isSelected == newItem.isSelected

        },
        selectionMode
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectableViewHolderBinding<TeamViewModel> =
        SelectableViewHolderBinding(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(
                    parent.context
                ), R.layout.item_team, parent, false
            )
        )

    class TeamLayoutManager(context: Context) : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean {
            return false
        }
    }
}