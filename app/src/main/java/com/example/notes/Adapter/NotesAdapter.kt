package com.example.notes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.EditedNote

class NotesAdapter(private val notesList : MutableList<EditedNote?>,
                   private val listener : OnItemClickListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(note: EditedNote?, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_list_item, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        val currentNote = notesList[position]
        if (currentNote != null) {
            holder.bind(currentNote)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.notesTitleTv)
        private val date: TextView = itemView.findViewById(R.id.notesDateTv)
        private val content: TextView = itemView.findViewById(R.id.notesDescTv)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedNote = notesList[position]
                    listener.onItemClick(clickedNote,position)
                }
            }
        }

        fun bind(note: EditedNote) {
            title.text = note.title
            date.text = note.date
            content.text = note.description
        }
    }

    fun updateNoteList(position: Int, updatedNote : EditedNote?){
        notesList[position]?.date= updatedNote?.date.toString()
        notesList[position]?.title= updatedNote?.title.toString()
        notesList[position]?.description= updatedNote?.description.toString()
        notifyItemChanged(position)
    }
}