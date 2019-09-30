package com.mustafayusef.holidaymaster

import android.content.Intent

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.login.LoginMember
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.splash.*


class MainActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.mainActivity) {
//
//                toolbar?.visibility = View.GONE
//            } else {
//
//                toolbar?.visibility = View.VISIBLE
//            }
//
//        }

        if (LoginMember.cacheObj.token!=""){
            view?.findNavController()?.navigate(R.id.dashboard2)
        }

        member.startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))
        userBrows.startAnimation(AnimationUtils.loadAnimation(context,R.anim.right_to_left))
        member.setOnClickListener {
            member.animate()
                .translationXBy(-750f)
                .duration = 1200
            Handler().postDelayed({

                Handler().postDelayed({

                    view?.findNavController().navigate(R.id.LoginFragment)

                },10)
            },1000)
        }
        userBrows.setOnClickListener {
            userBrows.animate()
                .translationXBy(750f)
                .duration = 1200
            Handler().postDelayed({

                Handler().postDelayed({

                    view?.findNavController().navigate(R.id.dashboard2)

                },10)
            },1000)
        }
    }

//    fun goToLogin (view: View) {
//        userBrows.animate()
//            .translationXBy(750f)
//            .duration = 1200
//        Handler().postDelayed({
//
//            Handler().postDelayed({
//
//                view?.findNavController().navigate(R.id.dashboard2)
//
//            },10)
//        },1000)
//
//    }
//    fun goToSearch (view:View){
//        userBrows.animate()
//            .translationXBy(750f)
//            .duration = 1200
//        Handler().postDelayed({
//
//            Handler().postDelayed({
//
//                view?.findNavController().navigate(R.id.dashboard2)
//
//            },10)
//        },1000)
//
//    }


}
