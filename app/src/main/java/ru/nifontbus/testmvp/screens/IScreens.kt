package ru.nifontbus.testmvp.screens

import com.github.terrakok.cicerone.Screen
import ru.nifontbus.testmvp.models.GithubUser

interface IScreens {
    fun usersScreen(): Screen
    fun detailsScreen() : Screen
}