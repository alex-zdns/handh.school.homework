package ru.zdanovich.handhSchoolHomework.presenter.noteEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentNoteEditBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Note

class NoteEditFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentNoteEditBinding? = null
    private val binding get() = _binding!!

    private var noteId = 0

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

        arguments?.let {bundle ->
            val note: Note? = NoteEditFragmentArgs.fromBundle(bundle).selectedNote
            note?.let {
                noteId = it.id
                binding.fneTitleEdit.setText(it.title)
                binding.fneBodyEdit.setText(it.body)
            }

        }
    }

    private fun setupToolBar() {
        binding.fneToolbar.apply {
            inflateMenu(R.menu.fragment_note_edit_menu)
            setNavigationOnClickListener {
                navigateBackToFragmentNotesList()
            }
            setOnMenuItemClickListener {
                if (it.itemId == R.id.action_fne_set_background_color) {
                    val action = NoteEditFragmentDirections.actionToColorDialog()
                    findNavController().navigate(action)
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

    companion object {
        const val NOTE_FOR_SAVE = "note_for_save"
        const val NOTE = "note"
    }

    private fun navigateBackToFragmentNotesList() {
        val title = binding.fneTitleEdit.text.toString()
        val body = binding.fneBodyEdit.text.toString()

        val note = Note(id = noteId, title = title, body = body)

        setFragmentResult(NOTE_FOR_SAVE, bundleOf(NOTE to note))
        findNavController().navigateUp()
    }
}