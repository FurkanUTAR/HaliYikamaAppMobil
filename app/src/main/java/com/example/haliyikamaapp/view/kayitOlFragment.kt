package com.example.haliyikamaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.haliyikamaapp.databinding.FragmentKayitOlBinding
import com.example.haliyikamaapp.model.kullanici
import com.example.haliyikamaapp.roomdb.haliYikamaDatabase
import com.example.haliyikamaapp.roomdb.kullaniciDAO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class kayitOlFragment : Fragment() {

    private var _binding: FragmentKayitOlBinding? = null
    private val binding get() = _binding!!
    private lateinit var db:haliYikamaDatabase
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
        _binding = FragmentKayitOlBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.girisYapTxtButton.setOnClickListener{
            val action=kayitOlFragmentDirections.actionKayitOlFragmentToGirisYapFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.kayitOlButton.setOnClickListener{ kaydet() }
    }

    fun kaydet(){
        var firmaAdi=binding.firmaAdiText.text.toString()
        var kullaniciAdi=binding.kullaniciAdiText.text.toString()
        var telNo=binding.telNoText.text.toString()
        var ePosta=binding.eMailText.text.toString()
        var parola=binding.parolaText.text.toString()

        if(
            !binding.firmaAdiText.text.isNullOrEmpty() &&
            !binding.kullaniciAdiText.text.isNullOrEmpty() &&
            !binding.telNoText.text.isNullOrEmpty() &&
            !binding.eMailText.text.isNullOrEmpty() &&
            !binding.parolaText.text.isNullOrEmpty()
            ) {
            var Kullanici = kullanici(firmaAdi, kullaniciAdi, telNo, ePosta, parola)

            mDisposable.add(
                kullaniciDao.ekle(Kullanici)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::kaydetYanitiEleAl)
            )
        }else{
            Toast.makeText(requireContext(),"Lütfen boş alan bırakmayınız!!",Toast.LENGTH_LONG).show()
        }
    }

    private fun kaydetYanitiEleAl(){
        val action=kayitOlFragmentDirections.actionKayitOlFragmentToGirisYapFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mDisposable.clear()
    }
}