package ru.nifontbus.testmvp.models

class GithubUsersRepo {
    private val users = listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5"),
    )

    fun getUsers() = users
}