package ru.nifontbus.testmvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle // Последнее изменение без очереди
//@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun init()
    fun updateList()
}