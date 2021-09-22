package ru.nifontbus.testmvp.models

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.models.data.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}
