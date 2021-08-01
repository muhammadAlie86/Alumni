package com.alie.alumni.model

import com.alie.alumni.R

object  KelasData {
    private val name = arrayOf("Acep Sudestian",
        "Ahmad Istar Ridwan", "Aldi Bayhaqi","Andika","Ari Anwarudin", "Arif Arfan",
        "Cholidatul Hikmah", "Dony Candra Saputra", "Dwi Cahyo Prasetyo","Dwijaya Sakti Marpaung",
        "Faisal Saleh", "Febri Wijaya", "Fitri Wijayanti","Indra Kurnia Sandi","Machfud Ibrahim",
        "Maulana Ibrahim","Melki Dona Putra", "Mochamad Bagus Fahri","Muhammad Ali Mukti",
        "Muhammad Fadly Robby","Muhammad Zakaria","Nabil","Nazarullah Afrizal","Nurhayati","Puji Nuryani",
        "Rahmat Abdul Rahim","Raras Ayuning Tyas","Rizki Hidayatullah","Rudi Apriyansyah",
        "Satria Putra Meintara","Sigit Firmansyah","Tetti Purnama","Taopik Kusdinar","Yusuf Triyanto","Wiradomas",
        "Zanuar Syaban"
    )

    private val image = arrayOf(R.drawable.ic_person1,
                R.drawable.cacing, R.drawable.aldi, R.drawable.tople, R.drawable.ari,
                R.drawable.arvan, R.drawable.amoy, R.drawable.dony, R.drawable.cahyo,
                R.drawable.gondrong, R.drawable.faisal, R.drawable.febri, R.drawable.fitri,
                R.drawable.indra, R.drawable.maput, R.drawable.ic_person1, R.drawable.melky,
                R.drawable.cepot, R.drawable.ali, R.drawable.fadly, R.drawable.zaka,
                R.drawable.nabil, R.drawable.izal, R.drawable.nuy, R.drawable.puji,
                R.drawable.rahmat,R.drawable.raras,R.drawable.ic_person1,R.drawable.rudi,
                R.drawable.satria,R.drawable.sigit,R.drawable.tety,R.drawable.topik,
                R.drawable.ic_person1,R.drawable.wira,R.drawable.zanuar
    )
    private val instagram = arrayOf(
        "'Walaupun ga ngerti,tapi paham'",
        "'Pengin di ajar lagi sama Bu Siwi",
        "'Pengen dosa ,tapi takut berdosa'",
        "'Banyak tugas,No problem'",
        "'Kalau kau merasa tidak berguna,Ingatlah memang benar'",
        "'Kalem tp aslinya ...'",
        "'Don't follow my dream,only folow my Instagram'",
        "'Aku kuliah,tapi sama aja'",
        "'Aku rapopo'",
        "'Epic skill mytic",
        "'Sibuk lagi nganggur'",
        "'Kangen kampus, tapi boong'",
        "'Satuan jarak yang membuatku bahagia adalah Cm Km",
        "'Walaupun jomblo,Tapi gw bangga'",
        "'Kampus 1% prihatin,99% memprihatinkan",
        "'Diam itu emas,tapi yang ngambang'",
        "'Walaupun gw ga ngerti,tapi gw ga paham'",
        "'Susahnya kalo lagi susah'",
        "'No kopi No party'",
        "'Hiduplah walau tidak berguna'",
        "'Biarpun semok yang penting ideal'",
        "'Bisa tapi ga paham'",
        "'Pintar tapi Bodoh'",
        "'Pengin kuliah,tapi ada karir yang muliah",
        "'Matematika itu gampang sebelum x dan y menyerang'",
        "'Miss you Bayhaqi'",
        "'Pinter itu relatif,nyontek itu alternatif'",
        "'Kalau orang lain bisa, kenapa harus kita'",
        "'Indahnya berbagi contekan'",
        "'Kipas mana ouy'",
        "'Ga perlu pintar,yang penting absen'",
        "'Pria sejati pantang nyakitin tetti'",
        "'2 tahun bersama,baru tau ringan sama dijinjing,berat gw yang mikul'",
        "'Yang penting ujian masuk'",
        "'Pengen kuliah biar bisa cabut'",
        "'Ngerti ga ngerti yang penting nyimak'"
    )
    private val phone = arrayOf("0819-1273-6126",
        "0813-2469-9937", "0877-7279-2958","0812-9242-30091","0895-3687-35732", "0818-0805-1556",
        "0896-9734-2884", "0896-4668-0557", "0821-1488-7313","0878-1762-1395",
        "0878-8879-8183", "0895-3415-18744", "0838-7475-2716","0877-7060-4000","0895-1227-2105",
        "0858-9279-6397","0823-8749-3637", "0812-2222-7965","0821-1236-7425",
        "0858-8149-7408","0896-1687-1307","0878-8104-9606","0895-3332-66194","0812-9823-5296","0858-6864-5377",
        "0822-1035-0035","0858-2861-1256","0877-8490-5664","0822-9978-2753",
        "0895-6358-71481","0895-3738-73648","0853-9225-3809","0856-2436-6636","0811-1156-990","0896-7698-9308",
        "0813-1910-4235"
    )

    val listData : ArrayList<Kelas>

        get()
        {
            val list = ArrayList<Kelas>()
            for (position in name.indices){
                val kelas = Kelas()
                kelas.name = name[position]
                kelas.photo = image[position]
                kelas.intagram = instagram[position]
                kelas.phone = phone[position]
                list.add(kelas)
        }
        return list
    }
}