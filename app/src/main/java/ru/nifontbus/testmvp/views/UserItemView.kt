package ru.nifontbus.testmvp.views

interface UserItemView: IItemView {
    fun showLogin(login: String)
    fun loadAvatar(url: String)
}