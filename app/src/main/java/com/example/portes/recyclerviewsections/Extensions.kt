package com.example.portes.recyclerviewsections

import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(mResource: Int) = LayoutInflater.from(context).inflate(mResource, this, false)
