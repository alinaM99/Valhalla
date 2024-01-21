package com.example.notes

import com.example.notes.model.EditedNote

object NotesManager {
     var editedNotes: MutableList<EditedNote?> = arrayListOf()
      var notes: EditedNote? = null

    fun getEditedNoteList(): List<EditedNote?> {
        return editedNotes
    }

    fun setEditedNoteList(notes: EditedNote?) {
         editedNotes.add(notes)
    }
     fun setEditedNote(note : EditedNote?){
         notes=note
     }
    fun getEditedNote() : EditedNote?{
        return notes
    }
}
