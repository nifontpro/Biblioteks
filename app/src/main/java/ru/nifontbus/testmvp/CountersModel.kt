package ru.nifontbus.testmvp

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

/*    private fun getCurrent(index: Int): Int {
        return counters[index]
    }*/

    fun next(index: Int): Int {
        counters[index]++
        return counters[index]
    }

    fun set(index: Int, value: Int){
        counters[index] = value
    }

}