package ru.zdanovich.handhSchoolHomework.ui.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.zdanovich.handhSchoolHomework.databinding.FragmentViewPagerBinding
import ru.zdanovich.handhSchoolHomework.domain.repositories.ImageRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.ImageRepositoryImpl


class ViewPagerFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private val repository: ImageRepository = ImageRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentViewPager.adapter = ImageAdapter(this, repository.getImages())
        binding.fragmentViewPagerIndicator.attachToPager(binding.fragmentViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.fragmentViewPagerIndicator.detachFromPager()
        _binding = null
    }

}