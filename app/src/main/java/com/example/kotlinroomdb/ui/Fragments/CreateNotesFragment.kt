package com.example.kotlinroomdb.ui.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinroomdb.Model.Notes
import com.example.kotlinroomdb.R
import com.example.kotlinroomdb.ViewModel.NotesViewModel
import com.example.kotlinroomdb.databinding.FragmentCreateNotesBinding
import java.util.Date

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding

    var priority: String = "1"

    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.ic_round_done)
        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_round_done)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_round_done)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_round_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        if (TextUtils.isEmpty(binding.editTitle.text.toString())) {
            binding.editTitle.error = "the title is required"
        } else if (TextUtils.isEmpty(binding.editSubtitle.text.toString())) {
            binding.editSubtitle.error = "the subtitle is required"
        } else if (TextUtils.isEmpty(binding.editNotes.text.toString())) {
            binding.editNotes.error = "the notes is required"
        } else {

            var title = binding.editTitle.text.toString()
            var subtitle = binding.editSubtitle.text.toString()
            var notes = binding.editNotes.text.toString()

            val d = Date()
            val notesDate: CharSequence = DateFormat.format("yyyy-MM-dd hh:mm", d.time)

            val data = Notes(
                null,
                title = title,
                subtitle = subtitle,
                notes = notes,
                date = notesDate.toString(),
                priority = priority
            )

            viewModel.addNotes(data)
            Navigation.findNavController(it!!)
                .navigate(R.id.action_createNotesFragment_to_homeFragment)
        }
    }
}