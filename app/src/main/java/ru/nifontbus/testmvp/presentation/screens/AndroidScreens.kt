package ru.nifontbus.testmvp.presentation.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nifontbus.testmvp.presentation.repository.RepositoryFragment
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoFragment
import ru.nifontbus.testmvp.presentation.users.UsersFragment

class AndroidScreens : IScreens {

    override fun usersScreen(): Screen = FragmentScreen { UsersFragment.newInstance() }
    override fun detailsScreen(): Screen = FragmentScreen { RepositoryFragment.newInstance() }
    override fun repoInfoScreen(): Screen = FragmentScreen { RepoInfoFragment.newInstance() }
}

