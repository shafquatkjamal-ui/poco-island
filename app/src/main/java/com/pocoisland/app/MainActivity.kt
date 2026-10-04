package com.pocoisland.app

import android.app.Activity
import android.os.Bundle
import android.graphics.*
import android.view.*
import android.content.Context
import android.widget.Toast
import kotlin.math.abs

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.BLACK

        setContentView(PocoIslandView(this))
    }
}

class PocoIslandView(context: Context) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var downX = 0f
    private var downY = 0f

    private var page = 0
    private var islandExpanded = false
    private var musicPlaying = false

    private val apps = arrayOf(
        "☎" to "Phone",
        "✉" to "Messages",
        "◎" to "Camera",
        "♫" to "Music",
        "◉" to "Photos",
        "▶" to "YouTube",
        "⚙" to "Settings",
        "🌐" to "Browser"
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        drawBackground(canvas, w, h)
        drawStatusBar(canvas, w)
        drawDynamicIsland(canvas, w)
        drawHome(canvas, w, h)
        drawDock(canvas, w, h)
    }

    private fun drawBackground(
        canvas: Canvas,
        w: Float,
        h: Float
    ) {
        val gradient = LinearGradient(
            0f,
            0f,
            w,
            h,
            Color.rgb(30, 35, 65),
            Color.rgb(4, 5, 12),
            Shader.TileMode.CLAMP
        )

        paint.shader = gradient

        canvas.drawRect(
            0f,
            0f,
            w,
            h,
            paint
        )

        paint.shader = null
    }

    private fun drawStatusBar(
        canvas: Canvas,
        w: Float
    ) {
        paint.color = Color.WHITE
        paint.textSize = 15f
        paint.typeface = Typeface.DEFAULT_BOLD

        canvas.drawText(
            "9:41",
            24f,
            32f,
            paint
        )

        paint.textSize = 13f
        paint.typeface = Typeface.DEFAULT

        canvas.drawText(
            "▮▮▮  WiFi  ▰",
            w - 105f,
            32f,
            paint
        )
    }

    private fun drawDynamicIsland(
        canvas: Canvas,
        w: Float
    ) {
        val center = w / 2f

        val left: Float
        val right: Float
        val bottom: Float

        if (islandExpanded) {
            left = center - 145f
            right = center + 145f
            bottom = 115f
        } else {
            left = center - 72f
            right = center + 72f
            bottom = 48f
        }

        paint.color = Color.BLACK

        canvas.drawRoundRect(
            left,
            10f,
            right,
            bottom,
            28f,
            28f,
            paint
        )

        if (!islandExpanded) {

            paint.color = Color.rgb(35, 35, 42)

            canvas.drawCircle(
                center + 36f,
                29f,
                5f,
                paint
            )

        } else {

            paint.color = Color.WHITE
            paint.textAlign = Paint.Align.CENTER
            paint.textSize = 14f

            canvas.drawText(
                if (musicPlaying)
                    "♫  Now Playing"
                else
                    "Poco Island",
                center,
                48f,
                paint
            )

            paint.textSize = 12f
            paint.color = Color.LTGRAY

            canvas.drawText(
                if (musicPlaying)
                    "Music is playing"
                else
                    "Tap to close",
                center,
                75f,
                paint
            )

            paint.textAlign = Paint.Align.LEFT
        }
    }

    private fun drawHome(
        canvas: Canvas,
        w: Float,
        h: Float
    ) {
        paint.textAlign = Paint.Align.CENTER

        paint.color = Color.WHITE
        paint.textSize = 38f
        paint.typeface = Typeface.DEFAULT_BOLD

        canvas.drawText(
            "Poco Island",
            w / 2f,
            170f,
            paint
        )

        paint.textSize = 14f
        paint.typeface = Typeface.DEFAULT
        paint.color = Color.LTGRAY

        canvas.drawText(
            if (page == 0)
                "Smart • Fast • Simple"
            else
                "Your applications",
            w / 2f,
            197f,
            paint
        )

        val cellWidth = w / 4f
        val cellHeight = 105f
        val startY = 250f

        for (i in apps.indices) {

            val row = i / 4
            val col = i % 4

            val x =
                cellWidth * col +
                cellWidth / 2f

            val y =
                startY +
                row * cellHeight

            drawAppIcon(
                canvas,
                x,
                y,
                apps[i].first,
                apps[i].second
            )
        }

        paint.textSize = 12f
        paint.color = Color.GRAY

        canvas.drawText(
            "Swipe left / right",
            w / 2f,
            h - 105f,
            paint
        )

        paint.textAlign = Paint.Align.LEFT
    }

    private fun drawAppIcon(
        canvas: Canvas,
        x: Float,
        y: Float,
        symbol: String,
        name: String
    ) {
        paint.color = Color.argb(
            55,
            255,
            255,
            255
        )

        canvas.drawRoundRect(
            x - 32f,
            y - 32f,
            x + 32f,
            y + 32f,
            19f,
            19f,
            paint
        )

        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 27f

        canvas.drawText(
            symbol,
            x,
            y + 9f,
            paint
        )

        paint.textSize = 12f
        paint.color = Color.LTGRAY

        canvas.drawText(
            name,
            x,
            y + 52f,
            paint
        )
    }

    private fun drawDock(
        canvas: Canvas,
        w: Float,
        h: Float
    ) {
        val left = 18f
        val right = w - 18f
        val top = h - 78f
        val bottom = h - 15f

        paint.color = Color.argb(
            60,
            255,
            255,
            255
        )

        canvas.drawRoundRect(
            left,
            top,
            right,
            bottom,
            30f,
            30f,
            paint
        )

        val dock = arrayOf(
            "☎",
            "✉",
            "♫",
            "⚙"
        )

        for (i in dock.indices) {

            val x =
                left +
                (i + 0.5f) *
                ((right - left) / 4f)

            paint.color = Color.WHITE
            paint.textAlign = Paint.Align.CENTER
            paint.textSize = 25f

            canvas.drawText(
                dock[i],
                x,
                top + 40f,
                paint
            )
        }

        paint.textAlign = Paint.Align.LEFT
    }

    override fun onTouchEvent(
        event: MotionEvent
    ): Boolean {

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                return true
            }

            MotionEvent.ACTION_UP -> {

                val dx = event.x - downX
                val dy = event.y - downY

                // Swipe left/right
                if (
                    abs(dx) > 100 &&
                    abs(dx) > abs(dy)
                ) {

                    page =
                        if (dx < 0) 1 else 0

                    invalidate()

                    return true
                }

                // Dynamic Island tap
                if (
                    downY < 130f &&
                    abs(dx) < 60f
                ) {

                    islandExpanded =
                        !islandExpanded

                    invalidate()

                    return true
                }

                // Dock
                if (downY > height - 120) {

                    val quarter =
                        width / 4f

                    when {

                        event.x < quarter -> {
                            Toast.makeText(
                                context,
                                "Phone",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        event.x < quarter * 2 -> {
                            Toast.makeText(
                                context,
                                "Messages",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        event.x < quarter * 3 -> {

                            musicPlaying =
                                !musicPlaying

                            islandExpanded = true

                            invalidate()
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "Settings",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    return true
                }

                return true
            }
        }

        return true
    }
}
