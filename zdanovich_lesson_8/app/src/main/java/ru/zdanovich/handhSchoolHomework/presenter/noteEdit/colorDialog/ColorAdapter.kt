package ru.zdanovich.handhSchoolHomework.presenter.noteEdit.colorDialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ColorItemBinding
import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

class ColorAdapter(
    private val colors: List<NoteColor>,
    private val currentColor: NoteColor,
    private val clickListener: OnRecyclerNoteColorItemClicked
) : RecyclerView.Adapter<ColorAdapter.ColorItemViewHolder>() {

    inner class ColorItemViewHolder(private val binding: ColorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteColor: NoteColor) {
            getDrawable(binding.colorItemCircle.context, R.drawable.color_item)?.let {
                it.setTint(noteColor.backgroundColor)
                binding.colorItemCircle.setImageDrawable(it)
            }

            if (currentColor == noteColor) {
                binding.colorItemMark.isVisible = true
                binding.colorItemMark.setColorFilter(noteColor.textColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ColorItemBinding.inflate(layoutInflater, parent, false)
        return ColorItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorItemViewHolder, position: Int) {
        holder.onBind(colors[position])
        holder.itemView.setOnClickListener {
            clickListener.onNoteColorClick(colors[position])
        }
    }

    override fun getItemCount(): Int = colors.size

    interface OnRecyclerNoteColorItemClicked {
        fun onNoteColorClick(noteColor: NoteColor)
    }
}