package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.model.EditedNote

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edit_note)
        setContentView(binding.root)
        init()
        onClickListerners()
    }

    private fun onClickListerners() {
        binding.saveButton.setOnClickListener {
            var notesItem = EditedNote(binding.notesTitleTv.text.toString(), binding.notesDateTv.text.toString(),binding.notesDescTv.text.toString())
            NotesManager.setEditedNoteList(notesItem)
            val intent = Intent(this,NotesListActivity::class.java)
            startActivity(intent)
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
    //one way I thought of was  to see if the exiting note has any change. If does not have,then the save button should be hidden.
}