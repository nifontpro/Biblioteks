package ru.nifontbus.testmvp.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nifontbus.testmvp.views.ui.UsersFragment

/*
interface AndroidScreens : IScreens {
    override fun usersScreen(): Screen {
        return FragmentScreen { UsersFragment() }
    }
}*/

object AndroidScreens {

    fun usersScreen(): Screen {
        return FragmentScreen { UsersFragment() }
    }
}

