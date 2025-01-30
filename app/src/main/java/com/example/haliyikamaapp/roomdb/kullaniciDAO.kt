package com.example.haliyikamaapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.haliyikamaapp.model.kullanici
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface kullaniciDAO {

    @Query("SELECT * FROM kullanici")
    fun kullanicilar(): Flowable<List<kullanici>>

    @Query("SELECT * FROM kullanici WHERE firmaAdi= :firmaAdi AND kullaniciAdi = :kullaniciAdi AND parola= :parola")
    fun kullaniciBul(firmaAdi: String, kullaniciAdi: String, parola: String): Single<kullanici>

    @Insert
    fun ekle(kullanici: kullanici): Completable

    @Delete
    fun sil(kullanici: kullanici): Completable
}
