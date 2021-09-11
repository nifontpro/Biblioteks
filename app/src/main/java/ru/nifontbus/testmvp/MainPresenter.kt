package ru.nifontbus.testmvp

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {

    private val model = CountersModel()

    fun counterClick1() {
        val nextValue = model.next(0)
        viewState.setButtonText1(nextValue.toString())
    }

    fun counterClick2() {
        val nextValue = model.next(1)
        viewState.setButtonText2(nextValue.toString())
    }

    fun counterClick3() {
        val nextValue = model.next(2)
        viewState.setButtonText3(nextValue.toString())
    }
}