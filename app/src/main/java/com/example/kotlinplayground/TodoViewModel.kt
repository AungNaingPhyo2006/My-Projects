package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {
    private var _todoList = MutableLiveData<List<Todo>>()
    var todoList : LiveData<List<Todo>> = _todoList

    fun getAllTodo() {
        _todoList.value = TodoManager.getAllTodo().reversed()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title : String){
        TodoManager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id : Int){
        TodoManager.deleteTodo(id)
        getAllTodo()
    }

}