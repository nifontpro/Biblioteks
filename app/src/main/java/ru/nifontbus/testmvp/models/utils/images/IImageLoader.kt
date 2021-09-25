package ru.nifontbus.testmvp.models.utils.images

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}