package ru.zdanovich.handhSchoolHomework.presenter.noteEdit.colorDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.zdanovich.handhSchoolHomework.databinding.ColorDialogBinding
import ru.zdanovich.handhSchoolHomework.domain.repositories.ColorRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.ColorRepositoryImpl

class ColorDialogFragment: DialogFragment() {
    private var _binding: ColorDialogBinding? = null
    private val binding get() = _binding!!

    private val repository: ColorRepository = ColorRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ColorDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.colorDialogRv.adapter = ColorAdapter(repository.getColors())
        binding.colorDialogActionCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }

}