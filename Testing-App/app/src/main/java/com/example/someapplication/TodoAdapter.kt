package com.example.someapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewholder>() {
    class TodoViewholder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewholder {
        return TodoViewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent, false
            )
        )
    }

    private fun toggleStrikeThrough(textView: TextView, checkBox: Boolean) {
        if (checkBox) {
            textView.paintFlags = textView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    public fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo() {
        todos.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewholder, position: Int) {
        val curr_item = todos[position]
        holder.itemView.apply {
            val titleText = findViewById<TextView>(R.id.tvTodoTitle)
            val checkBox = findViewById<CheckBox>(R.id.checkBox)
            titleText.text = curr_item.title
            checkBox.isChecked = curr_item.isChecked
            toggleStrikeThrough(titleText, curr_item.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(titleText, isChecked)
                curr_item.isChecked = !curr_item.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}