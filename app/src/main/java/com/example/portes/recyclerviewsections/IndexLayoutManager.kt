package com.example.portes.recyclerviewsections

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.internal_index_layout.view.*
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView





/**
 * Created by edgar on 5/31/15.
 */
class IndexLayoutManager : FrameLayout {
    companion object {
        const val TAG = "IndexLayoutManager"
    }
    lateinit var mRecyclerView: RecyclerView
    lateinit var mScrollListener: RecyclerView.OnScrollListener

    constructor(context: Context): super(context)
    constructor(context: Context,  attrs: AttributeSet): super(context, attrs)
    constructor(context: Context,  attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)


    override fun onFinishInflate() {
        super.onFinishInflate()
        LayoutInflater.from(context).inflate(R.layout.internal_index_layout, this, true)
        mScrollListener = getScrollListener()
    }

    fun attach(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        recyclerView.addOnScrollListener(mScrollListener)
        update(recyclerView, 0, 0)
    }

    private fun getScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                update(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        }
    }

    private fun update(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (mRecyclerView != null && mRecyclerView.childCount > 2) {
            section_title.visibility = View.VISIBLE

            val mView = recyclerView.getChildAt(0)
            val mActual = recyclerView.getChildPosition(mView)
            (mRecyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(mActual, mView.top + 0)


            val firstVisibleView = mRecyclerView.getChildAt(0)
            val secondVisibleView = mRecyclerView.getChildAt(1)

            val firstRowIndex = firstVisibleView.findViewById<TextView>(R.id.section_title)
            val secondRowIndex = secondVisibleView.findViewById<TextView>(R.id.section_title)


            val visibleRange = mRecyclerView.childCount
            val actual = mRecyclerView.getChildPosition(firstVisibleView)
            val next = actual + 1
            val last = actual + visibleRange
            // RESET STICKY LETTER INDEX
            section_title.text = "${getIndexContext(firstRowIndex).toUpperCase()}"
            section_title.visibility = TextView.VISIBLE
            ViewCompat.setAlpha(firstRowIndex, 1f)

            if (dy > 0) {
                if (next <= last) {
                    if (isHeader(firstRowIndex, secondRowIndex)) {
                        section_title.visibility = TextView.INVISIBLE
                        firstRowIndex.visibility = TextView.VISIBLE
                        ViewCompat.setAlpha(firstRowIndex, (1 - (Math.abs(ViewCompat.getY(firstVisibleView)) / firstRowIndex.getHeight())))
                        secondRowIndex.visibility = TextView.VISIBLE
                    } else {
                        firstRowIndex.visibility = TextView.INVISIBLE
                        section_title.visibility = TextView.VISIBLE
                    }
                }
            } else if (dy < 0) {
                if (next <= last) {
                    firstRowIndex.visibility = TextView.INVISIBLE
                    if ((isHeader(firstRowIndex, secondRowIndex) || (getIndexContext(firstRowIndex) != getIndexContext(secondRowIndex))) && isHeader(firstRowIndex, secondRowIndex)) {
                        section_title.visibility = TextView.INVISIBLE
                        firstRowIndex.visibility = TextView.VISIBLE
                        ViewCompat.setAlpha(firstRowIndex, 1 - (Math.abs(ViewCompat.getY(firstVisibleView) / firstRowIndex.getHeight())))
                        secondRowIndex.visibility = TextView.VISIBLE
                    } else {
                        secondRowIndex.visibility = TextView.INVISIBLE
                    }
                }
            }
            if (section_title.visibility == TextView.VISIBLE) {
                firstRowIndex.visibility = TextView.INVISIBLE
            }

        } else {
            section_title.visibility = View.GONE
        }
    }

    private fun isHeader(prev: TextView, act: TextView): Boolean {
        return if (isSameChar(prev.text[0], act.text[0])) {
            java.lang.Boolean.FALSE
        } else {
            java.lang.Boolean.TRUE
        }
    }

    private fun isSameChar(prev: Char, curr: Char): Boolean {
        return if (Character.toLowerCase(prev) == Character.toLowerCase(curr)) {
            java.lang.Boolean.TRUE
        } else {
            java.lang.Boolean.FALSE
        }
    }

    private fun getIndexContext(index: TextView): Char {
        return index.text[0]
    }
}