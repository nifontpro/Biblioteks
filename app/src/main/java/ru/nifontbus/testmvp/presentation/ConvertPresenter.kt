package ru.nifontbus.testmvp.presentation

import android.graphics.Bitmap
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.ConvertRepo
import ru.nifontbus.testmvp.views.ConvertView

class ConvertPresenter : MvpPresenter<ConvertView>() {

    private val router: Router = App.instance.router
    private val convertRepo = ConvertRepo()
    var inBitmap: Bitmap? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        inBitmap = convertRepo.loadBitmap("test.jpg")
        inBitmap?.let {
            viewState.showInputImage(inBitmap!!)
        }
    }

    fun convert() {

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}