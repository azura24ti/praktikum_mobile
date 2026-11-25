package com.example.azura_tic.Catatan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azura_tic.Data.Api.RetrofitClient
import com.example.azura_tic.R
import com.example.azura_tic.databinding.FragmentCatatanBinding
import com.example.azura_tic.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class CatatanFragment : Fragment() {

    private var _binding: FragmentCatatanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CatatanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatatanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Catatan"
        adapter = CatatanAdapter(
            onEdit = { note -> openForm(note) },
            onDelete = { note -> confirmDelete(note) }
        )
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter
        binding.fabAddNote.setOnClickListener { openForm() }
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) loadNotes()
    }

    private fun openForm(note: CatatanModel? = null) {
        val intent = Intent(requireContext(), CatatanFormActivity::class.java)
        if (note != null) {
            intent.putExtra("id", note.id)
            intent.putExtra("title", note.title)
            intent.putExtra("content", note.content)
        }
        startActivity(intent)
    }

    private fun loadNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val notes = RetrofitClient.service.getNotes(RetrofitClient.API_KEY)
                if (_binding == null) return@launch
                adapter.updateNotes(notes)
            } catch (e: Exception) {
                if (_binding != null) {

                    Toast.makeText(requireContext(), "Gagal memuat catatan", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun confirmDelete(note: CatatanModel) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus catatan")
            .setMessage("Hapus catatan '${note.title}'?")
            .setNegativeButton("Batal", null)
            .setPositiveButton("Hapus") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        val response = RetrofitClient.service.deleteNote(
                            RetrofitClient.API_KEY, "eq.${note.id}"
                        )

                        if (response.isSuccessful) loadNotes()
                        else Toast.makeText(
                            requireContext(),
                            "Gagal: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()

                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Koneksi gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }.show()
    }

    override fun onDestroyView() {
        binding.rvNotes.adapter = null
        _binding = null
        super.onDestroyView()
    }
}