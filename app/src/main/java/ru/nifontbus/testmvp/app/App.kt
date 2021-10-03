package ru.nifontbus.testmvp.app

import android.app.Application
import ru.nifontbus.testmvp.di.component.AppComponent
import ru.nifontbus.testmvp.di.component.DaggerAppComponent
import ru.nifontbus.testmvp.di.component.RepositorySubcomponent
import ru.nifontbus.testmvp.di.component.UserSubcomponent
import ru.nifontbus.testmvp.di.modules.AppModule
import ru.nifontbus.testmvp.di.scopes.IRepositoryScopeContainer
import ru.nifontbus.testmvp.di.scopes.IUserScopeContainer

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}

