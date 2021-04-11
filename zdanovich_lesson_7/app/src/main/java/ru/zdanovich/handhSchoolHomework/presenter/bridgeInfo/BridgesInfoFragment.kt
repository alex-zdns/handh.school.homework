package ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgeInfoBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgeAdapter

class BridgesInfoFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentBridgeInfoBinding? = null
    private val binding get() = _binding!!
    private var listenerBridgeInfo: BridgeInfoClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerBridgeInfo = context as? BridgeInfoClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBridgeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Bridge>(BRIDGE)?.let { bridge ->
            setupView(bridge)
        }

        binding.fgiBackButton.setOnClickListener {
            listenerBridgeInfo?.removeBridgeInfoFragment()
        }
    }

    private fun setupView(bridge: Bridge) {
        view?.let {
            val status = bridge.getBridgeStatus()

            val iconDrawable = when (status) {
                Bridge.BridgeStatus.Close -> R.drawable.ic_brige_late
                Bridge.BridgeStatus.Open -> R.drawable.ic_brige_normal
                Bridge.BridgeStatus.SoonClose -> R.drawable.ic_brige_soon
            }

            val imageURL = when (status) {
                Bridge.BridgeStatus.Close -> bridge.photoCloseUrl
                Bridge.BridgeStatus.Open,
                Bridge.BridgeStatus.SoonClose -> bridge.photoOpenUrl
            }

            Glide.with(it.context)
                .load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.fgiImage)

            binding.fbiBridgeCard.vhbStatusIcon.setImageResource(iconDrawable)
            binding.fbiBridgeCard.vhbBridgeName.text = bridge.name
            binding.fbiBridgeCard.vhbBridgeTimeDivorces.text =
                bridge.bridgeDivorcesTimes.joinToString(postfix = BridgeAdapter.BRIDGE_DIVORCES_TIME_POSTFIX) { time -> time.toUiString() }
            binding.fgiDescription.text = bridge.description
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerBridgeInfo = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val BRIDGE = "bridge"

        fun newInstance(bridge: Bridge): BridgesInfoFragment =
            BridgesInfoFragment().apply {
                val args = Bundle()
                args.putParcelable(BRIDGE, bridge)
                arguments = args
            }
    }


    interface BridgeInfoClickListener {
        fun removeBridgeInfoFragment()
    }
}