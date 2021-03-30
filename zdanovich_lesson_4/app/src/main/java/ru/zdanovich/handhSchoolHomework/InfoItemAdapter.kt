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
    private val infoItems: List<BaseInfoItem>,
    private val spanCount: Int
) : RecyclerView.Adapter<InfoItemAdapter.InfoItemViewHolder>() {
    private val itemTypes: List<Int> = getIndexesItemInfo()

    private fun getIndexesItemInfo(): List<Int> {
        val mutableItemTypes = MutableList(infoItems.size) { ITEM_LONG }
        var sum = 0
        for (i in infoItems.indices) {
            if (infoItems[i] is DetailInfoItem) {
                sum++
            } else {
                sum = 0
            }

            if (sum == spanCount) {
                for (j in (i - spanCount + 1)..i) {
                    mutableItemTypes[j] = ITEM_SHORT
                }
                sum = 0
            }
        }

        return mutableItemTypes
    }

    abstract class InfoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private class InfoItemShortViewHolder(private val binding: InfoItemBinding) :
        InfoItemViewHolder(binding.root) {

        fun onBind(detailInfoItem: DetailInfoItem) {
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

        fun onBind(infoItem: BaseInfoItem) {
            binding.infoItemLongIcon.setImageResource(infoItem.icon)
            binding.infoItemLongTitle.text = infoItem.title


            if (infoItem is DetailInfoItem) {
                binding.infoItemLongMessage.let {
                    if (infoItem.hasDebt) {
                        it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasDept)
                    } else {
                        it.setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1_MessageHasNotDept)
                    }

                    binding.infoItemLongMessage.visibility = View.VISIBLE
                    it.text = infoItem.message
                }
            } else {
                binding.infoItemLongMessage.visibility = View.GONE
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM_SHORT -> {
                val binding = InfoItemBinding.inflate(layoutInflater, parent, false)
                InfoItemShortViewHolder(binding)
            }
            ITEM_LONG -> {
                val binding = InfoItemLongBinding.inflate(layoutInflater, parent, false)
                InfoItemLongViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = infoItems.size
    override fun getItemViewType(position: Int): Int = itemTypes[position]

    override fun onBindViewHolder(holder: InfoItemViewHolder, position: Int) {
        when (holder) {
            is InfoItemShortViewHolder -> holder.onBind(infoItems[position] as DetailInfoItem)
            is InfoItemLongViewHolder -> holder.onBind(infoItems[position])
        }
    }

    companion object {
        const val ITEM_SHORT = 1
        const val ITEM_LONG = 0
    }
}