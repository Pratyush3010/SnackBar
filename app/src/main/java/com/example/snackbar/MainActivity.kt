package com.example.snackbar
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Color.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout


const val PERMISSION_CODE = 101
class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val simplesnackbar : Button = findViewById(R.id.btn_simple_snack)
        val customsnackbar : Button = findViewById(R.id.btn_Custom_snack)
        val topsnackbar : Button = findViewById(R.id.btn_Top_snack)
         val check :Button= findViewById(R.id.btn_Checkbox)

       checkPermission(
            android.Manifest.permission.READ_CONTACTS, PERMISSION_CODE
            )

        check.setOnClickListener {
          val intent = Intent(this@MainActivity,CheckBoxActivity::class.java)
            startActivity(intent)
        }

        topsnackbar.setOnClickListener {

            val snack = Snackbar.make(
                findViewById(android.R.id.content),
                "Had a snack at Snackbar",
                Snackbar.LENGTH_LONG
            )


            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.show()

        }

        simplesnackbar.setOnClickListener {

            val snack : Snackbar = Snackbar.make(it,"Hello I am Snack from Snack Bar",Snackbar.LENGTH_SHORT)
         snack.setAction("DISMISS", View.OnClickListener {
                System.out.println("Snackbar set Action - Onclick")
            })

            snack.setActionTextColor(parseColor("#BB4444"))
            snack.view.setBackgroundColor(parseColor("#FFF86F"))

//           val tv : TextView= View.findViewById(com.google.android.material.R.id.snackbar_text);
//            tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.someColor))
            val textview = snack.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textview.setTextColor(parseColor("#4444DD"))
            snack.show()
        }

        customsnackbar.setOnClickListener{

            val snack = Snackbar.make(it,"",Snackbar.LENGTH_LONG)

            val customSnackbar :View = layoutInflater.inflate(R.layout.custom_snack,null)

            // Top of frame

            snack.view.setBackgroundColor(Color.TRANSPARENT)
         val params = snack.view.layoutParams as FrameLayout.LayoutParams
            params.gravity= Gravity.TOP
            snack.view.layoutParams =params

            // Cutom Layout use in snackbar
            val SnackbarLayout = snack.view as SnackbarLayout
            SnackbarLayout.setPadding(0,0,0,0)

            SnackbarLayout.addView(customSnackbar,0)

            snack.show()
        }



    }
//   Deny Permission


    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                Toast.makeText(this@MainActivity, "Contacts Permission Granted", Toast.LENGTH_SHORT).show()
            } else {

                checkUserRequestedDontAskAgain()

                Toast.makeText(this@MainActivity, "Contacts Permission Denied", Toast.LENGTH_SHORT).show()


            }
        }
    }



    private fun checkUserRequestedDontAskAgain() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val rationalFalgREAD =
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)
            val rationalFalgWRITE =
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_CONTACTS)
            if (!rationalFalgREAD && !rationalFalgWRITE) {

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }
}