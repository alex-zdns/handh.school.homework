package ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgeInfoBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo.BridgeInfoViewModel.BridgeState
import ru.zdanovich.handhSchoolHomework.presenter.helpers.onBind

class BridgeInfoFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentBridgeInfoBinding? = null
    private val binding get() = _binding!!

    private var bridgeId = 1
    private val viewModel: BridgeInfoViewModel by viewModels { BridgeInfoViewModelFactory() }

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
        viewModel.state.observe(this.viewLifecycleOwner, this::setState)
        parseArgs()

        binding.fgiBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun parseArgs() {
        val args: BridgeInfoFragmentArgs by navArgs()
        bridgeId = args.bridgeId
        viewModel.getBridge(bridgeId)
    }

    private fun setState(state: BridgeState) =
        when (state) {
            is BridgeState.Default,
            is BridgeState.Loading -> {
                setLoading(true)
            }
            is BridgeState.Error.Internet -> {
                setLoading(false)
                showDialog(getString(R.string.error_internet_message))
            }
            is BridgeState.Error.Other -> {
                setLoading(false)
                showDialog(getString(R.string.other_error_message))
            }
            is BridgeState.Success -> {
                setupView(state.bridge)
                setLoading(false)
            }
        }

    private fun setLoading(loading: Boolean) {
        binding.fgiLoader.isVisible = loading
    }

    private fun setupView(bridge: Bridge) {
        view?.let {
            val imageURL = when (bridge.getBridgeStatus()) {
                Bridge.BridgeStatus.Close -> bridge.photoCloseUrl
                Bridge.BridgeStatus.Open,
                Bridge.BridgeStatus.SoonClose -> bridge.photoOpenUrl
            }

            Glide.with(it.context)
                .load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.fgiImage)

            binding.fgiDescription.text = bridge.description

            binding.fbiBridgeCard.onBind(bridge)
        }
    }

    private fun showDialog(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.Error))
            .setMessage(message)
            .setIcon(R.drawable.ic_error)
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                viewModel.getBridge(bridgeId)
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}