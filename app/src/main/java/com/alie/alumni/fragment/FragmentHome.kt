package com.alie.alumni.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alie.alumni.MainActivity
import com.alie.alumni.R
import com.alie.alumni.adapter.MahasiswaAdapter
import com.alie.alumni.database.DatabaseHelper
import com.alie.alumni.model.Mahasiswa

class FragmentHome : Fragment() {


    private lateinit var mRecyclerView : RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var mahasiswaAdapter: MahasiswaAdapter // global variabel
    private var listMahasiswa = ArrayList<Mahasiswa>()
    private lateinit var toolbar : Toolbar
    companion object{
        lateinit var databaseHelper : DatabaseHelper
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        databaseHelper = DatabaseHelper(activity!!,null,null,1)
        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Task"
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?) {
                val intent = Intent(activity,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        })
        mRecyclerView = view.findViewById(R.id.recyclerViewHome)
        viewMahasiswa()

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_back)

    }

    fun  viewMahasiswa(){
        listMahasiswa = databaseHelper.insert(activity!!)
        layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        mahasiswaAdapter = MahasiswaAdapter(activity!!,listMahasiswa)
        mRecyclerView.adapter = mahasiswaAdapter
    }
    override fun onResume() {
        viewMahasiswa()
        super.onResume()
    }
}
