package com.example.azura_tic.Catatan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.azura_tic.Data.Api.RetrofitClient
import com.example.azura_tic.R
import com.example.azura_tic.databinding.ActivityCatatanFormBinding
import com.example.azura_tic.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class CatatanFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatatanFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCatatanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val isEdit = intent.hasExtra("id")
        val noteId = intent.getLongExtra("id", 0)

        binding.btnSaveNote.text = if (isEdit) "Update" else "Simpan"
        if (isEdit) {
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etContent.setText(intent.getStringExtra("content"))
        }
        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()
            if (title.isBlank() || content.isBlank()) {

                Toast.makeText(this, "Isi judul dan catatan", Toast.LENGTH_SHORT).show()

                return@setOnClickListener

            }

            val data = mapOf("title" to title, "content" to content)

            binding.btnSaveNote.isEnabled = false
            lifecycleScope.launch {
                try {
                val api = RetrofitClient.service
                val response = if (isEdit) {

                    api.editNote(RetrofitClient.API_KEY, "eq.$noteId", data)
                } else {
                    api.addNote(RetrofitClient.API_KEY, data)
                }

                if (response.isSuccessful) {

                    finish()
                } else {
                    Toast.makeText(this@CatatanFormActivity,

                        "Gagal: ${response.code()}", Toast.LENGTH_SHORT).show()

                }
            } catch (e: Exception) {
                Toast.makeText(this@CatatanFormActivity,
                    "Koneksi gagal", Toast.LENGTH_SHORT).show()

            }
                binding.btnSaveNote.isEnabled = true
            }
        }
    }
}