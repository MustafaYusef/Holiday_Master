package com.mustafayusef.holidaymaster

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.utils.toast
import kotlinx.android.synthetic.main.activity_dashboard.*

class dashboard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        whats?.setOnClickListener {
            val contact ="+905384433030"  // use country code with your phone number
            val url = "https://api.whatsapp.com/send?phone=$contact"
            try {
                val pm = context?.packageManager
                pm?.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                context?.startActivity(i)
            } catch (e: PackageManager.NameNotFoundException) {
                context?.toast("لا يوجد whatsapp في جهازك")

            }
        }
//        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.dashboard2) {
//
//                toolbar?.visibility = View.GONE
//            } else {
//
//                toolbar?.visibility = View.VISIBLE
//            }
//
//        }


//        flight.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
//        hotel.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))
//
//        visa.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
//        SpicialOffers.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))
//        Tours.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))
//        groups.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))



        flight.setOnClickListener {
            //flight.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchTecket)
        }

        hotel.setOnClickListener {
           // hotel.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchHotel)
        }

        visa.setOnClickListener {
           // visa.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchVisa)
        }

        Tours.setOnClickListener {
           // Tours.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.fromDashToTour)
        }

        groups.setOnClickListener {
            //groups.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.fromDashToGroup)
        }

        SpicialOffers.setOnClickListener {
            //groups.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.spicialTour)
        }
        prof.setOnClickListener {
           // prof.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            if(LoginMember.cacheObj.token!=""){
                view?.findNavController()?.navigate(R.id.fromDashToProfile)
            }else{
                view?.findNavController()?.navigate(R.id.LoginFragment)
                Toast.makeText(context,"there is no account for you please login", Toast.LENGTH_SHORT).show()

            }
        }
    }



//    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }


}
