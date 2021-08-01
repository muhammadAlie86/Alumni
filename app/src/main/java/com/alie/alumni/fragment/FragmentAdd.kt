package com.alie.alumni.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alie.alumni.MainActivity
import com.alie.alumni.R
import com.alie.alumni.database.DatabaseHelper
import com.alie.alumni.model.Mahasiswa
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.layout_center_add.*
import java.text.SimpleDateFormat

import java.util.*


class FragmentAdd : Fragment() {

    private lateinit var toolbar : Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =inflater.inflate(R.layout.fragment_add, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Add Task"
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?) {
                val intent = Intent(activity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        val edt_tgl = view.findViewById<TextView>(R.id.edt_tgl)

        val ti1 = view.findViewById<TextInputLayout>(R.id.ti1)
        val ti2 = view.findViewById<TextInputLayout>(R.id.ti2)
        val ti3 = view.findViewById<TextInputLayout>(R.id.ti3)
        val ti4 = view.findViewById<View>(R.id.viewTgl)

        val animList  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom) as Animation
        val animList1  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom1) as Animation
        val animList2  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom2) as Animation
        val animList3  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom3) as Animation
        val animList4  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom4) as Animation
        val animList5  = AnimationUtils.loadAnimation(context,R.anim.anim_from_bottom5) as Animation

        ti1.animation = animList
        ti2.animation = animList1
        ti3.animation = animList2
        edt_tgl.animation = animList3
        ti4.animation = animList3
        btnSave.animation = animList4
        btnCancel.animation = animList5

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            val myFormat ="d MMM yyyy"
            val sdf = SimpleDateFormat(myFormat,Locale.US)
            edt_tgl.setText(sdf.format(cal.time))
        }
        edt_tgl.setOnClickListener {
            val dialog = DatePickerDialog(activity!!,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        }
        btnSave.setOnClickListener{
            if (edt_judul.text.isEmpty())
            {
                Toast.makeText(activity,"enter your title",Toast.LENGTH_SHORT).show()
                edt_judul.requestFocus()
            }
            else if(edt_judul.text.length > 20){
                Toast.makeText(activity,"title must be less than 20",Toast.LENGTH_SHORT).show()
                edt_judul.requestFocus()
            }
            else if(edt_ket.text.isEmpty()){
                Toast.makeText(activity,"enter the description",Toast.LENGTH_SHORT).show()
                edt_ket.requestFocus()
            }
            else if(edt_Hari.text.isEmpty()){
                Toast.makeText(activity,"enter your day",Toast.LENGTH_SHORT).show()
                edt_Hari.requestFocus()
            }
            else if((edt_Hari.text.toString().trim() != "senin")
                and  (edt_Hari.text.toString().trim() != "selasa")
                and  (edt_Hari.text.toString().trim() != "rabu")
                and  (edt_Hari.text.toString().trim() != "kamis")
                and  (edt_Hari.text.toString().trim() != "jumat")
                and  (edt_Hari.text.toString().trim() != "sabtu")
                and  (edt_Hari.text.toString().trim() != "minggu")
                and (edt_Hari.text.toString().trim()  != "Senin")
                and  (edt_Hari.text.toString().trim() != "Selasa")
                and  (edt_Hari.text.toString().trim() != "Rabu")
                and  (edt_Hari.text.toString().trim() != "Kamis")
                and  (edt_Hari.text.toString().trim() != "Jumat")
                and  (edt_Hari.text.toString().trim() != "Sabtu")
                and  (edt_Hari.text.toString().trim() != "Minggu")){
                Toast.makeText(activity,"invalid day",Toast.LENGTH_SHORT).show()
                edt_Hari.requestFocus()
            }
            else if(edt_tgl.text.isEmpty()){
                Toast.makeText(activity,"select the date",Toast.LENGTH_SHORT).show()
            }
            else{
                val mahasiswa = Mahasiswa()
                mahasiswa.kelasName = edt_judul.text.toString().trim()
                mahasiswa.kelasDescription = edt_ket.text.toString().trim()
                mahasiswa.kelasHari = edt_Hari.text.toString().trim()
                mahasiswa.kelasTanggal = edt_tgl.text.toString()

                val databaseHelper = DatabaseHelper(activity!!,null,null,1)
                databaseHelper.addMahasiswa(activity!!,mahasiswa)
                clearEdit()
                edt_judul.requestFocus()
            }

        }
        btnCancel.setOnClickListener {
            clearEdit()
            edt_judul.requestFocus()
        }
        return view
    }

    private fun clearEdit(){
        edt_Hari.text.clear()
        edt_tgl.text = ""
        edt_judul.text.clear()
        edt_ket.text.clear()
    }
}
