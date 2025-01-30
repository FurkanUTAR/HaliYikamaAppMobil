package com.example.haliyikamaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haliyikamaapp.R
import com.example.haliyikamaapp.adapter.ViewPagerAdapter
import com.example.haliyikamaapp.adapter.ViewPagerAdapterSiparisler
import com.example.haliyikamaapp.databinding.FragmentAnaMenuViewPagerBinding
import com.example.haliyikamaapp.databinding.FragmentGirisYapBinding
import com.example.haliyikamaapp.databinding.FragmentSiparislerBinding
import com.google.android.material.tabs.TabLayoutMediator

class siparislerFragment : Fragment() {

    private var _binding: FragmentSiparislerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSiparislerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapterSiparisler(requireActivity())
        binding.viewPager.adapter = adapter

        // TabLayout ile ViewPager'ı Bağlama
        TabLayoutMediator(binding.tabItem, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Alınacaklar"
                1 -> tab.text = "Teslimat"
                2 -> tab.text = "Veresiye"
                3 -> tab.text = "Hepsi"
            }
        }.attach()
    }
}