package com.example.haliyikamaapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RewriteQueriesToDropUnusedColumns

@Entity
data class kullanici(
    @ColumnInfo("firmaAdi")
    var firmaAdi:String,

    @ColumnInfo("kullaniciAdi")
    var kullaniciAdi:String,

    @ColumnInfo("telNo")
    var telNo:String,

    @ColumnInfo("ePosta")
    var ePosta:String,

    @ColumnInfo("parola")
    var parola:String
){
    @PrimaryKey(autoGenerate = true)
    var id=0
}