package com.example.azura_tic.pertemuan_6

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.azura_tic.R
import com.example.azura_tic.databinding.ActivityEnamBinding
import com.example.azura_tic.databinding.ActivityLoginBinding

class EnamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEnamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Sixth"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.btnfrag1.setOnClickListener {
            replaceFragment(SatuFragment())
        }
        binding.btnfrag2.setOnClickListener {
            replaceFragment(DuaFragment())
        }
        binding.btnfrag3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerFragment.id, fragment)
            .commit()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}