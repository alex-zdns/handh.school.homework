package ru.zdanovich.handhSchoolHomework.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.InfoItemBinding

class InfoItemAdapter(
    private val infoItems: List<InfoItem>
) : RecyclerView.Adapter<InfoItemAdapter.InfoItemViewHolder>() {

    class InfoItemViewHolder(private val infoItemBinding: InfoItemBinding) :
        RecyclerView.ViewHolder(infoItemBinding.root) {

        fun bind(detailInfoItem: DetailInfoItem) {
            infoItemBinding.infoItemIcon.setImageResource(detailInfoItem.icon)
            infoItemBinding.infoItemTitle.text = detailInfoItem.title
            infoItemBinding.infoItemMessage.let {
                if (detailInfoItem.hasDebt) {
                    it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasDept)
                } else {
                    it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasNotDept)
                }

                it.text = detailInfoItem.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val infoItemBinding = InfoItemBinding.inflate(layoutInflater, parent, false)
        return InfoItemViewHolder(infoItemBinding)
    }

    override fun onBindViewHolder(holder: InfoItemViewHolder, position: Int) {
        holder.bind(infoItems[position] as DetailInfoItem)
    }

    override fun getItemCount(): Int = infoItems.size
}