package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
   lateinit var rvTodo : RecyclerView
   lateinit var btnAddTodo : FloatingActionButton
   lateinit var rvAdapter: RVadapter
   lateinit var toDoList: ArrayList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodo = findViewById(R.id.rvMain)
        btnAddTodo = findViewById(R.id.addBtn)
        btnAddTodo.setOnClickListener {
            customDialog()
        }

        toDoList = arrayListOf(
            ToDo("To do 1"),
            ToDo("To do 2"),
            ToDo("To do 3")
        )

        rvAdapter = RVadapter(toDoList)
        rvTodo.adapter = rvAdapter
        rvTodo.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.del_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.del_item ->{
                rvAdapter.deleteItems()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun customDialog(){
        val dialogBuilder = AlertDialog.Builder(this)

        //val layout = LinearLayout(this)
        //layout.orientation = LinearLayout.VERTICAL

        val toDo = EditText(this)
        toDo.hint = "Enter to do item"
       // layout.addView(toDo)

        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                dialog, id -> rvAdapter.addItem(toDo.text.toString())
        })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New To Do")
        alert.setView(toDo)
        alert.show()
    }
}
