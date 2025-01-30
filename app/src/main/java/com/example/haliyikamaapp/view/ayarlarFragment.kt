package com.example.haliyikamaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.haliyikamaapp.R
import com.example.haliyikamaapp.databinding.FragmentAyarlarBinding
import com.example.haliyikamaapp.roomdb.haliYikamaDatabase
import com.example.haliyikamaapp.roomdb.kullaniciDAO
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ayarlarFragment : Fragment() {

    private var _binding: FragmentAyarlarBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: haliYikamaDatabase
    private lateinit var kullaniciDao: kullaniciDAO
    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(requireContext(),haliYikamaDatabase::class.java,"HalıYıkama").build()
        kullaniciDao = db.kullaniciDAO()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAyarlarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}