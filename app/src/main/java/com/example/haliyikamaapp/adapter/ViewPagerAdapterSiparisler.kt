package com.example.haliyikamaapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.haliyikamaapp.view.alinacaklarFragment
import com.example.haliyikamaapp.view.hepsiFragment
import com.example.haliyikamaapp.view.teslimatFragment
import com.example.haliyikamaapp.view.veresiyeFragment

class ViewPagerAdapterSiparisler(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4 // 4 tane fragment var: Alınacaklar, Teslimat, Veresiye, Hepsi
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> alinacaklarFragment()
            1 -> teslimatFragment()
            2 -> veresiyeFragment()
            3 -> hepsiFragment()
            else -> hepsiFragment()
        }
    }
}