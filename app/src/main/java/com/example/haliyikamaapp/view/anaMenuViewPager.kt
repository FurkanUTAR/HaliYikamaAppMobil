package com.example.haliyikamaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haliyikamaapp.adapter.ViewPagerAdapter
import com.example.haliyikamaapp.databinding.FragmentAnaMenuViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class anaMenuViewPager : Fragment() {

    private var _binding: FragmentAnaMenuViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnaMenuViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        // TabLayout ile ViewPager'ı Bağlama
        TabLayoutMediator(binding.tabItem, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Ana Menü"
                1 -> tab.text = "Ayarlar"
            }
        }.attach()


    }
}