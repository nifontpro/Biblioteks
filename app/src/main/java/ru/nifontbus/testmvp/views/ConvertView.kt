package ru.nifontbus.testmvp.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ConvertView: MvpView {
    fun showOutputText(text: String)
}