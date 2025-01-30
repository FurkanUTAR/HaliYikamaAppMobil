package com.example.haliyikamaapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.haliyikamaapp.view.anaMenuFragment
import com.example.haliyikamaapp.view.ayarlarFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // 2 tane fragment var: Ana Menü ve Ayarlar
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> anaMenuFragment()
            1 -> ayarlarFragment()
            else -> anaMenuFragment()
        }
    }
}