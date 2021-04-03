package ru.zdanovich.handhSchoolHomework.ui.sixth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.AdvertItemBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Advert

class AdvertAdapter(private val advertItems: List<Advert>) :
    RecyclerView.Adapter<AdvertAdapter.AdvertItemViewHolder>() {

    class AdvertItemViewHolder(private val binding: AdvertItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(advertItem: Advert) {
            binding.adItemIcon.setImageResource(advertItem.image)
            binding.adItemTitle.text = advertItem.title
            binding.adItemMessage.text = advertItem.message
            binding.adItemAddress.text = advertItem.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdvertItemBinding.inflate(layoutInflater, parent, false)
        return AdvertItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdvertItemViewHolder, position: Int) =
        holder.onBind(advertItems[position])

    override fun getItemCount(): Int = advertItems.size
}