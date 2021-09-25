package ru.nifontbus.testmvp.models.db.dao

import androidx.room.Dao
import androidx.room.Query
import ru.nifontbus.testmvp.models.db.RoomGithubUser

@Dao
interface UserDao: BaseDao<RoomGithubUser> {

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUser?
}
