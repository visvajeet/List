package com.work.list.utils

import android.content.Context
import android.widget.Toast

fun Context?.showToast(msg:String){
    this?.let {
        Toast.makeText(it,msg,Toast.LENGTH_SHORT).show()
    }
}