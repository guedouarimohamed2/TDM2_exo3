package com.a0.projet1.master.projet.Model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "intervention")
data class Intervention(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
      //  @ColumnInfo(name = "num") var num: Int,
        @ColumnInfo(name = "nom") var nom : String,
        @ColumnInfo(name = "type") var type : String,
        @ColumnInfo(name = "date") var date_depot : String
        )