package ru.nifontbus.testmvp.presentation.details.adapter

import ru.nifontbus.testmvp.presentation.IListPresenter
import ru.nifontbus.testmvp.presentation.users.adapter.IItemView

interface RepoItemView: IItemView {
    fun showName(name: String)
}

interface IRepoListPresenter : IListPresenter<RepoItemView>