package ru.nifontbus.testmvp.models.db.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nifontbus.testmvp.models.db.RoomGithubRepository

@Dao
interface RepositoryDao : BaseDao<RoomGithubRepository> {

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): List<RoomGithubRepository>

    @Query("SELECT * FROM RoomGithubRepository WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubRepository>

}