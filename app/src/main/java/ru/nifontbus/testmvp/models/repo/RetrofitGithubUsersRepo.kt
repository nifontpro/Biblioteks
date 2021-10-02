package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.db.IUserCache
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userCache: IUserCache
) : IGithubUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { users ->
                    userCache.saveUsersToDb(users)
                }
            } else {
                userCache.getLocalUsers()
            }
        }.subscribeOn(Schedulers.io())
}
