package ru.nifontbus.testmvp.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nifontbus.testmvp.views.ui.DetailsFragment
import ru.nifontbus.testmvp.views.ui.UsersFragment

class AndroidScreens : IScreens {

    override fun usersScreen(): Screen = FragmentScreen { UsersFragment.newInstance() }
    override fun detailsScreen(login: String): Screen =
        FragmentScreen { DetailsFragment.newInstance(login) }
}

