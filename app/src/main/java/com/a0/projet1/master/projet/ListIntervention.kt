package com.a0.projet1.master.projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a0.projet1.master.projet.Decoration.ItemOffsetDecoration
import com.a0.projet1.master.projet.Model.Intervention
import com.a0.projet1.master.projet.Model.Interventions
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_list_annonce.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListIntervention.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListIntervention.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListIntervention : Fragment() {

    internal lateinit var recycler_view: androidx.recyclerview.widget.RecyclerView
    internal  lateinit var adapter:AnnonceListAdapter

    internal  lateinit var search_adapter:AnnonceListAdapter
    internal  var last_suggest:MutableList<String> = ArrayList()

    internal  lateinit var search_bar: MaterialSearchBar



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_list_annonce, container, false)
        recycler_view = itemView.findViewById(R.id.annonce_recyclerview) as androidx.recyclerview.widget.RecyclerView
        recycler_view.setHasFixedSize(true)

        recycler_view.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 1)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)

        //Step Search bar
        search_bar = itemView.findViewById(R.id.search_bar) as MaterialSearchBar
        search_bar.setHint("Enter Date")
        search_bar.setCardViewElevation(10)

        search_bar.addTextChangeListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest = ArrayList<String>()
                for (search in last_suggest)
                    if(search.toLowerCase().contains(search_bar.text.toLowerCase()))
                        suggest.add(search)
                search_bar.lastSuggestions = suggest
            }
        })
        search_bar.setOnSearchActionListener(object :MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {

            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    annonce_recyclerview.adapter=adapter
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })

        adapter = AnnonceListAdapter(activity!!,Interventions.ans)
        recycler_view.adapter = adapter

        last_suggest.clear()
        if(Interventions.type_recherche==1){
            for (a in Interventions.ans)
                last_suggest.add(a.date_depot!!)
        }


        search_bar.visibility = View.VISIBLE
        search_bar.lastSuggestions = last_suggest

        return itemView
    }

    private fun startSearch(text: String) {
        if(Interventions.ans.size > 0){
            val result =ArrayList<Intervention>()
            for (a in Interventions.ans)
            {
                if(Interventions.type_recherche==1){
                    if(a.date_depot!!.toLowerCase().contains(text.toLowerCase()))
                        result.add(a)
                }
            }

            search_adapter = AnnonceListAdapter(activity!!,result)
            annonce_recyclerview.adapter = search_adapter
        }

    }
}
