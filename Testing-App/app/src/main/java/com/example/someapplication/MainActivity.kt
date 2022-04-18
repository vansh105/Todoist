package com.example.someapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter:TodoAdapter
    private val logtag=MainActivity::class.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter=TodoAdapter(mutableListOf())
        val recycler=findViewById<RecyclerView>(R.id.recycler_view)
        recycler.adapter=todoAdapter
        recycler.layoutManager=LinearLayoutManager(this)
        val addButton=findViewById<Button>(R.id.btnAddItem)
        val deleteButton=findViewById<Button>(R.id.btnDeleteDone)
        addButton.setOnClickListener {
            val text=findViewById<EditText>(R.id.etTodoTitle)
            val temp=Todo(text.text.toString())
            todoAdapter.addTodo(temp)
            text.text.clear()
        }
        deleteButton.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}