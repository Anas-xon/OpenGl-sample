/* 
 * Copyright Erkinjanov Anaskhan, 13/08/22.
 */

package com.ailnor.openglsample.objects

import com.ailnor.openglsample.Geometry
import com.ailnor.openglsample.VertexArray
import com.ailnor.openglsample.programs.ColorShaderProgram


class Puck(
    radius: Float,
    val height: Float,
    numPointAroundPuck: Int
){

    private companion object {
        const val POSITION_COMPONENT_COUNT = 3
    }

    private val vertexArray: VertexArray
    private val drawList: List<ObjectBuilder.DrawCommand>

    init {
        val generatedData = ObjectBuilder.createPuck(
            Geometry.Cylinder(Geometry.Point(0f, 0f, 0f), radius, height),
            numPointAroundPuck
        )

        vertexArray = VertexArray(generatedData.vertexData)
        drawList = generatedData.drawList
    }

    fun bindData(colorProgram: ColorShaderProgram) {
        vertexArray.setVertexAttribPointer(
            0,
            colorProgram.aPositionLocation,
            POSITION_COMPONENT_COUNT, 0
        )
    }

    fun draw() {
        for (drawCommand in drawList) {
            drawCommand.draw()
        }
    }

}