package ru.nifontbus.testmvp.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.nifontbus.testmvp.models.utils.images.GlideImageLoader
import ru.nifontbus.testmvp.models.utils.images.IImageLoader
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()

    @Singleton
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
