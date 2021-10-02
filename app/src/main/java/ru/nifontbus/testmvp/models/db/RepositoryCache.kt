package ru.nifontbus.testmvp.models.db

import io.reactivex.rxjava3.core.Single
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import javax.inject.Inject

class RepositoryCache : IRepositoriesCache {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var db: GithubDatabase

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