package com.mustafayusef.holidaymaster

import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.mustafayusef.holidaymaster.login.LoginMember
import kotlinx.android.synthetic.main.activity_main_navigation.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.bottom_sheet_airport.view.*

class mainNavigation : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)

        navController= Navigation.findNavController(this, R.id.navHost)
      //  bottomNav.setupWithNavController(navController)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener {controller, destination, arguments ->
            if(destination.id==R.id.spicialGroup||destination.id==R.id.spicialTour){
                bottomNav.visibility=View.VISIBLE
            }else{
                bottomNav.visibility=View.GONE
            }
        }
       // showDailog()
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.mainActivity||
            navController.currentDestination?.id==R.id.dashboard2) {
            finish()

            // do nothing
        } else if(navController.currentDestination?.id==R.id.successTicket){
            navController.navigate(R.id.searchActivity)
        } else if(navController.currentDestination?.id==R.id.spicialGroup){
            navController.navigate(R.id.dashboard2)
        }else if(navController.currentDestination?.id==R.id.searchActivity){
            navController.navigate(R.id.dashboard2)
        }else if(navController.currentDestination?.id==R.id.searchVisa){
            navController.navigate(R.id.dashboard2)
        }else if(navController.currentDestination?.id==R.id.main_group){
            navController.navigate(R.id.dashboard2)
        }else if(navController.currentDestination?.id==R.id.tours_main){
            navController.navigate(R.id.dashboard2)
        }
        else {
            super.onBackPressed()
        }

    }

    fun showDailog(){
//        val view = layoutInflater.inflate(R.layout.activity_lottie , null)
//        val display =this!!.windowManager.defaultDisplay
//        val size = Point()
//        display.getSize(size)
//        val width = size.x
//        val height = size.y


        //view.minimumHeight=600
        val dview: View = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.activity_lottie, null)

        val builder = AlertDialog.Builder(this!!).setView(dview)



        val malert= builder.show()
        malert.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))



       // mBottomSheetDialog.setContentView(view)
        //mBottomSheetDialog.setCancelable(true)
        malert.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )


        Handler().postDelayed({

            Handler().postDelayed({
                if(LoginMember.cacheObj.token!=""){
                    navController?.navigate(R.id.dashboard2)
                    malert?.dismiss()
                }else{
                    navController?.navigate(R.id.mainActivity)
                    malert?.dismiss()
                    // activity!!.finish()
                }
            },100)
        },4000)

    }
}
