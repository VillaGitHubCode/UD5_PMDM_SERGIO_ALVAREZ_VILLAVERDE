package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.FirebaseApp
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.databinding.ActivityInitialBinding
import kotlinx.coroutines.launch

class InitialActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInitialBinding
    private val binding: ActivityInitialBinding get()= _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}