package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

class GithubUsersRepo(private val api: IDataSource) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())

    override fun getRepository(urlRepository: String): Single<List<GithubRepository>> =
        api.getListRepository(urlRepository).subscribeOn(Schedulers.io())

}
