package ru.nifontbus.testmvp.presentation.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun usersScreen(): Screen
    fun detailsScreen() : Screen
    fun repoInfoScreen() : Screen
}