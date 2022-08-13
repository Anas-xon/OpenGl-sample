/* 
 * Copyright Erkinjanov Anaskhan, 13/08/22.
 */

package com.ailnor.openglsample.objects

import android.opengl.GLES20.*
import com.ailnor.openglsample.Geometry
import com.ailnor.openglsample.Utils
import com.ailnor.openglsample.VertexArray
import com.ailnor.openglsample.objects.ObjectBuilder.DrawCommand
import com.ailnor.openglsample.objects.ObjectBuilder.GeneratedData
import com.ailnor.openglsample.programs.ColorShaderProgram


class Mallet(
    radius: Float,
    val height: Float,
    numPointsAroundMallet: Int
){

    private companion object {
        const val POSITION_COMPONENT_COUNT = 3
    }

    private val vertexArray: VertexArray
    private val drawList: List<DrawCommand>

    init {
        val generatedData: GeneratedData = ObjectBuilder.createMallet(
            Geometry.Point(
                0f,
                0f,
                0f
            ),
            radius,
            height,
            numPointsAroundMallet
        )

        vertexArray = VertexArray(generatedData.vertexData)
        drawList = generatedData.drawList
    }

    fun bindData(colorProgram: ColorShaderProgram) {
        vertexArray.setVertexAttribPointer(
            0,
            colorProgram.aPositionLocation,
            POSITION_COMPONENT_COUNT,
            0
        )
    }

    fun draw() {
        for (drawCommand in drawList) {
            drawCommand.draw()
        }
    }

}