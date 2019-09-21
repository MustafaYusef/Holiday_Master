package com.mustafayusef.holidaymaster.Groups

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.group

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.group_book_fragment.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class groupBook : Fragment(),lesener {
    override fun onSucsessGetOrderGroup(Response: TourOrder) {
       // context?.toast(Response.toString())
        group=Response
    }

    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=123
    var dateBirth=""
var group:TourOrder?=null
    var datePass=""
   var dateExpir=""
    var imagePath=""
    override fun OnStart() {

    }

    override fun onFailer(message: String) {
        context?.toast(message.toString())
    }

    override fun onSucsess(Response: List<group>) {

    }

    override fun onSucsessBook(Response: tok) {

    }

    companion object {
        fun newInstance() = groupBook()
    }
    var firstNameGroupArray= mutableListOf<String>()
    var lastNameGroupArray= mutableListOf<String>()
    var passNoGroupArray= mutableListOf<String>()
    var nationalGroupArray= mutableListOf<String>()
    var dateBookGroupArray= mutableListOf<String>()
    var DatePasswordArray= mutableListOf<String>()
    var DateExpairPasswordArray= mutableListOf<String>()

    var PassportImageArray= mutableListOf<String>()
    var photoScanImageArray= mutableListOf<String>()
    var OtherImageArray= mutableListOf<String>()

    var Photos:MutableList<MultipartBody.Part> ?=mutableListOf<MultipartBody.Part>()


    var PassportImageUri:Uri?=null
    var photoScanImageUri:Uri?=null
    var OtherImageUri:Uri?=null
    var indexArray=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.group_book_fragment, container, false)
    }
     var GroupviewModel:groupsViewModel?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      var token=arguments?.getString("token")
        indexArray= arguments?.getInt("persons")!!
        context?.toast(indexArray.toString())
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= groupViewModelFactory(repostary)
        GroupviewModel = ViewModelProviders.of(this,factory).get(groupsViewModel::class.java)
        GroupviewModel?.dataLesener=this
        GroupviewModel?.getOrderGroup(token!!)
    }
   var indexPerson:Int=0
    var i=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextPerson?.setOnClickListener {
            if (indexArray >1) {
                if(!firstNameGroup.text.isNullOrEmpty()&&!lastNameGroup.text.isNullOrEmpty()&&
                    !passNoGroup.text.isNullOrEmpty()&&!nationalGroup.text.isNullOrEmpty()
                    &&!dateBirth.isNullOrEmpty()
                    &&!datePass.isNullOrEmpty()&&!dateExpir.isNullOrEmpty()
                    &&PassportImageUri!=null&&photoScanImageUri!=null){


                    bookGroupCon?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
                    bookGroupCon?.smoothScrollTo(0,0)
                    personNum?.text = "Person ${indexPerson  +2}"
                    Photos!!.add(getFile(PassportImageUri!!,i++))
                    Photos!!.add(getFile(photoScanImageUri!!,i++))
                    if(OtherImageUri!=null){
                       Photos!!.add(getFile(OtherImageUri!!,i++))
                    }
                    firstNameGroupArray.add( firstNameGroup.text.toString())
                    lastNameGroupArray.add( lastNameGroup.text.toString())
                    passNoGroupArray.add( passNoGroup.text.toString())
                    nationalGroupArray.add( nationalGroup.text.toString())
                    dateBookGroupArray.add( dateBirth)
                    DatePasswordArray.add( datePass)
                    DateExpairPasswordArray.add( dateExpir)

                    firstNameGroup?.setText("")
                    lastNameGroup?.setText("")
                    passNoGroup?.setText("")
                    nationalGroup?.setText("")
                    dateBookGroup?.text="Date of Birth"
                    DatePassword?.text="Password issue Date"
                    DateExpairPassword?.text="Password Expiry Date"

                    PassportImage?.setImageResource(0)
                    photoScanImage?.setImageResource(0)
                    OtherImage?.setImageResource(0)
                     PassportImageUri=null
                     photoScanImageUri=null
                     OtherImageUri=null
                    indexPerson++
                    indexArray--
                }else{
                    context?.toast("Fill all required field ")
                }

            }else if(indexArray==1){
                if(!firstNameGroup.text.isNullOrEmpty()&&!lastNameGroup.text.isNullOrEmpty()&&
                    !passNoGroup.text.isNullOrEmpty()&&!nationalGroup.text.isNullOrEmpty()
                    &&!dateBirth.isNullOrEmpty()
                    &&!datePass.isNullOrEmpty()&&!dateExpir.isNullOrEmpty()
                    &&PassportImageUri!=null&&photoScanImageUri!=null){


                    Photos!!.add(getFile(PassportImageUri!!,i++))
                    Photos!!.add(getFile(photoScanImageUri!!,i++))
                    if(OtherImageUri!=null){
                        Photos!!.add(getFile(OtherImageUri!!,i++))
                    }
                    firstNameGroupArray.add( firstNameGroup.text.toString())
                    lastNameGroupArray.add( lastNameGroup.text.toString())
                    passNoGroupArray.add( passNoGroup.text.toString())
                    nationalGroupArray.add( nationalGroup.text.toString())
                    dateBookGroupArray.add( dateBirth)
                    DatePasswordArray.add( datePass)
                    DateExpairPasswordArray.add( dateExpir)




                    indexArray--
                }else{
                    context?.toast("Fill all required field ")
                }
                println("Debage       "+ firstNameGroupArray)
            }
        }


        dateBookGroup?.setOnClickListener {
          getDate(0)
        }
        DatePassword?.setOnClickListener {
           getDate(1)
        }
        DateExpairPassword?.setOnClickListener {
         getDate(2)
        }
        PassportImageBtn?.setOnClickListener {
            openImage(0)
        }
        photoScanBtn?.setOnClickListener {
         openImage(1)
        }
        OtherImageBtn?.setOnClickListener {
            openImage(2)
        }
    }
    fun getFile(imageUri:Uri,i:Int):MultipartBody.Part{
        var path= getPathFromURI(imageUri!!)
        var oregnal = File(path)
//  var oregnal = File(getPathFromURI(imageUri))
        var imageFile = RequestBody.create(
            MediaType.parse(context?.contentResolver?.getType(imageUri)!!),
            oregnal
        )
        var imagesBodyRequest= MultipartBody.Part.createFormData("image$i", oregnal.name, imageFile)
        return imagesBodyRequest
    }
   fun openImage(flage:Int){
       if (Build.VERSION.SDK_INT < 19) {
           var intent = Intent()
           intent.type = "image/*"
           intent.action = Intent.ACTION_GET_CONTENT
           startActivityForResult(
               Intent.createChooser(intent, "Select Picture")
               , flage
           )
       } else {
           if (Build.VERSION.SDK_INT >=23) {
               if(checkPermissionREAD_EXTERNAL_STORAGE(context!!)){
                   var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                   intent.action= Intent.ACTION_GET_CONTENT
                   // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                   intent.addCategory(Intent.CATEGORY_OPENABLE)
                   intent.type = "image/*"
                   startActivityForResult(intent, flage);
               }else{
                   context?.toast("you can not pick images")
               }
           }else{
               //  var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
               var intent = Intent()

               intent.action= Intent.ACTION_GET_CONTENT
               intent.addCategory(Intent.CATEGORY_OPENABLE)
               intent.type = "image/*"
               startActivityForResult(intent, flage);
           }
       }
   }


   fun getDate(flage:Int){
       val c = Calendar.getInstance()
       val day = c.get(Calendar.DAY_OF_MONTH)
       val month = c.get(Calendar.MONTH)
       val year = c.get(Calendar.YEAR)

       val dpd = DatePickerDialog(
           context!!,

           DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

             var  date = "$year-${month + 1}-$dayOfMonth"
               if(flage==0){
                   dateBookGroup.text=date
                   dateBirth=date
               }else if(flage==1){
                   DatePassword?.text=date
                   datePass=date
               }else{
                   DateExpairPassword.text= date
                   dateExpir=date
               }
           },
           year,
           month,
           day
       )
       dpd.show()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK
            && null != data
        ) {


            var imageUri: Uri? = data.data
           // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)
             PassportImageUri=imageUri

            val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,PassportImageUri)
            PassportImage.setImageBitmap(bitmap)
            //circleImageView.setImageBitmap(bitmap)

//            getPathFromURI(imageUri!!)
//            var oregnal = File(imagePath)
////                        var oregnal = File(getPathFromURI(imageUri))
//            imageFile = RequestBody.create(
//                MediaType.parse(context?.contentResolver?.getType(imageUri)!!),
//                oregnal
//            )
//            imagesBodyRequest= MultipartBody.Part.createFormData("image", oregnal.name, imageFile)

        }else if (requestCode == 1 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            var imageUri: Uri? = data.data
            // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)

            photoScanImageUri=imageUri

            val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,photoScanImageUri)
            photoScanImage.setImageBitmap(bitmap)

        }else if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data
                ) {
            var imageUri: Uri? = data!!.data
            // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)

            OtherImageUri=imageUri
            val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,OtherImageUri)
            OtherImage.setImageBitmap(bitmap)
        }

    }
    @SuppressLint("NewApi")
    fun getPathFromURI(uri: Uri):String {
        var path: String = uri.path!! // uri = any content Uri
     var imagePath=""
        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) { // files selected from "Documents"
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else { // files selected from all other sources, especially on Samsung devices
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            ) // some example data you can query
            val cursor = context?.contentResolver ?.query(
                databaseUri,
                projection, selection, selectionArgs, null
            )
            if(cursor==null){
                imagePath = path
            }else
                if (cursor!!.moveToFirst()) {
                    val columnIndex = cursor!!.getColumnIndex(projection[0])
                    //  if (cursor.getType(columnIndex) == FIELD_TYPE_STRING) {
                    imagePath = cursor!!.getString(columnIndex)
                    // }

                    // Log.e("path", imagePath);

                }


            cursor?.close()
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, e.message, e)
        }
        return imagePath
    }

    fun checkPermissionREAD_EXTERNAL_STORAGE(
        context: Context
    ):Boolean {
        var currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                showDialog("External storage", context,
//                    Manifest.permission.READ_EXTERNAL_STORAGE);
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                        .requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) ,
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    fun showDialog(msg:String, context: Context,
                   permission:String) {
        var alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
            DialogInterface.OnClickListener(
                fun(dialog: DialogInterface, which:Int) {
                    ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(permission ),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            )



        )
        val  alert: AlertDialog = alertBuilder.create();
        alert.show();
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
//                    var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                    intent.action= Intent.ACTION_GET_CONTENT
//                    // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                    intent.addCategory(Intent.CATEGORY_OPENABLE)
//                    intent.type = "image/*"
//                    startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    context?.toast("tou can not pick image")
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}
