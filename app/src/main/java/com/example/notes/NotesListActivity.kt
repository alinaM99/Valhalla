package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.Adapter.NotesAdapter
import com.example.notes.databinding.ActivityNotesListBinding
import com.example.notes.model.EditedNote

class NotesListActivity : AppCompatActivity(),NotesAdapter.OnItemClickListener {
    private lateinit var binding : ActivityNotesListBinding
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_notes_list)
        setContentView(binding.root)
        init()
        onClickListeners()
    }
 private fun init(){
     if (NotesManager.getEditedNoteList().isNullOrEmpty()){
         Log.e("NotesListActivity","no notes entered")
     }
     else{
         binding.notesRv.layoutManager = LinearLayoutManager(this)
         binding.notesRv.adapter=NotesAdapter(NotesManager.getEditedNoteList() as MutableList<EditedNote?>,this)
         binding.notesRv.adapter?.notifyDataSetChanged()
     }
 }
    private fun onClickListeners() {
        binding.AddButton.setOnClickListener{
            val intent = Intent(this,EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(note: EditedNote?, position : Int) {
     // Handle item click here
        //set to manager and give intent to edit text
        Log.e("NotesListActivity", note?.title.toString())
        NotesManager.setEditedNote(note)
        val intent=Intent(this, EditNoteActivity::class.java)
        intent.putExtra("position",position)
        Log.e("logpos", position.toString())
        startActivity(intent)

    }

    fun updateExisitingNote(note : EditedNote?, position: Int){
        adapter= NotesAdapter(NotesManager.getEditedNoteList() as MutableList<EditedNote?>,this)
        adapter.updateNoteList(position,note)
    }
}