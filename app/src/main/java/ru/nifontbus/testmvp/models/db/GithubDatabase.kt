package ru.nifontbus.testmvp.models.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nifontbus.testmvp.models.db.dao.RepositoryDao
import ru.nifontbus.testmvp.models.db.dao.UserDao

@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class
    ],
    version = 1
)

abstract class GithubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "github_users.db"
    }
}