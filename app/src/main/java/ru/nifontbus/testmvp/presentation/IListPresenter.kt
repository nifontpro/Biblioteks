package ru.nifontbus.testmvp.presentation

import ru.nifontbus.testmvp.views.IItemView
import ru.nifontbus.testmvp.views.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener : ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>