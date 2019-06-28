package com.a0.projet1.master.projet


import android.annotation.SuppressLint
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.a0.projet1.master.projet.Model.Intervention
import com.a0.projet1.master.projet.Model.Interventions


class InterventionDetaille : Fragment() {

    val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

    internal  lateinit var annonce_date: TextView
    internal  lateinit var annonce_type: TextView
    internal  lateinit var annonce_nom: TextView

    companion object {
        internal var instance:InterventionDetaille?=null
        fun getInstance():InterventionDetaille{
            if (instance == null)
                instance = InterventionDetaille()

            return instance!!
        }
    }



    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var itemView = inflater.inflate(R.layout.fragment_annonce_detaille, container, false)
        val annonce = Interventions.findAnnonceByTitre(arguments!!.getString("nom"))

        annonce_date = itemView.findViewById(R.id.date) as TextView
        annonce_type = itemView.findViewById(R.id.type) as TextView
        annonce_nom = itemView.findViewById(R.id.nom) as TextView



        setDetailAnnonce(annonce)

        return itemView
    }



    private fun setDetailAnnonce(annonce: Intervention?) {
        annonce_nom.text = "Nom : " + annonce!!.nom
        annonce_type.text = "Type : " + annonce.type
        annonce_date.text = "Date : " + annonce.date_depot

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }



    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }


}
