package com.alie.alumni.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.alie.alumni.R
import com.alie.alumni.fragment.FragmentHome
import com.alie.alumni.model.Mahasiswa
import kotlinx.android.synthetic.main.mahasiswa_update.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MahasiswaAdapter(private var mContext: Context, private var listMahasiswa: ArrayList<Mahasiswa>)
    : RecyclerView.Adapter<MahasiswaAdapter.Companion.MahasiswaHolder> (){

    companion object {

        class MahasiswaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var tvJudul: TextView
            var tvDesc: TextView
            var tvHari: TextView
            var tvTanggal: TextView
            var imgHome: ImageView
            var rbHome: RadioButton

            init {
                tvJudul = itemView.findViewById(R.id.nameHome)
                tvDesc = itemView.findViewById(R.id.descHome)
                tvHari = itemView.findViewById(R.id.hari)
                tvTanggal = itemView.findViewById(R.id.tanggal)
                imgHome = itemView.findViewById(R.id.imageMenu)
                rbHome = itemView.findViewById(R.id.rbHome)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return MahasiswaHolder(view)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: MahasiswaHolder, position: Int) {
        val mahasiswa = listMahasiswa[position]
        holder.tvHari.text = mahasiswa.kelasHari
        holder.tvTanggal.text = mahasiswa.kelasTanggal
        holder.tvJudul.text = mahasiswa.kelasName
        holder.tvDesc.text = mahasiswa.kelasDescription

        holder.imgHome.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val popupMenu = PopupMenu(mContext,holder.imgHome)
                popupMenu.inflate(R.menu.pop_menu)
                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{

                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        when(item!!.itemId){
                            R.id.edit ->{
                                val inflater = LayoutInflater.from(mContext)
                                val view = inflater.inflate(R.layout.mahasiswa_update, null)

                                val tvName = view.findViewById<TextView>(R.id.edtUpJudul)
                                val tvDesc = view.findViewById<TextView>(R.id.edtUpKet)
                                val tvHari = view.findViewById<TextView>(R.id.edtUpHari)
                                val tvTgl = view.findViewById<TextView>(R.id.edtUpTgl)

                                val cal = Calendar.getInstance()

                                val dateSetListener =
                                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                                        cal.set(Calendar.YEAR, year)
                                        cal.set(Calendar.MONTH, month)
                                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                                        val myFormat = "dd MMM yyyy"
                                        val sdf = SimpleDateFormat(myFormat, Locale.US)
                                        tvTgl.setText(sdf.format(cal.time))
                                    }
                                tvTgl.setOnClickListener {
                                    val dialog = DatePickerDialog(
                                        mContext, dateSetListener,
                                        cal.get(Calendar.YEAR),
                                        cal.get(Calendar.MONTH),
                                        cal.get(Calendar.DAY_OF_MONTH)
                                    )
                                    //dialog.datePicker.maxDate = CalenderHelper.getCurrentDateInMills()
                                    dialog.show()
                                }

                                tvName.text = mahasiswa.kelasName
                                tvDesc.text = mahasiswa.kelasDescription
                                tvHari.text = mahasiswa.kelasHari
                                tvTgl.text = mahasiswa.kelasTanggal

                                val builder = AlertDialog.Builder(mContext)
                                    .setTitle("")
                                    .setView(view)
                                    .setPositiveButton("update", DialogInterface.OnClickListener { dialog, which ->

                                    }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                                    })
                                val alert = builder.create()

                                tvName.text = ""
                                tvHari.text = ""
                                tvDesc.text = ""
                                tvTgl.text = ""
                                alert.show()
                                alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(object : View.OnClickListener{

                                    override fun onClick(v: View?) {
                                        val isUpdate = FragmentHome.databaseHelper.updateMahasiswa(
                                            mahasiswa.kelasID.toString(),
                                            view.edtUpJudul.text.toString(),
                                            view.edtUpKet.text.toString(),
                                            view.edtUpHari.text.toString(),
                                            view.edtUpTgl.text.toString()
                                        )

                                        if (view.edtUpJudul.text.isEmpty())
                                        {
                                            Toast.makeText(mContext,"enter your title",Toast.LENGTH_SHORT).show()
                                            view.edtUpJudul.requestFocus()
                                        }else if(view.edtUpJudul.text.length > 20){
                                            Toast.makeText(mContext,"title must be less than 20",Toast.LENGTH_SHORT).show()
                                            view.edtUpJudul.requestFocus()
                                        }
                                        else if(view.edtUpKet.text.isEmpty()){
                                            Toast.makeText(mContext,"enter the description",Toast.LENGTH_SHORT).show()
                                            view.edtUpKet.requestFocus()
                                        }
                                        else if(view.edtUpHari.text.isEmpty()){
                                            Toast.makeText(mContext,"enter your day",Toast.LENGTH_SHORT).show()
                                            view.edtUpHari.requestFocus()
                                        }
                                        else if((view.edtUpHari.text.toString().trim() != "senin")
                                            and  (view.edtUpHari.text.toString().trim() != "selasa")
                                            and  (view.edtUpHari.text.toString().trim() != "rabu")
                                            and  (view.edtUpHari.text.toString().trim() != "kamis")
                                            and  (view.edtUpHari.text.toString().trim() != "jumat")
                                            and  (view.edtUpHari.text.toString().trim() != "sabtu")
                                            and  (view.edtUpHari.text.toString().trim() != "minggu")
                                            and  (view.edtUpHari.text.toString().trim()  !="Senin")
                                            and  (view.edtUpHari.text.toString().trim() != "Selasa")
                                            and  (view.edtUpHari.text.toString().trim() != "Rabu")
                                            and  (view.edtUpHari.text.toString().trim() != "Kamis")
                                            and  (view.edtUpHari.text.toString().trim() != "Jumat")
                                            and  (view.edtUpHari.text.toString().trim() != "Sabtu")
                                            and  (view.edtUpHari.text.toString().trim() != "Minggu"))
                                        {
                                            Toast.makeText(mContext,"invalid day",Toast.LENGTH_SHORT).show()
                                            view.edtUpHari.requestFocus()
                                        }
                                        else if(view.edtUpTgl.text.isEmpty()){
                                            Toast.makeText(mContext,"select the date",Toast.LENGTH_SHORT).show()
                                        }

                                        else if (isUpdate == true) {

                                            listMahasiswa[position].kelasName = view.edtUpJudul.text.toString()
                                            listMahasiswa[position].kelasDescription = view.edtUpKet.text.toString()
                                            listMahasiswa[position].kelasHari = view.edtUpHari.text.toString()
                                            listMahasiswa[position].kelasTanggal = view.edtUpTgl.text.toString()
                                            notifyDataSetChanged()
                                            alert.dismiss()

                                            Toast.makeText(mContext, "Updating successfully", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(mContext, "updating error", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                })
                                return true
                            }
                            R.id.delete ->{
                                val mahasiswaName = mahasiswa.kelasName

                                AlertDialog.Builder(mContext)
                                    .setTitle("Warning")
                                    .setMessage("Are you sure to delete $mahasiswaName ?")
                                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                                        if (FragmentHome.databaseHelper.deleteMahasiswa(mahasiswa.kelasID)) {

                                            listMahasiswa.removeAt(position)
                                            notifyItemRemoved(position)
                                            notifyItemRangeChanged(position, listMahasiswa.size)
                                            Toast.makeText(mContext, "$mahasiswaName Deleted", Toast.LENGTH_SHORT)
                                                .show()
                                        } else {

                                            Toast.makeText(mContext, "Failed Deleting", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                                    .show()
                                return true
                            }
                        }
                        return false
                    }
                })
                popupMenu.show()
            }
        })

        holder.rbHome.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mahasiswa = listMahasiswa[position]
                val mahasiswaName = mahasiswa.kelasName

                if (FragmentHome.databaseHelper.deleteMahasiswa(mahasiswa.kelasID)) {

                    listMahasiswa.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listMahasiswa.size)
                    Toast.makeText(mContext, "$mahasiswaName Done", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    Toast.makeText(mContext, "filed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
