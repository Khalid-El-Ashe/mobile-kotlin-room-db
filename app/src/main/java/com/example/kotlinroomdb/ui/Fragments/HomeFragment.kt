package com.example.kotlinroomdb.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlinroomdb.Model.Notes
import com.example.kotlinroomdb.R
import com.example.kotlinroomdb.ViewModel.NotesViewModel
import com.example.kotlinroomdb.databinding.FragmentHomeBinding
import com.example.kotlinroomdb.ui.Adapter.NotesAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    // i need to inflate items for this frag with binding
    lateinit var binding: FragmentHomeBinding

    // i need to make instance of my view model class
    val viewModel: NotesViewModel by viewModels()

    var oldMyNotes = arrayListOf<Notes>()

    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // to show my delete option menu
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager

        // in here i need to get all database and add this in recycler
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        })
        binding.filterHigh.setOnClickListener {
            // in here i need to get all database and add this in recycler
            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.filterMedium.setOnClickListener {
            // in here i need to get all database and add this in recycler
            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.filterLaw.setOnClickListener {
            // in here i need to get all database and add this in recycler
            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.allNotes.setOnClickListener {
            // in here i need to get all database and add this in recycler
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener {
            // i need make intent with navigation
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    // in here i need to make search
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)

        // i need to make search with kotlin
        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchFiltering(p0: String?) {
        val newFilterList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.toString().contains(p0!!) || i.subtitle.toString().contains(p0!!)) {
                newFilterList.add(i)
            }
        }
        adapter.filtering(newFilterList)
    }
}