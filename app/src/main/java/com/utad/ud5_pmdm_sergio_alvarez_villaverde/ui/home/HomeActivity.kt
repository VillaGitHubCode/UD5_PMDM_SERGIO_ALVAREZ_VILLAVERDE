package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network.DealApi
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.databinding.ActivityHomeBinding
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.adapter.DealAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityHomeBinding
    private val binding: ActivityHomeBinding get() = _binding

    private val adapter = DealAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDealsHome.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDealsHome.adapter = adapter

        observeViewModelStates()

        viewModel.getDealsList("1")
    }

    private fun observeViewModelStates() {
        lifecycleScope.launch {
            viewModel.homeState.collect{ state ->

                if (state.isLoading){
                    binding.pbHome.visibility = View.VISIBLE
                }else{
                    binding.pbHome.visibility = View.GONE
                }

                if(state.dealList!=null){
                    if(adapter!=null){
                        adapter.submitList(state.dealList)
                    }
                }

                if(state.error!=null){
                    Toast.makeText(this@HomeActivity, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}