package com.n2n.covid19.ui.main.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.n2n.covid19.R

class CustomPercentView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val confirmedPaint: Paint by lazy {
        Paint().apply {
            color = getContext().getColor(R.color.color_confirmed)
        }
    }

    val deathPaint: Paint by lazy {
        Paint().apply {
            color = getContext().getColor(R.color.color_death)
        }
    }

    val recoveredPaint: Paint by lazy {
        Paint().apply {
            color = getContext().getColor(R.color.color_recover)
        }
    }

    var percentDeath: Float = 0f
    var percentRecover: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val confirmedRect = setUpRectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas?.drawRect(confirmedRect, confirmedPaint)
        val deathRect = setUpRectF(0f, 0f, width * percentDeath, height.toFloat())
        canvas?.drawRect(deathRect, deathPaint)
        val recoverRect = setUpRectF(width - percentRecover * width, 0f, width.toFloat(), height.toFloat())
        canvas?.drawRect(recoverRect, recoveredPaint)
    }

    private fun setUpRectF(left: Float, top: Float, right: Float, bottom: Float): RectF {
        return RectF(left, top, right, bottom)
    }

}