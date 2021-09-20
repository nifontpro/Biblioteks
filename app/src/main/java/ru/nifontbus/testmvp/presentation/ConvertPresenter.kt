package ru.nifontbus.testmvp.presentation

import android.graphics.Bitmap
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.ConvertRepo
import ru.nifontbus.testmvp.views.ConvertView

class ConvertPresenter : MvpPresenter<ConvertView>() {

    private val router: Router = App.instance.router
    private val convertRepo = ConvertRepo()
    lateinit var inBitmap: Bitmap

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        convertRepo.loadBitmap("test.jpg")
            .subscribe { bitmap ->
                inBitmap = bitmap
                viewState.showInputImage(bitmap)
            }
    }

    fun convert() {
        convertRepo.convert(inBitmap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { outBitmap ->
                viewState.showOutputImage(outBitmap)
                convertRepo.saveBitmap(outBitmap)
            }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}