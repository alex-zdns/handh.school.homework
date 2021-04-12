package ru.zdanovich.handhSchoolHomework.presenter.noteEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentNoteEditBinding

class NoteEditFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentNoteEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.fneToolbar.inflateMenu(R.menu.fragment_notes_list_menu)
        binding.fneToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.fneToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}