package ru.nifontbus.testmvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle // Последнее изменение без очереди
//@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setButtonText1(text: String)
    fun setButtonText2(text: String)
    fun setButtonText3(text: String)
}