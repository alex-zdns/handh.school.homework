package ru.zdanovich.handhSchoolHomework.presenter.bridgeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgesListBinding
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListViewModel.State

class BridgesListFragment : androidx.fragment.app.Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var _binding: FragmentBridgesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BridgesListViewModel by viewModels { BridgesListViewModelFactory() }

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
        viewModel.state.observe(this.viewLifecycleOwner, this::setState)

        if (viewModel.state.value is State.Default) viewModel.getBridges()
    }

    private fun setState(state: State) =
        when (state) {
            is State.Default,
            is State.Loading -> {
                setLoading(true)
            }
            is State.Error -> {
                setLoading(false)
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }
            is State.Success -> {
                binding.blfRecyclerView.adapter = BridgeAdapter(state.bridges)
                setLoading(false)
            }
        }

    private fun setLoading(loading: Boolean) {
        binding.blfLoader.isRefreshing = loading
    }

    override fun onRefresh() {
        viewModel.getBridges()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}