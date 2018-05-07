package com.example.portes.recyclerviewsections

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initView()
    }

    private fun initView() {
        rview_contacts.layoutManager = LinearLayoutManager(this)
        rview_contacts.setHasFixedSize(true)
        val mContactAdapter = ContactAdapter(getContacts())
        rview_contacts.adapter = mContactAdapter

        index_layout.attach(rview_contacts)
    }

    private fun getContacts():ArrayList<Contact> {
        val mArrayContact = ArrayList<Contact>()
        mArrayContact.add(Contact("Amadeus","M"))
        mArrayContact.add(Contact("Ana Karen","F"))
        mArrayContact.add(Contact("Martha Lucia","F"))
        mArrayContact.add(Contact("Karla","F"))
        mArrayContact.add(Contact("Luz Mayte","F"))
        mArrayContact.add(Contact("Isabel","F"))
        mArrayContact.add(Contact("Marisol","F"))
        mArrayContact.add(Contact("Yessica","F"))
        mArrayContact.add(Contact("Yaneli","F"))
        mArrayContact.add(Contact("Rosalba","F"))
        mArrayContact.add(Contact("Daniela","F"))
        mArrayContact.add(Contact("Luna","F"))
        mArrayContact.add(Contact("Cintia","F"))
        mArrayContact.add(Contact("Larissa","F"))
        mArrayContact.add(Contact("Limoncito","F"))
        mArrayContact.add(Contact("Maria Fernanda","F"))
        mArrayContact.add(Contact("Madrina","F"))
        mArrayContact.add(Contact("Zoe","F"))

        mArrayContact.sortWith(compareBy { it.name })
        return mArrayContact
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
