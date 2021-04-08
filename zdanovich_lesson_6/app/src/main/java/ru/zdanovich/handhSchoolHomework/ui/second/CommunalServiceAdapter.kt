package ru.zdanovich.handhSchoolHomework.ui.second

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.CommunalServiceCardBinding
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalService

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
        return CommunalServiceItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunalServiceItemViewHolder, position: Int) {
        holder.onBind(communalServices[position])
    }

    override fun getItemCount(): Int = communalServices.size
}