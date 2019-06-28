package com.a0.projet1.master.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.a0.projet1.master.projet.database.DataBase


/**
 * A simple [Fragment] subclass.
 *
 */
class AjouterForm : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_ajouter_form, container, false)
        return itemView
    }

}
