package com.example.mediaandcontactslogin.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import com.example.mediaandcontactslogin.R
import com.example.mediaandcontactslogin.databinding.UserContactsActivityBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "UserContactsActivity"
private const val REQUEST_CODE_READ_CONTACTS = 1

class UserContactsActivity : AppCompatActivity() {
    private lateinit var binding: UserContactsActivityBinding
    private lateinit var userContactNames: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserContactsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContactNames = findViewById(R.id.lv_contacts)

        val hasReadContactSPermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_CONTACTS)

        if (hasReadContactSPermission == PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS)
        } else
        {
            getUserContactInfo()
//            val main = Intent(this, ChoiceActivity::class.java)
//            startActivity(main)
        }

    }

    override fun onResume() {
        super.onResume()
        getUserContactInfo()
    }

    private fun getUserContactInfo(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
        == PackageManager.PERMISSION_GRANTED){
            val projection = arrayOf(ContactsContract.Contacts
                .DISPLAY_NAME_PRIMARY)
            val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            projection, null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

            val contacts = ArrayList<String>()
            cursor?.use {
                while (it.moveToNext()){
                    contacts.add(it.getString(it.getColumnIndexOrThrow(ContactsContract
                        .Contacts.DISPLAY_NAME_PRIMARY)))
                }
            }

            val adapter = ArrayAdapter<String>(this, R.layout.contact_list_item,
                R.id.tv_user_contact_name, contacts)
            userContactNames.adapter = adapter
        } else {
            Snackbar.make(binding.root, "Please Grant Access to Your Contacts", Snackbar.LENGTH_LONG)
                .setAction("Grant Access"){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS)) {
                        ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_CONTACTS),
                            REQUEST_CODE_READ_CONTACTS)
                    } else {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", this.packageName, null)
                        intent.data = uri
                        this.startActivity(intent)
                    }
                }.show()
        }
    }
}