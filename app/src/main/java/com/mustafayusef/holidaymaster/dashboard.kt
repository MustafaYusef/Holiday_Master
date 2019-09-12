package com.mustafayusef.holidaymaster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.login.LoginMember
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


        flight.startAnimation(AnimationUtils.loadAnimation(context,R.anim.top_bottum))
        hotel.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))

        visa.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        prof.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))
        Tours.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        groups.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))



        flight.setOnClickListener {
            flight.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchTecket)
        }

        hotel.setOnClickListener {
            hotel.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchHotel)
        }

        visa.setOnClickListener {
            visa.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.dashToSearchVisa)
        }

        Tours.setOnClickListener {
            Tours.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.fromDashToTour)
        }

        groups.setOnClickListener {
            groups.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
            view?.findNavController()?.navigate(R.id.fromDashToGroup)
        }

        prof.setOnClickListener {
            prof.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in))
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
