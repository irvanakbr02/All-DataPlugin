package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.TodoAdapter
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Todo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var todoList = mutableListOf(
                Todo("Irvan Akbar","19090099","Mobile",false),
                Todo("Dhani Gaming","19090118","Mobile",false),
                Todo("Musnadil","19090041","Mobile",false),
                Todo("Arip","19090010","Web",false),
                Todo("Ilham","19090069","Tak punya",false),
                Todo("Kurniawan","19090001","Mobile",false)

        )
        val adapter = TodoAdapter(todoList)
        binding.rvTodoList.adapter = adapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            val nama = binding.et1.text.toString()
            val nim = binding.et2.text.toString()
            val divisi = binding.et3.text.toString()
            val todo = Todo(nama,nim,divisi, false)
            todoList.add(todo)
            adapter.notifyDataSetChanged()
            adapter.notifyItemInserted(todoList.size-1)
        }
    }
}