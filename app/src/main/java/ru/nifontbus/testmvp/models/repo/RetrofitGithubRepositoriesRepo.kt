package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.db.IRepositoriesCache
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus

class RetrofitGithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val repositoriesCache: IRepositoriesCache
) : IGithubRepositoriesRepo {

    override fun getRepository(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getListRepository(url)
                        .flatMap { repositories ->
                            repositoriesCache.getLocalRepositories(user, repositories)
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                repositoriesCache.saveLocalRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}
