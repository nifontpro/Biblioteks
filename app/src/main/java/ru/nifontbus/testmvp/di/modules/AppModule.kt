package ru.nifontbus.testmvp.di.modules

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}
