package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.notes.Adapter.NotesAdapter
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.model.EditedNote

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditNoteBinding
    var position : Int=0
    val notesListActivity= NotesListActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edit_note)
        setContentView(binding.root)
        position = intent.getIntExtra("position", 0) // 0 is the default value if the key is not found
        // Use the position as needed
        Log.e("logpos edn", position.toString())
        init()
        onClickListerners()
    }

    private fun onClickListerners() {
        binding.saveButton.setOnClickListener {
            if (intent.hasExtra("position")){
                var notesItem = EditedNote(
                    binding.notesTitleTv.text.toString(),
                    binding.notesDateTv.text.toString(),
                    binding.notesDescTv.text.toString()
                )
                notesListActivity.updateExisitingNote(notesItem,position)
                val intent = Intent(this, NotesListActivity::class.java)
                startActivity(intent)

            }else {
                var notesItem = EditedNote(
                    binding.notesTitleTv.text.toString(),
                    binding.notesDateTv.text.toString(),
                    binding.notesDescTv.text.toString()
                )
                NotesManager.setEditedNoteList(notesItem)
                val intent = Intent(this, NotesListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun init(){
       val toolbar = binding.editNoteToolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.back_button)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.notesTitleTv.setText(NotesManager.getEditedNote()?.title.toString())
        binding.notesDateTv.setText(NotesManager.getEditedNote()?.date.toString())
        binding.notesDescTv.setText(NotesManager.getEditedNote()?.description.toString())
    }

    //TODO: when we edit an existing note and click save, it gets added as a new note in RV -> this has to be rectified.
    //one way I thought of was  to see if the existing note has any change. If does not have,then the save button should be hidden.
    //

}