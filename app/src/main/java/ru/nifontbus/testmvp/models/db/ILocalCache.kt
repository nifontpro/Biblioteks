package ru.nifontbus.testmvp.models.db

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

interface ILocalCache {
    fun saveUsersToDb(users: List<GithubUser>): Single<List<GithubUser>>
    fun getLocalUsers(): Single<List<GithubUser>>

    fun getLocalRepositories(
        user: GithubUser,
        repositories: List<GithubRepository>
    ): Single<List<GithubRepository>>

    fun saveLocalRepositories(user: GithubUser): Single<List<GithubRepository>>
}