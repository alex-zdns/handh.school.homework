package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgesListBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge

class BridgesListFragment : androidx.fragment.app.Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var _binding: FragmentBridgesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BridgesListViewModel by viewModels { BridgesListViewModelFactory() }

    private val clickListener = object : BridgeAdapter.OnRecyclerBridgeClicked {
        override fun onBridgeClick(bridge: Bridge) {
            val action = BridgesListFragmentDirections.actionBridgesListFragmentToBridgeInfoFragment(bridge.id)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBridgesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.blfLoader.setOnRefreshListener(this)
        setupToolbar()

        viewModel.state.observe(this.viewLifecycleOwner, this::setState)

        if (viewModel.state.value is BridgeListState.Default) viewModel.getBridges()
    }

    private fun setupToolbar() {
        binding.blfToolbar.apply {
            inflateMenu(R.menu.fragment_bridges_list_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.action_fbl_to_map) {
                    findNavController().navigate(R.id.action_bridgesListFragment_to_bridgesMapFragment)
                    return@setOnMenuItemClickListener true
                }

                return@setOnMenuItemClickListener false
            }
        }
    }

    private fun setState(bridgeListState: BridgeListState) =
        when (bridgeListState) {
            is BridgeListState.Default,
            is BridgeListState.Loading -> {
                setLoading(true)
            }
            is BridgeListState.Error.Internet -> {
                setLoading(false)
                showToast(getString(R.string.error_internet_message))
            }
            is BridgeListState.Error.Other -> {
                setLoading(false)
                showToast(getString(R.string.other_error_message))
            }
            is BridgeListState.Success -> {
                binding.blfRecyclerView.adapter =
                    BridgeAdapter(bridgeListState.bridges.values.toList(), clickListener)
                setLoading(false)
            }
        }

    private fun setLoading(loading: Boolean) {
        binding.blfLoader.isRefreshing = loading
    }

    override fun onRefresh() {
        viewModel.getBridges()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}