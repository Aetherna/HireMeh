package dev.aetherna.hiremeh.common.util

import android.app.Activity
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


fun Activity.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Fragment.shortToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Activity.startActivity(){

}
