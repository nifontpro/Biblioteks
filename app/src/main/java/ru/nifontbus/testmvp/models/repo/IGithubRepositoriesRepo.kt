package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

interface IGithubRepositoriesRepo {
    fun getRepository(user: GithubUser) : Single<List<GithubRepository>>
}
