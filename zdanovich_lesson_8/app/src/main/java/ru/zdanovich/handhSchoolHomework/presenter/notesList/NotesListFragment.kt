package ru.zdanovich.handhSchoolHomework.presenter.notesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentNotesListBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.presenter.noteEdit.NoteEditFragment


class NotesListFragment : androidx.fragment.app.Fragment() {
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesListViewModel by viewModels { NotesListViewModelFactory() }
    private val adapter = NoteAdapter(object : NoteAdapter.OnRecyclerNoteItemClicked {
        override fun onNoteClick(note: Note) {
            val action = NotesListFragmentDirections.actionNotesListFragmentToNoteEditFragment(note)
            findNavController().navigate(action)
        }

        override fun onNoteLongClick(note: Note) {
            showDeleteOrArchiveDialog(note)
        }

    })

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
        setupRecycleView()

        viewModel.notesList.observe(this.viewLifecycleOwner, this::updateNotesList)

        setFragmentResultListener(NoteEditFragment.NOTE_FOR_SAVE) { key, bundle ->
            val note = bundle.getParcelable<Note>(NoteEditFragment.NOTE)
            note?.let {
                viewModel.saveNote(it)
            }
        }

        binding.fnlAddNotesButton.setOnClickListener {
            val action = NotesListFragmentDirections.actionNotesListFragmentToNoteEditFragment()
            findNavController().navigate(action)
        }
    }

    private fun updateNotesList(notes: List<Note>) {
        adapter.submitList(notes)
    }

    private fun setupRecycleView() {
        val spanCount =
            view?.resources?.getInteger(R.integer.notes_list_span_count) ?: DEFAULT_SPAN_COUNT
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL)
        binding.fnlRv.adapter = adapter
        binding.fnlRv.layoutManager = staggeredGridLayoutManager
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

    private fun showDeleteOrArchiveDialog(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.nlf_dialog_title))
            .setMessage(note.title)
            .setIcon(R.drawable.ic_info)
            .setPositiveButton(getString(R.string.nlf_dialog_to_delete)) { dialog, _ ->
                viewModel.deleteNote(note.id)
                dialog.cancel()
            }
            .setNegativeButton(getString(R.string.nlf_dialog_to_archive)) { dialog, _ ->
                dialog.cancel()
            }
            .setNeutralButton(getString(R.string.nlf_dialog_to_cancel)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DEFAULT_SPAN_COUNT = 2
    }
}