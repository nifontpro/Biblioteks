package ru.nifontbus.testmvp.models.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nifontbus.testmvp.models.db.RoomGithubRepository
import ru.nifontbus.testmvp.models.db.RoomGithubUser

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
}