package ru.zdanovich.handhSchoolHomework.ui.second

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.CommunalServiceCardElectroBinding
import ru.zdanovich.handhSchoolHomework.databinding.CommunalServiceCardWatterBinding
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalService
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalServiceType

class CommunalServiceAdapter(private val communalServices: List<CommunalService>) :
    RecyclerView.Adapter<CommunalServiceAdapter.CommunalServiceItemViewHolder>() {

    abstract class CommunalServiceItemViewHolder(
        private val icon: ImageView,
        private val title: TextView,
        private val accountId: TextView,
        private val message: TextView,
        root: View
    ) :
        RecyclerView.ViewHolder(root) {
        open fun onBind(item: CommunalService) {
            icon.setImageResource(item.icon)
            title.text = item.title
            accountId.text = item.accountId.toString()

            val style =
                if (item.hasDept) R.style.CommunalServiceCardMessageHasDebt
                else R.style.CommunalServiceCardMessageNoDebt

            message.setTextAppearance(style)
            message.text = Html.fromHtml(item.messageInHTML, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    class CommunalServiceItemWatterViewHolder(private val binding: CommunalServiceCardWatterBinding) :
        CommunalServiceItemViewHolder(
            binding.cswIcon,
            binding.cswTitle,
            binding.cswAccountId,
            binding.cswMessage,
            binding.root
        ) {
        override fun onBind(item: CommunalService) {
            super.onBind(item)
            binding.cswNewValues.inputFieldTitle.text =
                itemView.resources.getString(R.string.watter_input_title)
        }
    }

    class CommunalServiceItemElectroViewHolder(private val binding: CommunalServiceCardElectroBinding) :
        CommunalServiceItemViewHolder(
            binding.cseIcon,
            binding.cseTitle,
            binding.cseAccountId,
            binding.cseMessage,
            binding.root
        ) {
        override fun onBind(item: CommunalService) {
            super.onBind(item)

            binding.cseValueDay.inputFieldTitle.text =
                itemView.resources.getString(R.string.electro_input_title_day)
            binding.cseValueNight.inputFieldTitle.text =
                itemView.resources.getString(R.string.electro_input_title_night)
            binding.cseValuePeak.inputFieldTitle.text =
                itemView.resources.getString(R.string.electro_input_title_peak)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunalServiceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            WATTER_TYPE -> {
                val binding =
                    CommunalServiceCardWatterBinding.inflate(layoutInflater, parent, false)
                CommunalServiceItemWatterViewHolder(binding)
            }
            ELETRO_TYPE -> {
                val binding =
                    CommunalServiceCardElectroBinding.inflate(layoutInflater, parent, false)
                CommunalServiceItemElectroViewHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (communalServices[position].type) {
            is CommunalServiceType.Electricity -> ELETRO_TYPE
            is CommunalServiceType.Watter -> WATTER_TYPE
        }
    }

    override fun onBindViewHolder(holder: CommunalServiceItemViewHolder, position: Int) {
        holder.onBind(communalServices[position])
    }

    override fun getItemCount(): Int = communalServices.size

    companion object {
        const val WATTER_TYPE = 0
        const val ELETRO_TYPE = 1
    }
}