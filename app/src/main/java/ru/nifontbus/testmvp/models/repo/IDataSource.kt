package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.nifontbus.testmvp.models.data.GithubList
import ru.nifontbus.testmvp.models.data.GithubUser

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getListRepository(@Url urlRepository: String) : Single<List<GithubList>>
}