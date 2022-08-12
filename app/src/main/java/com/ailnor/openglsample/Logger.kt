/* 
 * Copyright Erkinjanov Anaskhan, 08/08/22.
 */

package com.ailnor.openglsample

import android.util.Log

object Logger {

    const val ON = true

    fun e(message: String, tag: String = "OPEN_GL") {
        if (ON)
            Log.e(tag, message)
    }

}