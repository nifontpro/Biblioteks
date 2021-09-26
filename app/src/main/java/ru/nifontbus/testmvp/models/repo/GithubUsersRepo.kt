package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.db.ILocalCache
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus

class GithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val localCache: ILocalCache
) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    localCache.saveUsersToDb(users)
                }
            } else {
                localCache.getLocalUsers()
            }
        }.subscribeOn(Schedulers.io())

    override fun getRepository(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getListRepository(url)
                        .flatMap { repositories ->
                            localCache.getLocalRepositories(user, repositories)
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                localCache.saveLocalRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}
