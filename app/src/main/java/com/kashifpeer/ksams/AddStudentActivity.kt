package com.kashifpeer.ksams

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_add_student.*

class AddStudentActivity : AppCompatActivity() {
    //permission constraints
    private val CAMERA_REQUEST_CODE = 100;
    private val STORAGE_REQUEST_CODE = 101;

    //IMAGE PICK CONSTANTS
    private val IMAGE_PICK_CAMERA_CODE = 102
    private val IMAGE_PICK_GALLERY_CODE = 103

    private val TAG: String = AddStudentActivity::class.java.getSimpleName()

    //arrays of permission
    private lateinit var cameraPermissions: Array<String>//camera and storage
    private lateinit var storagePermissions: Array<String>//only storage

    private var imageUri:Uri?=null
    //variables that will contain data to save in database

    private var name: String? = ""
    private var phone: String? = ""
    private var email: String? = ""
    private var dob: String? = ""



    //Actionbar
   // private var actionBar: ActionBar? = null;
    var dbHelper = MyDbHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        //init actionbar
        //actionBar = supportActionBar
        //title of actionbar
       // actionBar!!.title = "Add Record"
        //backbutton in actionbar
        //actionBar!!.setDisplayHomeAsUpEnabled(true)
      //  actionBar!!.setDisplayShowHomeEnabled(true)

        //init permission arrays
        cameraPermissions = arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        storagePermissions = arrayOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        //click ImageView To pick Image
        profileTv.setOnClickListener {
            //show img pick dialog
            imagePickDialog()

        }
        //Click save Button to save record
                btnSave.setOnClickListener {
                    inputData()

                }

        }

    private fun inputData() {
        //get Data
        name = ""+nameEt.text.toString().trim()
        phone = ""+phoneEt.text.toString().trim()
        email = ""+emailEt.text.toString().trim()
        dob = ""+dobEt.text.toString().trim()

        //save data to db
        val timestamp = System.currentTimeMillis()
        val id = dbHelper.insertRecord(""+name,
                ""+imageUri,
                ""+phone,
                ""+email,
            ""+timestamp,
            ""+timestamp
        )
        Log.d(TAG, "inputData: $email ")
        Log.d(TAG, "inputData: $phone ")
        //Log.d(TAG, "inputData: $dob ")
        Toast.makeText(this, "Record added against id , $id", Toast.LENGTH_SHORT).show()

    }

    private fun imagePickDialog() {
        //options to display in dialog
        val options = arrayOf("Camera", "Gallery")
        //dialog
        val builder = AlertDialog.Builder(this)
        //title
        builder.setTitle("PickImage from")
        //Set Item Options
        builder.setItems(options){ dialog, which ->
            //handle Item Click
            if (which == 0){
                //camera clicked
                if (!checkCameraPermissions()){
                    //permission not granted
                    requestCameraPermissions()
                }
                else{
                    //permission already granted
                    pickFromCamera()

                }
            }
            else{
                //gallery Clicked
                if (!checkStoragePermissions()){
                    //permission not granted
                    requestStoragePermissions()
                }
                else{
                    //permission already granted
                    pickFromGallery()
                }

            }

        }
        //show dialog
        builder.show()
    }

    private fun requestStoragePermissions() {
        //request the storage permission
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE)

    }

    private fun pickFromGallery() {
        //pick image from gallery using Intent
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/+" //only image to be picked
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE)


    }

    private fun checkStoragePermissions(): Boolean {
        //check if storage permission is enabled or not
        return ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED


    }

    private fun requestCameraPermissions() {
        //request camera permissions
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE)

    }

    private fun pickFromCamera() {
        //pick image from camera
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image Title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Descriprion")
        //put Image uri
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //intent to open camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE)
    }

    private fun checkCameraPermissions(): Boolean {
        //check if camera or storage permissions are granted or not
        val results = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val results1 = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        return results && results1

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()//go back to prrev activity
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    //if allowed returns true otherwise false
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera()
                    } else {
                        Toast.makeText(this, "Camera and Storage Permissions are required", Toast.LENGTH_SHORT).show()

                    }

                }
            }
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    //if allowed returns true otherwise false
                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storageAccepted) {
                        pickFromGallery()
                    } else {
                        Toast.makeText(this, "Storage Permissions is required", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //image picked from camera or gallery will be recieved here
        if (resultCode == RESULT_OK){
            //image is picked
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                //picked from gallery
                //crop image
                CropImage.activity(data!!.data)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this)
            }else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                //picked from camera
                //crop image
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this)
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                //cropped image received
                val result = CropImage.getActivityResult(data)
                if (resultCode == RESULT_OK){
                    val resultUri = result.uri
                    imageUri = resultUri
                    //set image
                    profileTv.setImageURI(resultUri)
                }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    //error
                    val error = result.error
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



}
