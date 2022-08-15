package com.ailnor.openglsample

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    private lateinit var surfaceView: GLSurfaceView
    private var rendererSet = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        surfaceView = GLSurfaceView(this)

        if ((getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.reqGlEsVersion < 0x00030001)
            Log.e("Log", "This device doesn't support OpenGL 3.1")

        val renderer = AirHockeyRenderer(this)
        surfaceView.setEGLContextClientVersion(2)
        surfaceView.setRenderer(renderer)
        rendererSet = true

        surfaceView.setOnTouchListener { v, event ->
            if (event != null){
                val normalizedX = (event.x/ v.width) * 2 - 1
                val normalizedY = -((event.y/v.height) * 2 - 1)

                if (event.action == MotionEvent.ACTION_DOWN){
                    surfaceView.queueEvent{
                        renderer.handleTouchPress(normalizedX, normalizedY)
                    }
                } else if (event.action == MotionEvent.ACTION_MOVE){
                    surfaceView.queueEvent {
                        renderer.handleTouchDrag(normalizedX, normalizedY)
                    }
                }
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }

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