package ru.zdanovich.handhSchoolHomework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.InfoItemBinding
import ru.zdanovich.handhSchoolHomework.databinding.InfoItemLongBinding
import ru.zdanovich.handhSchoolHomework.models.BaseInfoItem
import ru.zdanovich.handhSchoolHomework.models.DetailInfoItem

class InfoItemAdapter(
    private val infoItems: List<BaseInfoItem>,
    spanCount: Int,
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<InfoItemAdapter.InfoItemViewHolder>() {
    private val itemTypes: List<Int> = getIndexesItemInfo(spanCount)

    private fun getIndexesItemInfo(spanCount: Int): List<Int> {
        val mutableItemTypes = MutableList(infoItems.size) { ITEM_LONG }
        var sum = 0
        for (i in infoItems.indices) {
            sum = if (infoItems[i] is DetailInfoItem) sum + 1 else 0

            if (sum == spanCount) {
                for (j in (i - spanCount + 1)..i) {
                    mutableItemTypes[j] = ITEM_SHORT
                }
                sum = 0
            }
        }

        return mutableItemTypes
    }

    abstract class InfoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun onBind(infoItem: BaseInfoItem)

        fun setMessage(infoItem: BaseInfoItem, messageView: TextView) {
            messageView.let {
                if (infoItem is DetailInfoItem) {
                    val style =
                        if (infoItem.hasDebt) R.style.TextAppearance_MaterialComponents_Body1_MessageHasDept
                        else R.style.TextAppearance_MaterialComponents_Body1_MessageHasNotDept

                    it.setTextAppearance(style)
                    it.visibility = View.VISIBLE
                    it.text = infoItem.message
                    return
                }

                it.visibility = View.GONE
            }
        }

        fun setIconAndTitle(infoItem: BaseInfoItem, iconView: ImageView, titleView: TextView) {
            iconView.setImageResource(infoItem.icon)
            titleView.text = infoItem.title
        }
    }


    private class InfoItemShortViewHolder(private val binding: InfoItemBinding) :
        InfoItemViewHolder(binding.root) {

        override fun onBind(infoItem: BaseInfoItem) {
            setIconAndTitle(infoItem, binding.infoItemIcon, binding.infoItemTitle)
            setMessage(infoItem, binding.infoItemMessage)
        }
    }

    class InfoItemLongViewHolder(private val binding: InfoItemLongBinding) :
        InfoItemViewHolder(binding.root) {

        override fun onBind(infoItem: BaseInfoItem) {
            setIconAndTitle(infoItem, binding.infoItemLongIcon, binding.infoItemLongTitle)
            setMessage(infoItem, binding.infoItemLongMessage)
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
        holder.onBind(infoItems[position])

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(infoItems[position].title)
        }
    }


    interface OnRecyclerItemClicked {
        fun onItemClick(title: String)
    }

    companion object {
        const val ITEM_SHORT = 1
        const val ITEM_LONG = 0
    }
}