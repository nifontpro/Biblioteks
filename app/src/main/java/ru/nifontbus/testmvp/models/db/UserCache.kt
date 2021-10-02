package ru.nifontbus.testmvp.models.db

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import javax.inject.Inject

class UserCache(val db: GithubDatabase): IUserCache {

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
}