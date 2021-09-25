package ru.nifontbus.testmvp.models.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.db.GithubDatabase
import ru.nifontbus.testmvp.models.db.RoomGithubRepository
import ru.nifontbus.testmvp.models.db.RoomGithubUser
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus

class GithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) : IGithubUsersRepo {

    //    override fun getUsers(): Single<List<GithubUser>> = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers()
                    .flatMap { users ->
                        Single.fromCallable {
                            val roomUsers = users.map { user ->
                                RoomGithubUser(
                                    user.id ?: "",
                                    user.login ?: "",
                                    user.avatarUrl,
                                    user.reposUrl ?: ""
                                )
                            }
                            db.userDao.insert(roomUsers)
                            users
                        }
                    }
            } else {
                Single.fromCallable {
                    db.userDao.getAll().map { roomUser ->
                        GithubUser(
                            roomUser.id,
                            roomUser.login,
                            roomUser.avatarUrl,
                            roomUser.reposUrl
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())

    //    = api.getListRepository(urlRepository).subscribeOn(Schedulers.io())
    override fun getRepository(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getListRepository(url)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                                    ?: throw RuntimeException("No such user in cache")
                                val roomRepos = repositories.map {
                                    RoomGithubRepository(
                                        it.id ?: "",
                                        it.name ?: "",
                                        it.forksCount,
                                        roomUser.id
                                    )
                                }
                                db.repositoryDao.insert(roomRepos)
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id)
                        .map { GithubRepository(it.id, it.name, it.forksCount) }
                }

            }
        }.subscribeOn(Schedulers.io())

}
