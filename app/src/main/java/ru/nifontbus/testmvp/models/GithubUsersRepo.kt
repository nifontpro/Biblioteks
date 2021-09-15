package ru.nifontbus.testmvp.models

import io.reactivex.rxjava3.kotlin.toObservable

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5"),
    ).toObservable()

    fun getUsers() = users
}