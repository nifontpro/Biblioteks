package ru.nifontbus.testmvp.models.images

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}