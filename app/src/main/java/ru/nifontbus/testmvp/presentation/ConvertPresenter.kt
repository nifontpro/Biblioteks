package ru.nifontbus.testmvp.presentation

import android.graphics.Bitmap
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.ConvertRepo
import ru.nifontbus.testmvp.views.ConvertView

class ConvertPresenter : MvpPresenter<ConvertView>() {

    private val router: Router = App.instance.router
    private val convertRepo = ConvertRepo()
    private val compositeDispose = CompositeDisposable()

    lateinit var inBitmap: Bitmap

    override fun detachView(view: ConvertView?) {
        super.detachView(view)
        cancelObserve()
    }

    fun cancelObserve() {
        compositeDispose.clear()
        viewState.showOutputText("Convert canceled")
    }

    fun convert() {

        val result = convertRepo.saveBitmapToFile(inBitmap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { fileName ->
                Log.e("my", fileName)
                viewState.showOutputText(fileName)
            }

        compositeDispose.add(result)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}