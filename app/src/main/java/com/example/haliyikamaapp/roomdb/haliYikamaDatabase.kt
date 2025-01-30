package com.example.haliyikamaapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.haliyikamaapp.model.kullanici

@Database(entities = [kullanici::class], version = 1)
abstract class haliYikamaDatabase : RoomDatabase() {
    abstract fun kullaniciDAO(): kullaniciDAO
}
