package com.example.azura_tic.Catatan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.azura_tic.databinding.ItemNoteBinding

class CatatanAdapter(
    private val onEdit: (CatatanModel) -> Unit,
    private val onDelete: (CatatanModel) -> Unit
) : RecyclerView.Adapter<CatatanAdapter.NoteViewHolder>() {
    private val notes = mutableListOf<CatatanModel>()

    class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content
        holder.binding.btnEdit.setOnClickListener { onEdit(note) }
        holder.binding.btnDelete.setOnClickListener { onDelete(note) }
    }

    override fun getItemCount(): Int = notes.size
    fun updateNotes(newNotes: List<CatatanModel>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}