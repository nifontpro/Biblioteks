package ru.nifontbus.testmvp.presentation

import ru.nifontbus.testmvp.presentation.users.adapter.IItemView
import ru.nifontbus.testmvp.presentation.users.adapter.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener : ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>