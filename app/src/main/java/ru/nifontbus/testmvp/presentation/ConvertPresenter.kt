package ru.nifontbus.testmvp.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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

    //    val inBitmap = BitmapFactory.decodeFile("app/src/main/res/drawable/test.jpg")
    lateinit var inBitmap: Bitmap

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun convert() {

        convertRepo.saveBitmapToFile(inBitmap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { fileName ->
//                viewState.showOutputImage(outBitmap)
//                convertRepo.saveBitmapToFile(outBitmap)
                Log.e("my", fileName)
                viewState.showOutputImage(fileName)
            }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}