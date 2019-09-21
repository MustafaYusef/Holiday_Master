package com.mustafayusef.holidaymaster.Visa

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
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.MainActivity
import com.mustafayusef.holidaymaster.Models.country
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_form_one.*
import kotlinx.android.synthetic.main.group_book_fragment.*
import kotlinx.android.synthetic.main.progress.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File
import java.util.*

class formOne:Fragment(),lesener{
    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onSucsess(Response: List<country>) {
    }

    override fun onSucsessSearch(Response: List<country>) {
    }

    override fun onSucsessBook(message: msg) {
        context?.toast(message.msg)
        progLoading?.visibility=View.GONE
    }

    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=123
   var photoScanImageVUri:Uri?=null
    var PassImageVUri:Uri?=null
    var OtherImageVisaUri:Uri?=null
    var FrontIdentImageUri:Uri?=null
    var backIdentImageUri:Uri?=null

    var photoScanImageVBody : MultipartBody.Part?=null
    var PassImageVUriBody:MultipartBody.Part?=null
    var OtherImageVisaBody:MultipartBody.Part?=null
    var FrontIdentImageBody:MultipartBody.Part?=null
    var backIdentImageBody:MultipartBody.Part?=null

    var visaViewModel:VisaViewModel?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_form_one,container,false)
    }
    var visa:country?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.ToolBar)
//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.formOne) {
//
//                toolbar?.visibility = View.VISIBLE
//            } else {
//
//                toolbar?.visibility = View.GONE
//            }

//        }

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= VisaViewModelFactory(repostary)
        visaViewModel = ViewModelProviders.of(this,factory).get(VisaViewModel::class.java)
        visaViewModel?.dataLesener=this

           visa= arguments!!.getSerializable("visa") as country?


        dateBirthV?.setOnClickListener {
            getDate(0)
        }
        passDateV?.setOnClickListener {
            getDate(1)
        }
        passExpirDateV?.setOnClickListener {
            getDate(2)
        }


        photoScanVisa?.setOnClickListener {
            openImage(0)
        }
        passScanBtnV?.setOnClickListener {
            openImage(1)
        }
        OtherImageV?.setOnClickListener {
            openImage(2)
        }
        FrontIdentImageBtn?.setOnClickListener {
            openImage(3)
        }
        backIdentImageBtn?.setOnClickListener {
            openImage(4)
        }

        ApplayVisa?.setOnClickListener {

          if(!firstNameV?.text.toString().isNullOrEmpty()&&!lastNameV?.text.toString().isNullOrEmpty()&&
              !nationalityV?.text.toString().isNullOrEmpty()
              &&!passPortNum?.text.toString().isNullOrEmpty()){

              visaViewModel!!.BookVis(LoginMember.cacheObj.token,firstNameV?.text.toString(),
                  lastNameV?.text.toString(),
                  passPortNum?.text.toString(),
                  nationalityV?.text.toString(),
                  dateBirthV.text.toString(),
                  passDateV.text.toString(),
                  passExpirDateV.text.toString(),
                  visa!!,
                  photoScanImageVBody,
               PassImageVUriBody,
               OtherImageVisaBody,
               FrontIdentImageBody,
               backIdentImageBody
                  )


          }else{
              context?.toast("Please Fill all required Field")
          }
        }
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
                    dateBirthV.text=date
                }else if(flage==1){
                    passDateV?.text=date
                }else{
                    passExpirDateV.text= date
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
            photoScanImageVUri=imageUri

            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,photoScanImageVUri)
            photoScanImageV.setImageBitmap(bitmap)
            //circleImageView.setImageBitmap(bitmap)
            photoScanImageVBody=getFile(photoScanImageVUri!!,0)
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

            PassImageVUri=imageUri

            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,PassImageVUri)
            PassImageV.setImageBitmap(bitmap)
            PassImageVUriBody=getFile(PassImageVUri!!,1)


        }else if (requestCode == 2 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            var imageUri: Uri? = data!!.data
            // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)

            OtherImageVisaUri=imageUri
            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,OtherImageVisaUri)
            OtherImageVisa.setImageBitmap(bitmap)
            OtherImageVisaBody=getFile(OtherImageVisaUri!!,2)
        }else if (requestCode == 3 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            var imageUri: Uri? = data!!.data
            // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)

            FrontIdentImageUri=imageUri
            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,FrontIdentImageUri)
            FrontIdentImage.setImageBitmap(bitmap)
            FrontIdentImageBody=getFile(FrontIdentImageUri!!,3)
        }else if (requestCode == 4 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            var imageUri: Uri? = data!!.data
            // val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)

            backIdentImageUri=imageUri

            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,backIdentImageUri)
            backIdentImage.setImageBitmap(bitmap)
            backIdentImageBody=getFile(backIdentImageUri!!,4)
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




