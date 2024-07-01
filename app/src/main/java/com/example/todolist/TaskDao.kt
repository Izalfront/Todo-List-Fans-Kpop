package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (task: Task)

    @Update
    suspend fun update (task: Task)

    @Delete
    suspend fun delete (task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTask(): LiveData<List<Task>>
}