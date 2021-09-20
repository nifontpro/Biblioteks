package ru.nifontbus.testmvp.views

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.nifontbus.testmvp.models.GithubUser

@AddToEndSingle
interface ConvertView: MvpView {
    fun showInputImage(bitmap: Bitmap)
    fun showOutputImage(bitmap: Bitmap)
}