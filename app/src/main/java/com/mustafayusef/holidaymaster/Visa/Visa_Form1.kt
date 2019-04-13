package com.mustafayusef.holidaymaster.Visa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_visa__form1.*

class Visa_Form1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa__form1)
        val trans=supportFragmentManager.beginTransaction()
        val frag=formOne()
        trans.replace(R.id.fragmentHolder,frag)
        trans.addToBackStack(null)
        trans.commit()
      Back.visibility=View.INVISIBLE
    }
    fun showFormTow(view:View){
//        val intent= Intent(this@Visa_Form1,Visa_Form2::class.java)
//        startActivity(intent)
        val trans=supportFragmentManager.beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
        val frag=formTow()
        trans.replace(R.id.fragmentHolder,frag)
        trans.addToBackStack(null)
        trans.commit()


        Back.visibility=View.VISIBLE
    }
    fun backToTow(view: View){
        val trans=supportFragmentManager.beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
        val frag=formOne()
        trans.replace(R.id.fragmentHolder,frag)
        trans.addToBackStack(null)
        trans.commit()
        Back.visibility=View.INVISIBLE

    }
    fun backVisa(view: View){
        val intent=Intent(this@Visa_Form1,searchVisa::class.java)
        startActivity(intent)
    }

    fun DateOfBirth(view: View){

    }
    fun UploadPhoto(view: View){

    }



    fun passDate(view: View){

    }
    fun passExpir(view: View){

    }
    fun passPhoto(view: View){

    }
    fun otherFile(view: View){

    }


}
