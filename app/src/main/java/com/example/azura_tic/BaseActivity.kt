package com.example.azura_tic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.azura_tic.Home.HomeFragment
import com.example.azura_tic.Message.MessageFragment
import com.example.azura_tic.More.MoreFragment
import com.example.azura_tic.R.id
import com.example.azura_tic.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0) // bottom = 0
            insets
        }

        replaceFragment(HomeFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }
                id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false // false jika item tidak dikenal
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            // .addToBackStack(null) sengaja dinonaktifkan: saat back langsung keluar aplikasi
            .commit()
    }
}