package ru.nifontbus.testmvp.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ConvertView: MvpView {
    fun showOutputImage(text: String)
}