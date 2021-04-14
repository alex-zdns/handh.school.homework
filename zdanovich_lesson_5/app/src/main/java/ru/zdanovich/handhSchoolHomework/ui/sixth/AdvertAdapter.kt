package ru.zdanovich.handhSchoolHomework.ui.sixth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.AdvertItemBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Advert

class AdvertAdapter(
    private val advertItems: List<Advert>,
    private val clickListener: OnRecyclerItemClicked
) :
    RecyclerView.Adapter<AdvertAdapter.AdvertItemViewHolder>() {

    class AdvertItemViewHolder(val binding: AdvertItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(advertItem: Advert) {
            binding.adItemIcon.setImageResource(advertItem.image)
            binding.adItemTitle.text = advertItem.title
            binding.adItemMessage.text = advertItem.message
            binding.adItemAddress.text = advertItem.address
            binding.adItemLike.isVisible = advertItem.isLike
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdvertItemBinding.inflate(layoutInflater, parent, false)
        return AdvertItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdvertItemViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(advertItems[position].title)
        }

        holder.binding.adMore.setOnClickListener {
            clickListener.onItemClick(advertItems[position].address)
        }

        return holder.onBind(advertItems[position])
    }


    override fun getItemCount(): Int = advertItems.size

    interface OnRecyclerItemClicked {
        fun onItemClick(title: String)
    }
}