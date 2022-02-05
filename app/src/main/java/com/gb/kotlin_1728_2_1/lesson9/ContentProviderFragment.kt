package com.gb.kotlin_1728_2_1.lesson9

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.FragmentContentProviderBinding
import com.gb.kotlin_1728_2_1.databinding.FragmentMainBinding

class ContentProviderFragment : Fragment() {


    private var _binding: FragmentContentProviderBinding? = null
    private val binding: FragmentContentProviderBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    showDialog()
                }
                else -> {
                    myRequestPermission()
                }
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ it->
        if(it){
            getContacts()
        }else{

        }
    }
    val REQUEST_CODE = 999
    private fun myRequestPermission() {
        //requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)


        launcher.launch(Manifest.permission.READ_CONTACTS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {

            when {
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    showDialog()
                }
                else -> {
                    Log.d("", "КОНЕЦ")
                }
            }
        }
    }

    private fun getContacts() {
        context?.let { it ->
            val contentResolver = it.contentResolver
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )
            cursor?.let { cursor->
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    addView(name)
                }
            }
            cursor?.close()
        }
    }

    private fun addView(name:String) {
        binding.containerForContacts.addView(TextView(requireContext()).apply {
            text = name
            textSize = 30f
        })
    }
    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Доступ к контактам")
            .setMessage("Объяснение")
            .setPositiveButton("Предоставить доступ") { _, _ ->
                myRequestPermission()
            }
            .setNegativeButton("Не надо") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    }

    companion object {
        @JvmStatic
        fun newInstance() = ContentProviderFragment()
    }
}