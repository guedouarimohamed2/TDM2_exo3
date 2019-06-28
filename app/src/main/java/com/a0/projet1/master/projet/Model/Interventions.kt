package com.a0.projet1.master.projet.Model

import com.a0.projet1.master.projet.R
import java.util.*

object Interventions {
    var tests = ArrayList<Intervention>()
    var ans:MutableList<Intervention> = ArrayList()
    val t = System.currentTimeMillis()
    var nb_intervention:Int?=0
    //Log.i("TAG", "SERIAL: " + Build.SERIAL);
    val KEY_ENABLE_HOME = "position"
    var type_recherche = 1


    fun initial(){

       // this.nb_intervention = 15
    }
    fun add_annonce(Intervention: Intervention){
        ans.add(Intervention)
        this.nb_intervention = this.nb_intervention!! + 1
    }

    fun remove_annonce(Intervention: Intervention){
        ans.remove(Intervention)
        this.nb_intervention = this.nb_intervention!! - 1
    }


    fun findAnnonceByTitre(titre: String?): Intervention? {
        for (a in Interventions.ans)
            if (a.nom.equals(titre))
                return a
        return null
    }

}