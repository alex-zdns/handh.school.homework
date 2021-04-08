package ru.zdanovich.handhSchoolHomework.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.fragmentSecondRv.adapter =
            CommunalServiceAdapter(repository.getCommunalServiceCards())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}