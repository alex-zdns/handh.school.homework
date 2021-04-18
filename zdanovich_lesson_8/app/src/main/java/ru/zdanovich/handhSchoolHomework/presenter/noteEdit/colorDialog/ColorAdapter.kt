package ru.zdanovich.handhSchoolHomework.presenter.noteEdit.colorDialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ColorItemBinding
import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor

class ColorAdapter(private val colors: List<NoteColor>) :
    RecyclerView.Adapter<ColorAdapter.ColorItemViewHolder>() {

    class ColorItemViewHolder(private val binding: ColorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteColor: NoteColor) {
            getDrawable(binding.colorItemCircle.context, R.drawable.color_item)?.let {
                it.setTint(noteColor.backgroundColor)
                binding.colorItemCircle.setImageDrawable(it)
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
    }

    override fun getItemCount(): Int = colors.size

}