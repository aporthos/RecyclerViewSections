package com.example.portes.recyclerviewsections

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_contact.view.*
import kotlin.math.log


class ContactAdapter(arrayContact: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    companion object {
        const val TAG = "ContactAdapter"
    }
    private var  mMapIndex =  LinkedHashMap<String, Int>()
    private val mArrayContact = arrayContact
    init {
        getSection()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = parent.inflate(R.layout.item_contact)
        return ViewHolder(mView)
    }

    override fun getItemCount() = mArrayContact.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mContact = mArrayContact[position]
        val mSection = mArrayContact[position].name.substring(0,1).toUpperCase()
        holder.bind(mContact, mSection , mMapIndex[mSection] == position)
    }

    private fun getSection() {
        for (index in mArrayContact.indices) {
            val mName = mArrayContact[index].name.substring(0,1).toUpperCase()
            if (!mMapIndex.containsKey(mName)) {
                mMapIndex[mName] = index
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact, mSection: String, isShow: Boolean) = with(itemView) {
            Log.i(TAG, "bind: $mSection $isShow")
            lbl_name.text = contact.name
            lbl_gender.text = contact.gender
            section_title.text = mSection
            section_title.visibility = if (isShow) View.VISIBLE else View.GONE
        }
    }
}