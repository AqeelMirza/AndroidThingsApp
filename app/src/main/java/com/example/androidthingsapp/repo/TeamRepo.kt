package com.example.androidthingsapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.androidthingsapp.viewmodel.TeamViewModel

interface TeamRepo {
    fun getTeams(): LiveData<PagedList<TeamViewModel>>
}