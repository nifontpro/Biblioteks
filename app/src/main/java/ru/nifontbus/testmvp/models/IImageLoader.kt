package ru.nifontbus.testmvp.models

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}