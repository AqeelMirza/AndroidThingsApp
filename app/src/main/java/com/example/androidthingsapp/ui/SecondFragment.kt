package com.example.androidthingsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidthingsapp.R
import com.example.androidthingsapp.adapter.SelectedTeamsAdapter
import com.example.androidthingsapp.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    // private lateinit var selectedItems: ArrayList<TeamViewModel>
    private lateinit var adapter: SelectedTeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedItems = arguments?.getParcelableArrayList<TeamViewModel>("selected_items")!!
        getRandom(selectedItems)
        initList(selectedItems)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun setChosenItem(view: View, viewModel: TeamViewModel) {
        view.findViewById<TextView>(R.id.selected_name).text =
            "\n${viewModel.name}\n"
    }

    private fun getRandom(selectedItems: ArrayList<TeamViewModel>) {
        val randomItem = (0..selectedItems.size-1).random()
        view?.let { setChosenItem(it, selectedItems.get(randomItem)) }
        removeItem(selectedItems, randomItem)
    }

    private fun removeItem(
        selectedItems: ArrayList<TeamViewModel>,
        randomItem: Int
    ) {
        selectedItems.remove(selectedItems.get(randomItem))
    }

    private fun initList(selectedItems: List<TeamViewModel>) {
        val controller =
            AnimationUtils.loadLayoutAnimation(
                requireActivity(),
                R.anim.layout_animation_left_to_right
            )
        adapter = SelectedTeamsAdapter(selectedItems)
        home_recycler_view.adapter = adapter
        home_recycler_view.layoutManager = LinearLayoutManager(activity)
        home_recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        home_recycler_view.setLayoutAnimation(controller)
        home_recycler_view.scheduleLayoutAnimation()
    }
}