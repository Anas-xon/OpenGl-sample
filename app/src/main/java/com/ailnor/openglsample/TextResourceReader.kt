/*
 * Copyright Erkinjanov Anaskhan, 08/08/22.
 */
package com.ailnor.openglsample

import android.content.Context
import java.lang.StringBuilder
import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.lang.RuntimeException
import android.content.res.Resources.NotFoundException

object TextResourceReader {
    fun readTextFileFromResource(context: Context, resourceId: Int): String {
        val body = StringBuilder()
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var nextLine: String?
            while (bufferedReader.readLine().also { nextLine = it } != null) {
                body.append(nextLine)
                body.append('\n')
            }
            body.deleteCharAt(body.length - 1)
        } catch (e: IOException) {
            throw RuntimeException("Could not open resource: $resourceId", e)
        } catch (nfe: NotFoundException) {
            throw RuntimeException("Resource not found: $resourceId", nfe)
        }
        return body.toString()
    }
}