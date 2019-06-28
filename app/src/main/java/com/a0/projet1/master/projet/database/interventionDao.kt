package com.a0.projet1.master.projet.database

import androidx.room.*
import com.a0.projet1.master.projet.Model.Intervention

@Dao
interface InterventionDao
{


    @Query("SELECT * FROM intervention")
    fun getAll(): List<Intervention>



    @Insert
    fun insertAll(vararg intervention: Intervention)

    @Delete
    fun delete(intervention: Intervention)

    @Update
    fun update(vararg intervention: Intervention)


}