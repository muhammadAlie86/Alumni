package com.alie.alumni.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alie.alumni.MainActivity
import com.alie.alumni.R
import com.alie.alumni.adapter.ListKelasAdapter
import com.alie.alumni.model.Kelas
import com.alie.alumni.model.KelasData


class FragmentBook : Fragment()
{
    private val TAG = "Fragment book"
    private var listKelas : ArrayList<Kelas> = arrayListOf()
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var listAdapter : ListKelasAdapter // global variabel
    private lateinit var toolbar : Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        Log.d(TAG,"onCreateView")
        val v : View =inflater.inflate(R.layout.fragment_book, container, false)
        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //show recycler view
        mRecyclerView = v.findViewById(R.id.recyclerView)
        toolbar = v.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Book"
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?) {
                val intent = Intent(activity,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })
        showRecyclerViewList()
        return v
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        Log.d(TAG,"onCreate")
        super.onCreate(savedInstanceState)
        listKelas.addAll(KelasData.listData)//get data

    }
    fun showRecyclerViewList() {
        layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        listAdapter = ListKelasAdapter(activity!!,listKelas)//get dcontext dan list
        mRecyclerView.adapter = listAdapter //adapter

    }
}


