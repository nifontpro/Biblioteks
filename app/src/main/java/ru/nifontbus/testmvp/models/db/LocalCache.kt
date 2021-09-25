package ru.nifontbus.testmvp.models.db

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

class LocalCache: ILocalCache {

    private val db = App.getRoomDb()

    override fun saveUsersToDb(users: List<GithubUser>): Single<List<GithubUser>> =
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

    override fun getLocalUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id,
                roomUser.login,
                roomUser.avatarUrl,
                roomUser.reposUrl
            )
        }
    }

    override fun getLocalRepositories(
        user: GithubUser,
        repositories: List<GithubRepository>
    ): Single<List<GithubRepository>> = Single.fromCallable {
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

    override fun saveLocalRepositories(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            db.repositoryDao.findForUser(roomUser.id)
                .map { GithubRepository(it.id, it.name, it.forksCount) }
        }
}