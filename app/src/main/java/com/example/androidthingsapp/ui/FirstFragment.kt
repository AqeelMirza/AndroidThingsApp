package com.example.androidthingsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidthingsapp.R
import com.example.androidthingsapp.adapter.TeamAdapter
import com.example.androidthingsapp.multipleselectable.SelectableAdapter
import com.example.androidthingsapp.repo.PopulateTeamRepo
import com.example.androidthingsapp.viewmodel.MainViewModel
import com.example.androidthingsapp.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var selectedItems: List<TeamViewModel>
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, MainViewModel.Factory(PopulateTeamRepo()))
            .get(MainViewModel::class.java)

        initList()

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            validateAndNavigateToNextPage(adapter)
        }
    }

    private fun validateAndNavigateToNextPage(adapter: TeamAdapter) {
        selectedItems = adapter.getSelectedItems()
        if (selectedItems.size < 3)
            Toast.makeText(
                requireActivity(),
                "Please choose min 3 Favorites",
                Toast.LENGTH_SHORT
            ).show()
        else {
            val bundle = bundleOf("selected_items" to selectedItems)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    private fun initList() {
        val controller =
            AnimationUtils.loadLayoutAnimation(
                requireActivity(),
                R.anim.layout_animation_down_to_up
            )
        adapter = TeamAdapter(SelectableAdapter.Selection.MULTIPLE)
        home_recycler_view.adapter = adapter
        home_recycler_view.layoutManager = TeamAdapter.TeamLayoutManager(requireActivity())
        home_recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.teams.observe(requireActivity(), Observer { teamsPagedList ->
            adapter.submitList(teamsPagedList)
        })

        home_recycler_view.setLayoutAnimation(controller)
        home_recycler_view.scheduleLayoutAnimation()
    }
}