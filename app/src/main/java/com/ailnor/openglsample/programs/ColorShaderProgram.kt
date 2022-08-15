/* 
 * Copyright Erkinjanov Anaskhan, 13/08/22.
 */

package com.ailnor.openglsample.programs

import android.content.Context
import android.opengl.GLES20.*
import com.ailnor.openglsample.R


class ColorShaderProgram(context: Context): ShaderProgram(
    context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader
){

    // Uniform locations
    private val uMatrixLocation = glGetUniformLocation(program, U_MATRIX)
    private val uColorLocation = glGetUniformLocation(program, U_COLOR)

    // Attribute locations
    val aPositionLocation = glGetAttribLocation(program, A_POSITION)


    fun setUniforms(matrix: FloatArray?, r: Float, g: Float, b: Float) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0)
        glUniform4f(uColorLocation, r, g, b, 1f)
    }

}