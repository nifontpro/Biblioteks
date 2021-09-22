package ru.nifontbus.testmvp.presentation.users.adapter

interface UserItemView: IItemView {
    fun showLogin(login: String)
    fun loadAvatar(url: String)
}