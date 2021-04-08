package ru.zdanovich.handhSchoolHomework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentFirstBinding

class FirstFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.fragmentFirstToolbar.apply {
            inflateMenu(R.menu.fragment_first_menu)
            setOnMenuItemClickListener {
                var result = true

                when (it.itemId) {
                    R.id.action_fragment_first_search -> showToast(getString(R.string.search))
                    R.id.action_fragment_first_more_item_one -> showToast(getString(R.string.item_one))
                    R.id.action_fragment_first_more_item_two -> showToast(getString(R.string.item_two))
                    R.id.action_fragment_first_more_item_three -> showToast(getString(R.string.item_three))
                    else -> result = false
                }

                result
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}