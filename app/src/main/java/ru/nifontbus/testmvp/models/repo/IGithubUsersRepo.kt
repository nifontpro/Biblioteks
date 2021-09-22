package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepository(urlRepository: String) : Single<List<GithubRepository>>
}
