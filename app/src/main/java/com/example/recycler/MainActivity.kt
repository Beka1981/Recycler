package com.example.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter.setOnItemClickListener {
           Toast.makeText(this, "click delete", Toast.LENGTH_SHORT).show()
        }
        binding.rvUser.layoutManager = (LinearLayoutManager(this))
        adapter.setData(getFakeUsers())
        binding.rvUser.addItemDecoration(
            DividerItemDecoration(
                binding.rvUser.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvUser.adapter = adapter

    }

    private fun getFakeUsers(): List<User> {
        val data = ArrayList<User>()
        for (i in 1..20)
            data.add(User(i, "Username$i"))
        return data.toList()
    }

}