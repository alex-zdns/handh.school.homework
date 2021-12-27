package ru.zdanovich.handhSchoolHomework.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentSecondBinding
import ru.zdanovich.handhSchoolHomework.domain.repositories.CommunalServiceRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.CommunalServiceRepositoryMock

class SecondFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val repository: CommunalServiceRepository = CommunalServiceRepositoryMock()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.fragmentSecondRv.adapter =
            CommunalServiceAdapter(repository.getCommunalServiceCards())
    }

    private fun setupToolBar() {
        binding.fragmentSecondToolbar.apply {
            inflateMenu(R.menu.fragment_second_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.action_fragment_second_flashlight) {
                    Toast.makeText(context, getString(R.string.flashlight), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}