package ru.zdanovich.handhSchoolHomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.InfoItemBinding
import ru.zdanovich.handhSchoolHomework.databinding.InfoItemLongBinding
import ru.zdanovich.handhSchoolHomework.models.BaseInfoItem
import ru.zdanovich.handhSchoolHomework.models.DetailInfoItem

class InfoItemAdapter(
    private val infoItems: List<BaseInfoItem>
) : RecyclerView.Adapter<InfoItemAdapter.InfoItemViewHolder>() {

    abstract class InfoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private class InfoItemShortViewHolder(private val binding: InfoItemBinding) :
        InfoItemViewHolder(binding.root) {

        fun bind(detailInfoItem: DetailInfoItem) {
            binding.infoItemIcon.setImageResource(detailInfoItem.icon)
            binding.infoItemTitle.text = detailInfoItem.title
            binding.infoItemMessage.let {
                if (detailInfoItem.hasDebt) {
                    it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasDept)
                } else {
                    it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasNotDept)
                }

                it.text = detailInfoItem.message
            }
        }
    }

    class InfoItemLongViewHolder(private val binding: InfoItemLongBinding) :
        InfoItemViewHolder(binding.root) {

        fun bind(infoItem: BaseInfoItem) {
            binding.infoItemLongIcon.setImageResource(infoItem.icon)
            binding.infoItemLongTitle.text = infoItem.title


            if (infoItem is DetailInfoItem) {
                binding.infoItemLongMessage.let {
                    if (infoItem.hasDebt) {
                        it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasDept)
                    } else {
                        it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasNotDept)
                    }

                    it.text = infoItem.message
                }
            } else {
                binding.infoItemLongMessage.visibility = View.GONE
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemViewHolder {

        return when (viewType) {
            ITEM_SHORT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = InfoItemBinding.inflate(layoutInflater, parent, false)
                InfoItemShortViewHolder(binding)
            }
            ITEM_LONG -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = InfoItemLongBinding.inflate(layoutInflater, parent, false)
                InfoItemLongViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemCount(): Int = infoItems.size

    override fun getItemViewType(position: Int): Int {
        if (infoItems[position] is DetailInfoItem) {

            if (position == 6) {
                return ITEM_LONG
            }

            return ITEM_SHORT
        } else {
            return ITEM_LONG
        }
    }

    companion object {
        const val ITEM_SHORT = 0
        const val ITEM_LONG = 1
    }

    override fun onBindViewHolder(holder: InfoItemViewHolder, position: Int) {
        when (holder) {
            is InfoItemShortViewHolder -> holder.bind(infoItems[position] as DetailInfoItem)
            is InfoItemLongViewHolder -> holder.bind(infoItems[position])
        }
    }
}