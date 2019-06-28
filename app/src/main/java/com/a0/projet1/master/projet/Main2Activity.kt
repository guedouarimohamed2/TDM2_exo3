package com.a0.projet1.master.projet

import android.app.Activity
import android.app.DatePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.google.android.material.navigation.NavigationView
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
//import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


import com.a0.projet1.master.projet.Model.Intervention
import com.a0.projet1.master.projet.Model.Interventions
import com.a0.projet1.master.projet.R.id.date
import com.a0.projet1.master.projet.adapter.AnnonceListAdapter
import com.a0.projet1.master.projet.database.DataBase
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import kotlinx.android.synthetic.main.fragment_ajouter_form.*
import kotlinx.coroutines.GlobalScope
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
////////////////////////////////////////////////////////////////////////////////////////////////////////////
private val showDetail = object : BroadcastReceiver(){
    override fun onReceive(p0: Context?, intent: Intent?) {
        if (intent!!.action!!.toString() == Interventions.KEY_ENABLE_HOME)
        {
            val detailFragment = InterventionDetaille.getInstance()
            val num = intent.getStringExtra("nom")
            val bundle = Bundle()
            bundle.putString("nom",num)
            detailFragment.arguments = bundle

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment,detailFragment)
            fragmentTransaction.addToBackStack("detail")
            fragmentTransaction.commit()
        }
    }
}

var arr = arrayListOf<String>()
    val manager = supportFragmentManager
    var images: MutableList<Uri>? = ArrayList()
    var interventions: List<Intervention> = ArrayList<Intervention>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            loadFrag2(AjouterForm())
            images!!.clear()
        }

        toolbar.setTitle("Interventions plombiers")
        setSupportActionBar(toolbar)
        loadFrag1(ll!!)
        intial_DB()

if(btn_date !=null) {
    btn_date.setOnClickListener {

    }
}

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, IntentFilter(Interventions.KEY_ENABLE_HOME))

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

lateinit var db:RoomDatabase
    fun intial_DB(){
         db = Room.databaseBuilder(
                this.applicationContext,
                DataBase::class.java, "intervention.db"
        ).allowMainThreadQueries().build()
            interventions = (db as DataBase).interventionDao().getAll()

        if(interventions.isEmpty()) {
            var a1:Intervention=Intervention(0,"mohamed", "type1","2019-07-26")
            var a2:Intervention=Intervention(0,"nazim", "type1","2019-08-26")
            var a3:Intervention=Intervention(0,"mahmoud", "type2","2019-09-23")
            var a4:Intervention=Intervention(0,"yacine", "type2","2019-07-16")
            var a5:Intervention=Intervention(0,"makhlouf", "type3","2019-07-23")
            var a6:Intervention=Intervention(0,"salim", "type3","2019-08-06")
         /*   Interventions.add_annonce(a1)
            Interventions.add_annonce(a2)
            Interventions.add_annonce(a3)
            Interventions.add_annonce(a4)
            Interventions.add_annonce(a5)
            Interventions.add_annonce(a6)*/
            (db as DataBase).interventionDao().insertAll(a1,a2,a3,a4,a5,a6)
            interventions = (db as DataBase).interventionDao().getAll()
            for(i in interventions){
                Interventions.add_annonce(i)
            }
        }else{
            for(i in interventions){
                Interventions.add_annonce(i)
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
    var ll:ListIntervention? = ListIntervention()
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                loadFrag1(ll!!)
            }
            R.id.nav_gallery -> {
                loadFrag2(AjouterForm())
                images!!.clear()
            }
            R.id.tri_date_depot -> {
                var sortedList = Interventions.ans.sortedByDescending {it.date_depot}
                Interventions.ans.clear()
                for (obj in sortedList) {
                    Interventions.ans.add(obj)
                }
                ll!!.adapter = AnnonceListAdapter(ll!!.activity!!,Interventions.ans)
                ll!!.recycler_view.adapter = ll!!.adapter
            }
            R.id.rech_date ->{

                ll!!.last_suggest.clear()
                for (a in Interventions.ans)
                    ll!!.last_suggest.add(a.date_depot!!)

                ll!!.search_bar.visibility = View.VISIBLE
                ll!!.search_bar.lastSuggestions = ll!!.last_suggest
                ll!!.search_bar.setHint("Entrer Nom")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    internal lateinit var btn: Button

    fun test(){
        ll!!.recycler_view.adapter = ll!!.adapter
    }
    fun show_list_annonce(view : View){
        loadFrag1(ListIntervention())
    }
    fun show_ajouter_form(view : View){
        loadFrag2(AjouterForm())
        images!!.clear()
    }
    private fun loadFrag1(f1:ListIntervention){
        val ft = manager.beginTransaction()
        ft.replace(R.id.fragment,f1)
        ft.addToBackStack(null)
        ft.commit()
    }
    private fun loadFrag2(f2:AjouterForm){
        val ft = manager.beginTransaction()
        ft.replace(R.id.fragment,f2)
        ft.addToBackStack(null)
        ft.commit()
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val REQUEST_PICK_PHOTO = 1
    }

    val c=Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONDAY)
    val day = c.get(Calendar.DAY_OF_MONTH)
    fun pick_date(view: View){
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val d = mDay.toString()+"-"+mMonth.toString()+"-"+mYear.toString()
            var date = LocalDate.of(mYear,mMonth, mDay)
            datee.text =date.toString()
        }, year, month, day)
        dpd.show()
        datee.text =date.toString()
    }
    fun ajouter(view: View){
        val nom: EditText =findViewById(R.id.input_nom)
        val type: EditText =findViewById(R.id.input_type)
        val date: TextView =findViewById(R.id.datee)
        val t =  System.currentTimeMillis();
        Log.i("TAG", "SERIAL: " + t);
        val a1 = Intervention(0,nom.text.toString(),type.text.toString(),datee.text.toString())
        Interventions.ans.clear()
        (db as DataBase).interventionDao().insertAll(a1)
        interventions = (db as DataBase).interventionDao().getAll()
        for(i in interventions){
            Interventions.add_annonce(i)
        }
        loadFrag1(ll!!)
        nom.setText("")
        type.setText("")
    }
}