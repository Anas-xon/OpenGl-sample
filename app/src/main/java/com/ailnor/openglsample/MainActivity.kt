package com.ailnor.openglsample

import android.app.ActivityManager
import android.content.Context
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var surfaceView: GLSurfaceView
    private var rendererSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        surfaceView = GLSurfaceView(this)

        if ((getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.reqGlEsVersion < 0x00030001)
            Log.e("Log", "This device doesn't support OpenGL 3.1")

        surfaceView.setEGLContextClientVersion(2)
        surfaceView.setRenderer(AirHockeyRenderer(this))
        rendererSet = true
        setContentView(surfaceView)


    }

    override fun onResume() {
        super.onResume()
        if (rendererSet)
            surfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (rendererSet)
            surfaceView.onPause()
    }


}