package com.dungnd.mvvm.data.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dungnd.mvvm.data.local.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("select * from User")
    suspend fun getAllUser(): List<User>?

    @Query("select * from User where email = :email")
    suspend fun getUser(email: String): User?
}