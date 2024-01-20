package com.example.notes

import com.example.notes.model.EditedNote

object NotesManager {
     var editedNotes: MutableList<EditedNote?> = arrayListOf()

    fun getEditedNote(): List<EditedNote?> {
        return editedNotes
    }

    fun setEditedNote(notes: EditedNote?) {
         editedNotes.add(notes)
    }
}
