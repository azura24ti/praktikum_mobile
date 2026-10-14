package com.example.azura_tic.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.azura_tic.R
import com.example.azura_tic.databinding.FragmentHomeBinding
import com.example.azura_tic.databinding.FragmentMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Bunga", "Halo! Apa kabar?", "https://i.pravatar.cc/150?img=47"),
        MessageModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?img=12"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?img=44"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?img=13"),
        MessageModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?img=49"),
        MessageModel("Fajar", "Lagi ngapain?", "https://i.pravatar.cc/150?img=14"),
        MessageModel("Gita", "Boleh minta tolong?", "https://i.pravatar.cc/150?img=45"),
        MessageModel("Hana", "Lihat email ya", "https://i.pravatar.cc/150?img=48"),
        MessageModel("Irfan", "Oke noted", "https://i.pravatar.cc/150?img=15"),
        MessageModel("Joko", "Sampai jumpa besok", "https://i.pravatar.cc/150?img=16")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Message"

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }
}