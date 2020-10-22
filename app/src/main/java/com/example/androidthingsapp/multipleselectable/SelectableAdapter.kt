package com.example.androidthingsapp.multipleselectable

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.paging.PagedListAdapter


abstract class SelectableAdapter<V : SelectableItem, VH : SelectableAdapter.SelectableViewHolder<V>>(diffCallback: DiffUtil.ItemCallback<V> = object : DiffUtil.ItemCallback<V>() {
    override fun areItemsTheSame(oldItem: V, newItem: V): Boolean =
            oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: V, newItem: V): Boolean =
            oldItem.id == newItem.id && oldItem.isSelected == newItem.isSelected
}, protected val selectionMode: Selection = Selection.MULTIPLE) : PagedListAdapter<V, VH>(diffCallback) {

    var itemSelectedListener: OnItemSelectedListener<V>? = null


    enum class Selection {
        MULTIPLE
    }

    private val internalItemSelectionListener = object : OnItemSelectedListener<V> {
        override fun onItemSelected(item: V) {
            itemSelectedListener?.onItemSelected(item)//Send outside of the adapter if need
        }
    }

    fun getSelectedItems(): List<V> = (0 until itemCount).mapNotNull { getItem(it) }.filter { it.isSelected.get() }


    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position > -1) {
            getItem(position)?.let { item ->
                holder.bind(item, internalItemSelectionListener)
            }
        }
    }

    /**
     * Custom ViewHolder to be use in [SelectableAdapter]
     *
     */
    open class SelectableViewHolder<V : SelectableItem>(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var item: V
        private lateinit var selectableListener: OnItemSelectedListener<V>

        init {
            view.setOnClickListener {
                item.isSelected.set(!item.isSelected.get())
                selectableListener.onItemSelected(item)
            }
        }

        /**
         * Need to be call inside [onBindViewHolder] to enable selection
         */
        internal open fun bind(item: V, internalItemSelectionListener: OnItemSelectedListener<V>) {
            this.item = item
            selectableListener = internalItemSelectionListener
        }
    }


    /**
     * Custom ViewHolder to be use in [SelectableAdapter] with Binding
     *
     */
    open class SelectableViewHolderBinding<V : SelectableItem>(val binding: ViewDataBinding) : SelectableViewHolder<V>(binding.root) {

        override fun bind(item: V, internalItemSelectionListener: OnItemSelectedListener<V>) {
            super.bind(item, internalItemSelectionListener)
            binding.setVariable(1, item)
            binding.executePendingBindings()
        }
    }

}
