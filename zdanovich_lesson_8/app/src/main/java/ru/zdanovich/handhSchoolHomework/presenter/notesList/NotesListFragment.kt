package ru.zdanovich.handhSchoolHomework.presenter.notesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentNotesListBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.presenter.noteEdit.NoteEditFragment

class NotesListFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()

        val currentBackStackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<Note>(NoteEditFragment.NOTE_FOR_SAVE)
            ?.observe(currentBackStackEntry, Observer { result ->
                Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show()
            })

        binding.fnlAddNotesButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_notesListFragment_to_noteEditFragment)
        )
    }

    private fun setupToolBar() {
        binding.fnlToolbar.apply {
            inflateMenu(R.menu.fragment_notes_list_menu)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.action_fnl_search) {
                    Toast.makeText(context, getString(R.string.search), Toast.LENGTH_LONG).show()
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