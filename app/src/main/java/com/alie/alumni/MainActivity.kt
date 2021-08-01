package com.alie.alumni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alie.alumni.fragment.FragmentAdd
import com.alie.alumni.fragment.FragmentBook
import com.alie.alumni.fragment.FragmentHome
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.bottom_nav_view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation(savedInstanceState)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        space.onSaveInstanceState(outState)
    }

    private fun initBottomNavigation(savedInstanceState: Bundle?) {
        space.initWithSaveInstanceState(savedInstanceState)
        space.setCentreButtonSelectable(true)
        space.setCentreButtonSelectable(false)
        space.addSpaceItem(
            SpaceItem(
                "Task",
                R.drawable.ic_assignment
            )
        )
        space.addSpaceItem(
            SpaceItem(
                "Friend",
                R.drawable.ic_person
            )
        )
        space.setSpaceOnClickListener(object :  SpaceOnClickListener {
            override fun onCentreButtonClick() {
               setupFragment(fragment = FragmentAdd())
                //layoutDialog(R.layout.bottom_sheet_add)
            }
            override fun onItemReselected(itemIndex: Int, itemName: String?) {

                when (itemIndex) {
                    0 -> setupFragment(fragment = FragmentHome())
                    1 -> setupFragment(fragment = FragmentBook())
                    else
                    -> setupFragment(fragment = FragmentAdd())
                }
            }
            override fun onItemClick(itemIndex: Int, itemName: String?) {

                when (itemIndex) {
                    0 -> setupFragment(fragment = FragmentHome())
                    1 -> setupFragment(fragment = FragmentBook())
                    else
                        -> setupFragment(fragment = FragmentAdd())
                }
            }

        })
    }
     fun setupFragment(fragment: Fragment){
         val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.container,fragment)
         fragmentTransaction.addToBackStack(null)
         fragmentTransaction.commit()
    }
}




