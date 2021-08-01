package com.alie.alumni.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.alie.alumni.R
import com.alie.alumni.model.Kelas

class ListKelasAdapter : RecyclerView.Adapter<ListKelasAdapter.Companion.ListKelasHolder>
{
    private  var context: Context
    private  var listKelas: ArrayList<Kelas>

    constructor(context: Context, list: ArrayList<Kelas>) : super()// secondary constructor
    {
        this.listKelas = list
        this.context = context
    }
    companion object // static class
    {
        class ListKelasHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        {
            var tvName: TextView
            var imgList: ImageView
            var itemKelas : LinearLayout

            init
            {
                itemKelas = itemView.findViewById(R.id.kelasItem) as LinearLayout
                tvName = itemView.findViewById(R.id.nameList) as TextView
                imgList = itemView.findViewById(R.id.imageList) as ImageView
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKelasHolder
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.roll_item, parent, false)
        val holder = ListKelasHolder(view)

        val mDialog = Dialog(context)
        mDialog.setContentView(R.layout.layout_dialog_book)
        val nameDial = mDialog.findViewById<TextView>(R.id.nameDial)
        val btnPhone = mDialog.findViewById<Button>(R.id.phoneDial)
        val btnIns = mDialog.findViewById<TextView>(R.id.insDial)
        val imgDial = mDialog.findViewById<ImageView>(R.id.imgDial)

        btnPhone.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val phoneNumber = listKelas.get(holder.adapterPosition).phone
                val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(phoneIntent)
            }

        })

        holder.itemKelas.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {

                nameDial.text = listKelas.get(holder.adapterPosition).name
                btnPhone.text = listKelas.get(holder.adapterPosition).phone
                btnIns.text = listKelas.get(holder.adapterPosition).intagram
                imgDial.setImageResource(listKelas.get(holder.adapterPosition).photo)

                Toast.makeText(context,""+ listKelas.get(holder.adapterPosition).name,Toast.LENGTH_SHORT).show()
                mDialog.show()
            }
        })
        return holder
    }
    override fun getItemCount() = listKelas.size

    override fun onBindViewHolder(holder: ListKelasHolder, position: Int)
    {
        val listKelas = listKelas[position]

        holder.tvName.text = listKelas.name
        holder.imgList.setImageResource(listKelas.photo)

        val anim  = AnimationUtils.loadAnimation(context,R.anim.anim_from_right) as Animation
        holder.imgList.animation = anim
        holder.tvName.animation = anim
    }
}

