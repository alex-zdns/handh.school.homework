package ru.zdanovich.handhSchoolHomework.presenter.notesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.NoteItemBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Note

class NoteAdapter(
    private val clickListener: OnRecyclerNoteItemClicked) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener.onNoteClick(getItem(position))
        }

        holder.itemView.setOnLongClickListener {
            clickListener.onNoteLongClick(getItem(position))
            true
        }
    }

    class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.noteItemTitle.apply {
                isVisible = note.title.isNotEmpty()
                text = note.title
            }

            binding.noteItemBody.text = note.body
            binding.root.setCardBackgroundColor(note.noteColor.backgroundColor)
            binding.noteItemBody.setTextColor(note.noteColor.textColor)
            binding.noteItemTitle.setTextColor(note.noteColor.textColor)
        }
    }

    private class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem == newItem
    }

    interface OnRecyclerNoteItemClicked {
        fun onNoteClick(note: Note)
        fun onNoteLongClick(note: Note)
    }
}