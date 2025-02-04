package com.example.kotlinplayground

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Date
import java.time.Instant
data class Todo(
    var id : Int,
    var title : String,
    var createdAt : Date
)



@RequiresApi(Build.VERSION_CODES.O)
fun getFakeTodo() : List<Todo>{
    return listOf(
         Todo(id=1, title= "First Todo",createdAt = Date.from(Instant.now())),
         Todo(id=2, title= "Second Todo", createdAt = Date.from(Instant.now())),
         Todo(id=3, title =  "Third Todo", createdAt = Date.from(Instant.now()))
        )
}
