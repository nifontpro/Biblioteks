package ru.nifontbus.testmvp.presentation.screens

import com.github.terrakok.cicerone.Screen
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

interface IScreens {
    fun usersScreen(): Screen
    fun repositoryScreen(currentUser: GithubUser) : Screen
    fun repoInfoScreen(currentUser: GithubUser, currentRepository: GithubRepository) : Screen
}