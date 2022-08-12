/* 
 * Copyright Erkinjanov Anaskhan, 08/08/22.
 */

package com.ailnor.openglsample

import android.opengl.GLES20.*


object ShaderHelper {

    fun compileVertexShader(shaderCode: String): Int {
        return compileShader(GL_VERTEX_SHADER, shaderCode)
    }

    fun compileFragmentShader(shaderCode: String): Int {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode)
    }

    private fun compileShader(type: Int, shaderCode: String): Int {
        val shaderObjectId = glCreateShader(type)
        if (shaderObjectId == 0) {
            Logger.e("Could not create new shader.")
            return 0
        }

        glGetError()

        glShaderSource(shaderObjectId, shaderCode)
        glCompileShader(shaderObjectId)

        val compileStatus = IntArray(1)
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0)

        if (Logger.ON)
            Logger.e("Results of compiling source: \n $shaderCode: ${glGetShaderInfoLog(shaderObjectId)}")

        if (compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId)

            Logger.e("Compilation of shader failed.")

            return 0
        }

        return shaderObjectId
    }

    fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int{
        val programObjectId = glCreateProgram()

        if (programObjectId == 0){
            Logger.e("Could not create new program")
            return 0
        }

        glAttachShader(programObjectId, vertexShaderId)
        glAttachShader(programObjectId, fragmentShaderId)

        glLinkProgram(programObjectId)

        val linkStatus = IntArray(1)
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)

        if (Logger.ON)
            Logger.e("Results of linking program: \n" + glGetProgramInfoLog(programObjectId))

        if (linkStatus[0] == 0){
            glDeleteProgram(programObjectId)

            Logger.e("Linking of program failed.")

            return 0
        }

        return programObjectId
    }

    fun validateProgram(programObjectId: Int): Boolean{
        glValidateProgram(programObjectId)
        val validateStatus = IntArray(1)

        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0)
        Logger.e("Result of validating program: " + validateStatus[0] + "\n Log: " + glGetProgramInfoLog(programObjectId))

        return validateStatus[0] != 0
    }

}