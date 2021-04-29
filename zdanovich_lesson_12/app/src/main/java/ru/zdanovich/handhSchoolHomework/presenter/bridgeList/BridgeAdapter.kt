package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.databinding.ViewHolderBridgeBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.helpers.onBind

class BridgeAdapter(
    private val bridges: List<Bridge>,
    private val clickListener: OnRecyclerBridgeClicked
) :
    RecyclerView.Adapter<BridgeAdapter.BridgeItemViewHolder>() {

    class BridgeItemViewHolder(private val binding: ViewHolderBridgeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(bridge: Bridge) {
            binding.onBind(bridge)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBridgeBinding.inflate(layoutInflater, parent, false)
        return BridgeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BridgeItemViewHolder, position: Int) {
        holder.onBind(bridges[position])
        holder.itemView.setOnClickListener {
            clickListener.onBridgeClick(bridges[position])
        }
    }

    override fun getItemCount(): Int = bridges.size

    interface OnRecyclerBridgeClicked {
        fun onBridgeClick(bridge: Bridge)
    }

    companion object {
        const val BRIDGE_DIVORCES_TIME_POSTFIX = "  "
    }
}