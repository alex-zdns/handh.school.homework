package ru.zdanovich.handhSchoolHomework.ui.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentThirdBinding

class ThirdFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerFragment: ViewPagerFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentThirdButtonShowBanner.setOnClickListener {
            if (binding.fragmentThirdButtonShowBanner.isSelected) {
                showBanner(it)
            } else {
                hideBanner(it)
            }
        }
    }

    private fun showBanner(view: View) {
        childFragmentManager.beginTransaction()
            .remove(viewPagerFragment)
            .commit()

        binding.fragmentThirdButtonShowBanner.text =
            view.resources.getString(R.string.fragment_third_button_show_banner_label)
        binding.fragmentThirdButtonShowBanner.isSelected = false
    }

    private fun hideBanner(view: View) {
        viewPagerFragment = ViewPagerFragment()

        childFragmentManager.beginTransaction()
            .add(R.id.fragment_third_view_pager_container, viewPagerFragment)
            .commit()

        binding.fragmentThirdButtonShowBanner.text =
            view.resources.getString(R.string.fragment_third_button_hide_banner_label)
        binding.fragmentThirdButtonShowBanner.isSelected = true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}