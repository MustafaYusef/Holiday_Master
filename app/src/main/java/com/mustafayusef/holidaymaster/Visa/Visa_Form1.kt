package com.mustafayusef.holidaymaster.Visa

import android.Manifest.permission.CAMERA
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Gallery
import android.widget.Toast
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_visa__form1.*
import java.util.*

class Visa_Form1 : AppCompatActivity() {
     var fragmentFlage=true
    var dateOfBirth=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa__form1)
        fragmentFlage=true
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
        if(fragmentFlage){
            val trans=supportFragmentManager.beginTransaction()
            trans.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
            val frag=formTow()
            trans.replace(R.id.fragmentHolder,frag)
            trans.addToBackStack(null)
            trans.commit()
            Back.visibility=View.VISIBLE
            fragmentFlage=false
        }

    }
    fun backToTow(view: View){

        val trans=supportFragmentManager.beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
        val frag=formOne()
        trans.replace(R.id.fragmentHolder,frag)
        trans.addToBackStack(null)
        trans.commit()
        Back.visibility=View.INVISIBLE
        fragmentFlage=true
    }
    fun backVisa(view: View){
        val intent=Intent(this@Visa_Form1,searchVisa::class.java)
        startActivity(intent)
    }

    fun DateOfBirth(view: View){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
            this,

            DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                depText.text= "$year-${month+1}-$dayOfMonth"
                dateOfBirth = "$year-${month+1}-$dayOfMonth"
            },
            year,
            month,
            day
        )

        //show datepicker
        dpd.show()
    }
    fun UploadPhoto(view: View){
//
//            val pictureDialog = AlertDialog.Builder(this)
//            pictureDialog.setTitle("Select Action")
//            val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
//            pictureDialog.setItems(pictureDialogItems
//            ) { dialog, which ->
//                when (which) {
//                    0 -> choosePhotoFromGallary()
//                    1 -> takePhotoFromCamera()
//                }
//            }
//            pictureDialog.show()
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
