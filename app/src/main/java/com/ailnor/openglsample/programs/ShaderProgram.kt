/* 
 * Copyright Erkinjanov Anaskhan, 13/08/22.
 */

package com.ailnor.openglsample.programs

import android.content.Context
import android.opengl.GLES20.glUseProgram
import com.ailnor.openglsample.ShaderHelper.buildProgram
import com.ailnor.openglsample.TextResourceReader.readTextFileFromResource


open class ShaderProgram(
    context: Context, vertexShaderResourceId: Int,
    fragmentShaderResourceId: Int
) {

    protected companion object{
        // Uniform constants
        const val U_COLOR = "u_Color"
        const val U_MATRIX = "u_Matrix"
        const val U_TEXTURE_UNIT = "u_TextureUnit"

        // Attribute constants
        const val A_POSITION = "a_Position"
        const val A_COLOR = "a_Color"
        const val A_TEXTURE_COORDINATES = "a_TextureCoordinates"
    }

    // Shader program
    protected var program = 0

    init {
        program = buildProgram(
            readTextFileFromResource(
                context, vertexShaderResourceId
            ),
            readTextFileFromResource(
                context, fragmentShaderResourceId
            )
        )
    }

    fun useProgram() {
        // Set the current OpenGL shader program to this program.
        glUseProgram(program)
    }

}