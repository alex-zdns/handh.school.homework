package ru.zdanovich.handhSchoolHomework.presenter.notesList

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.flow.*
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentNotesListBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Note
import ru.zdanovich.handhSchoolHomework.domain.models.NoteColor
import ru.zdanovich.handhSchoolHomework.presenter.noteEdit.NoteEditFragment
import ru.zdanovich.handhSchoolHomework.presenter.notesList.NotesListViewModel.State


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

        viewModel.notesList.observe(this.viewLifecycleOwner, this::setState)

        setFragmentResultListener(NoteEditFragment.NOTE_FOR_SAVE) { _, bundle ->
            val note = bundle.getParcelable<Note>(NoteEditFragment.NOTE)
            note?.let {
                viewModel.saveNote(it)
            }
        }

        binding.fnlAddNotesButton.setOnClickListener {
            val action = NotesListFragmentDirections.actionNotesListFragmentToNoteEditFragment(
                Note(
                    title = EMPTY,
                    body = EMPTY,
                    noteColor = NoteColor()
                )
            )
            findNavController().navigate(action)
        }
    }

    private fun setState(state: State) =
        when (state) {
            State.Init -> {
            }
            State.EmptyList -> {
                binding.fnlNoContent.text = getText(R.string.fnl_no_content)
                binding.fnlNoContent.isVisible = true
                updateNotesList(emptyList())
            }
            is State.Success -> {
                binding.fnlNoContent.isVisible = false
                updateNotesList(state.notes)
            }
            State.EmptySearchResult -> {
                binding.fnlNoContent.text = getString(R.string.fnl_no_result)
                binding.fnlNoContent.isVisible = true
                updateNotesList(emptyList())
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

            val menuItem = menu.findItem(R.id.action_fnl_search)
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        Log.d(this@NotesListFragment::class.simpleName, "new text = $it")
                        viewModel.searchNotes(it)
                    }

                    return true
                }
            })
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
                viewModel.archieNote(note.id)
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
        const val EMPTY = ""
    }
}