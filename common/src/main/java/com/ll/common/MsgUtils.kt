package com.ll.common

import android.content.Context
import android.widget.Toast

object MsgUtils {

     fun show(context: Context,msg : String){
         Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
     }
}