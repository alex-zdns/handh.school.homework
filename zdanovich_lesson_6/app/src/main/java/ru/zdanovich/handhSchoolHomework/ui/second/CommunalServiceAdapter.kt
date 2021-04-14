package ru.zdanovich.handhSchoolHomework.ui.second

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.CommunalServiceCardBinding
import ru.zdanovich.handhSchoolHomework.databinding.InputFieldBinding
import ru.zdanovich.handhSchoolHomework.databinding.InputFieldElectroBinding
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalService
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalServiceType

class CommunalServiceAdapter(private val communalServices: List<CommunalService>) :
    RecyclerView.Adapter<CommunalServiceAdapter.CommunalServiceItemViewHolder>() {

    class CommunalServiceItemViewHolder(private val binding: CommunalServiceCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CommunalService) {
            binding.cscIcon.setImageResource(item.icon)
            binding.cscTitle.text = item.title
            binding.cscAccountId.text = item.accountId.toString()

            val style =
                if (item.hasDept) R.style.CommunalServiceCardMessageHasDebt
                else R.style.CommunalServiceCardMessageNoDebt

            binding.cscMessage.setTextAppearance(style)
            binding.cscMessage.text = Html.fromHtml(item.messageInHTML, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunalServiceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CommunalServiceCardBinding.inflate(layoutInflater, parent, false)
        val layoutInflaterInput = LayoutInflater.from(binding.cscInputField.context)

        when (viewType) {
            WATTER_TYPE -> {
                val bindingInput =
                    InputFieldBinding.inflate(layoutInflaterInput, binding.cscInputField, true)
                bindingInput.inputFieldTitle.text =
                    parent.resources.getString(R.string.watter_input_title)
            }
            ELETRO_TYPE -> {
                val bindingInput = InputFieldElectroBinding.inflate(
                    layoutInflaterInput,
                    binding.cscInputField,
                    true
                )
                bindingInput.cseValueDay.inputFieldTitle.text =
                    parent.resources.getString(R.string.electro_input_title_day)
                bindingInput.cseValueNight.inputFieldTitle.text =
                    parent.resources.getString(R.string.electro_input_title_night)
                bindingInput.cseValuePeak.inputFieldTitle.text =
                    parent.resources.getString(R.string.electro_input_title_peak)
            }
            else -> throw IllegalArgumentException()
        }

        return CommunalServiceItemViewHolder(binding)
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