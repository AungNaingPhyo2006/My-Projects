package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinplayground.db.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {
    val todoDao = MainApplication.todoDatabase.getTodoDao()
    var todoList : LiveData<List<Todo>> = todoDao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
//    fun addTodo(title : String){
//        viewModelScope.launch (Dispatchers.IO){
//            todoDao.addTodo(Todo(title=title, createdAt = Date.from(Instant.now())))
//        }
//    }
    fun addTodo(packageName: String, operatorName: String, price: String, phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(
                Todo(
                    packageName = packageName,
                    operatorName = operatorName,
                    price = price,
                    phoneNumber = phoneNumber,
                    createdAt = Date.from(Instant.now())
                )
            )
        }
    }

    fun deleteTodo(id : Int){
        viewModelScope.launch (Dispatchers.IO){
            todoDao.deleteTodo(id)
        }
    }

}