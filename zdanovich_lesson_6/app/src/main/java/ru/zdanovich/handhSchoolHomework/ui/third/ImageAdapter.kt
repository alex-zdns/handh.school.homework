package ru.zdanovich.handhSchoolHomework.ui.third

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.zdanovich.handhSchoolHomework.domain.models.ImageContainer

class ImageAdapter(
    fragment: Fragment,
    private val images: List<ImageContainer>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(images[position])
    }
}