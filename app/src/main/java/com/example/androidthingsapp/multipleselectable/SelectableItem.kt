package com.example.androidthingsapp.multipleselectable

import androidx.databinding.ObservableBoolean


/**
 * Interface to allow selection on an Item,
 *
 */
interface SelectableItem {
    /**
     * Observable on selection, can be use in XML to set the selection status if needed
     */
    var isSelected: ObservableBoolean

    /**
     * Unique ID to identify the Item
     */
    val id: Int
}

/**
 * Listener use to be notify on item selection
 */
interface OnItemSelectedListener<I> {
    fun onItemSelected(item: I)
}