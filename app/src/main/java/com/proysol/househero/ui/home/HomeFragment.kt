package com.proysol.househero.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.proysol.househero.databinding.FragmentHomeBinding
import com.proysol.househero.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.launch
import com.proysol.househero.R


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeMenuAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initMenuOptions()
        initObservers()
        initViews()
    }

    private fun initViews() {
        val options = RequestOptions().centerCrop().placeholder(R.drawable.margaret)

        binding?.ivProfilePic?.let {
            Glide.with(this)
                .load(R.drawable.margaret)
                .apply(options)
                .into(it)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.menuOptions.collect {
                    homeMenuAdapter.update(it)
                }
            }
        }
    }

    private fun initMenuOptions() {
        homeMenuAdapter = HomeAdapter()
        binding?.rvHomeMenu?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeMenuAdapter
        }
    }

}