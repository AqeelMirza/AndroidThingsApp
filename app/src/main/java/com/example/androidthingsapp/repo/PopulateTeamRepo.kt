package com.example.androidthingsapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.example.androidthingsapp.viewmodel.TeamViewModel

class PopulateTeamRepo : TeamRepo {
    override fun getTeams(): LiveData<PagedList<TeamViewModel>> {
        val teams = listOf(
            TeamViewModel(1, "Arsenal"),
            TeamViewModel(2, "Manchester United"),
            TeamViewModel(3, "Manchester City"),
            TeamViewModel(4, "Liverpool"),
            TeamViewModel(5, "Chelsea"),
            TeamViewModel(6, "WestHam United"),
            TeamViewModel(7, "Leicester City "),
            TeamViewModel(8, "Everton")
        )
        return LivePagedListBuilder(object : DataSource.Factory<Int, TeamViewModel>() {
            override fun create(): DataSource<Int, TeamViewModel> =
                object : PositionalDataSource<TeamViewModel>() {
                    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TeamViewModel>) {
                        callback.onResult(teams, 0, teams.size)
                    }

                    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TeamViewModel>) {
                        callback.onResult(teams)
                    }
                }

        }, teams.size).build()
    }


}