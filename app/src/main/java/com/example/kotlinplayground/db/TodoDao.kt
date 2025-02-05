package com.example.kotlinplayground.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinplayground.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO ORDER BY createdAt DESC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo:Todo)

    @Query("Delete FROM Todo Where id = :id")
    fun deleteTodo(id:Int)
}