package com.example.haliyikamaapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.haliyikamaapp.databinding.FragmentGirisYapBinding
import com.example.haliyikamaapp.model.kullanici
import com.example.haliyikamaapp.roomdb.haliYikamaDatabase
import com.example.haliyikamaapp.roomdb.kullaniciDAO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class girisYapFragment : Fragment() {

    private var _binding: FragmentGirisYapBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: haliYikamaDatabase
    private lateinit var kullaniciDao: kullaniciDAO
    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Veritabanı bağlantısını kur
        db = Room.databaseBuilder(requireContext(), haliYikamaDatabase::class.java, "HalıYıkama").build()
        kullaniciDao = db.kullaniciDAO()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGirisYapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kayıt ol butonuna tıklama işlemi
        binding.kayitOlTxtButton.setOnClickListener {
            val action = girisYapFragmentDirections.actionGirisYapFragmentToKayitOlFragment()
            Navigation.findNavController(view).navigate(action)
        }

        // Giriş yap butonuna tıklama işlemi
        binding.girisYapButton.setOnClickListener {
            val firmaAdi = binding.firmaAdiText.text.toString()
            val kullaniciAdi = binding.kullaniciAdiText.text.toString()
            val parola = binding.parolaText.text.toString()

            // Giriş kontrolü
            if (firmaAdi.isNotEmpty() && kullaniciAdi.isNotEmpty() && parola.isNotEmpty()) {
                girisKontrol(firmaAdi, kullaniciAdi, parola)
            } else {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun.", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Giriş kontrolü için kullanılan metod
    fun girisKontrol(firmaAdi: String, kullaniciAdi: String, parola: String) {
        mDisposable.add(
            kullaniciDao.kullaniciBul(firmaAdi, kullaniciAdi, parola)
                .subscribeOn(Schedulers.io()) // Arka planda çalıştırma
                .observeOn(AndroidSchedulers.mainThread()) // Ana thread'e dön
                .subscribe(this::sonucuEleAl, this::hataEleAl)
        )
    }

    // Giriş başarılıysa yönlendirme
    private fun sonucuEleAl(kullanici: kullanici) {
        val action = girisYapFragmentDirections.actionGirisYapFragmentToAnaMenuViewPager()
        Navigation.findNavController(requireView()).navigate(action)
    }

    // Giriş başarısızsa hata mesajı göster
    private fun hataEleAl(throwable: Throwable) {
        Toast.makeText(requireContext(), "Giriş başarısız! Bilgilerinizi kontrol edin.", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mDisposable.clear() // Rx işlemlerini temizle
    }
}
