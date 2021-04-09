package ru.zdanovich.handhSchoolHomework.ui.third

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentImageBinding
import ru.zdanovich.handhSchoolHomework.domain.models.ImageContainer

class ImageFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    private var listenerImageClick: OnImageClicked? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerImageClick = context as? OnImageClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<ImageContainer>(IMAGE_CONTAINER)?.let { imageContainer ->
            binding.fragmentImageImage.setImageResource(imageContainer.image)
            binding.fragmentImageCaption.text =
                view.resources.getString(R.string.fragment_image_caption, imageContainer.id)

            binding.fragmentImageImage.setOnClickListener {
                listenerImageClick?.onImageClick(
                    view.resources.getString(
                        R.string.fragment_image_caption,
                        imageContainer.id
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listenerImageClick = null
    }

    companion object {
        private const val IMAGE_CONTAINER = "img_container"

        fun newInstance(imageContainer: ImageContainer): ImageFragment =
            ImageFragment().apply {
                val args = Bundle()
                args.putParcelable(IMAGE_CONTAINER, imageContainer)
                arguments = args
            }
    }

    interface OnImageClicked {
        fun onImageClick(text: String)
    }

}