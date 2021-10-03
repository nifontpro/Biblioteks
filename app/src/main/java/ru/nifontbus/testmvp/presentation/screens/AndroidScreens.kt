package ru.nifontbus.testmvp.presentation.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.presentation.repository.RepositoryFragment
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoFragment
import ru.nifontbus.testmvp.presentation.users.UsersFragment

class AndroidScreens : IScreens {

    override fun usersScreen(): Screen = FragmentScreen { UsersFragment.newInstance() }
    override fun repositoryScreen(currentUser: GithubUser): Screen =
        FragmentScreen { RepositoryFragment.newInstance(currentUser) }

    override fun repoInfoScreen(
        currentUser: GithubUser,
        currentRepository: GithubRepository
    ): Screen = FragmentScreen { RepoInfoFragment.newInstance(currentUser, currentRepository) }
}

