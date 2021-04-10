package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ViewHolderBridgeBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

class BridgeAdapter(
    private val bridges: List<Bridge>,
    //private val clickListener: OnRecyclerBridgeClicked
) :
    RecyclerView.Adapter<BridgeAdapter.BridgeItemViewHolder>() {

    class BridgeItemViewHolder(private val binding: ViewHolderBridgeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(bridge: Bridge) {

            val iconDrawable = when (bridge.getBridgeStatus()) {
                Bridge.BridgeStatus.Close -> R.drawable.ic_brige_late
                Bridge.BridgeStatus.Open -> R.drawable.ic_brige_normal
                Bridge.BridgeStatus.SoonClose -> R.drawable.ic_brige_soon
            }

            binding.vhbStatusIcon.setImageResource(iconDrawable)
            binding.vhbBridgeName.text = bridge.name
            binding.vhbBridgeTimeDivorces.text =
                bridge.bridgeDivorcesTimes.joinToString(postfix = BRIDGE_DIVORCES_TIME_POSTFIX) { it.toUiString() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBridgeBinding.inflate(layoutInflater, parent, false)
        return BridgeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BridgeItemViewHolder, position: Int) {
        return holder.onBind(bridges[position])
    }


    override fun getItemCount(): Int = bridges.size

    interface OnRecyclerBridgeClicked {
        fun onItemClick(title: String)
    }

    companion object {
        const val BRIDGE_DIVORCES_TIME_POSTFIX = "  "
    }
}