package com.future.gamebooster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sin

private val Bg = Color(0xFF05070D)
private val Panel = Color(0xFF0B101B)
private val Cyan = Color(0xFF00E5FF)
private val Purple = Color(0xFF9C5CFF)
private val Green = Color(0xFF35FF9A)
private val Text = Color(0xFFEAF7FF)
private val Muted = Color(0xFF7D91A5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FutureBoosterApp() }
    }
}

@Composable
fun FutureBoosterApp() {
    var boosted by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Gaming") }

    val infinite = rememberInfiniteTransition(label = "hud")
    val rotation by infinite.animateFloat(
        0f, 360f,
        infiniteRepeatable(tween(8000, easing = LinearEasing)),
        label = "rotation"
    )
    val pulse by infinite.animateFloat(
        0.94f, 1.06f,
        infiniteRepeatable(tween(1100, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )

    MaterialTheme(colorScheme = darkColorScheme()) {
        Box(
            Modifier.fillMaxSize().background(Bg)
        ) {
            Canvas(Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                for (i in 0..18) {
                    val x = (w / 18f) * i
                    drawLine(Color(0xFF101A29), Offset(x, 0f), Offset(x, h), 1f)
                }
                for (i in 0..30) {
                    val y = (h / 30f) * i
                    drawLine(Color(0xFF0B1420), Offset(0f, y), Offset(w, y), 1f)
                }
            }

            Column(
                Modifier.fillMaxSize().padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("FUTURE", color = Cyan, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 4.sp)
                        Text("GAME BOOSTER", color = Text, fontSize = 23.sp, fontWeight = FontWeight.Black)
                    }
                    Box(
                        Modifier.size(48.dp).scale(pulse),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(Modifier.fillMaxSize().rotate(rotation)) {
                            drawCircle(Cyan.copy(alpha = .16f), style = Stroke(2.dp.toPx()))
                            drawArc(Cyan, 0f, 100f, false, style = Stroke(2.dp.toPx()))
                        }
                        Text("FX", color = Cyan, fontWeight = FontWeight.Bold)
                    }
                }

                HudCard {
                    Box(Modifier.fillMaxWidth().height(220.dp), contentAlignment = Alignment.Center) {
                        Canvas(Modifier.size(205.dp).rotate(rotation)) {
                            drawCircle(Cyan.copy(alpha = .08f), style = Stroke(2.dp.toPx()))
                            drawCircle(Purple.copy(alpha = .12f), radius = size.minDimension*.38f, style = Stroke(2.dp.toPx()))
                            drawArc(Cyan, 20f, 120f, false, style = Stroke(4.dp.toPx()))
                            drawArc(Purple, 210f, 80f, false, style = Stroke(4.dp.toPx()))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(if (boosted) "BOOST ACTIVE" else "SYSTEM READY",
                                color = if (boosted) Green else Cyan,
                                fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(if (boosted) "MAX" else "98",
                                color = Text, fontSize = 54.sp, fontWeight = FontWeight.Black)
                            Text(if (boosted) "PERFORMANCE MODE" else "PERFORMANCE %",
                                color = Muted, fontSize = 11.sp, letterSpacing = 2.sp)
                        }
                    }
                }

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard("FPS", if (boosted) "60+" else "59", Cyan, Modifier.weight(1f))
                    StatCard("RAM", "2.8 GB", Purple, Modifier.weight(1f))
                    StatCard("TEMP", if (boosted) "42°C" else "39°C", Green, Modifier.weight(1f))
                }

                Button(
                    onClick = { boosted = !boosted },
                    modifier = Modifier.fillMaxWidth().height(58.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (boosted) Color(0xFF123D2A) else Color(0xFF102A35)
                    )
                ) {
                    Text(
                        if (boosted) "⚡ BOOST ACTIVE — TAP TO STOP" else "⚡ ACTIVATE TURBO BOOST",
                        color = if (boosted) Green else Cyan,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                }

                Text("MODULES", color = Muted, fontSize = 11.sp, letterSpacing = 3.sp)

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ModuleCard("RAM\nOPTIMIZER", "READY", Cyan, Modifier.weight(1f))
                    ModuleCard("GPU\nMODE", "AUTO", Purple, Modifier.weight(1f))
                    ModuleCard("NETWORK\nTUNER", "READY", Green, Modifier.weight(1f))
                }

                HudCard {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("GAME LAUNCHER", color = Text, fontWeight = FontWeight.Bold)
                            Text("3 GAMES", color = Muted, fontSize = 11.sp)
                        }
                        listOf("Cyber Arena", "Neon Racer", "Battle Zone").forEachIndexed { i, name ->
                            Row(
                                Modifier.fillMaxWidth()
                                    .background(Color(0xFF101827), RoundedCornerShape(12.dp))
                                    .clickable { selected = name }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("◈", color = listOf(Cyan, Purple, Green)[i], fontSize = 18.sp)
                                Spacer(Modifier.width(12.dp))
                                Text(name, color = Text, fontWeight = FontWeight.SemiBold)
                                Spacer(Modifier.weight(1f))
                                Text(if (selected == name) "SELECTED" else "PLAY", color = if (selected == name) Green else Muted, fontSize = 10.sp)
                            }
                        }
                    }
                }

                Spacer(Modifier.weight(1f))
                Text(
                    "v1.0 • FUTURE PERFORMANCE ENGINE",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF405064),
                    fontSize = 9.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun HudCard(content: @Composable () -> Unit) {
    Box(
        Modifier.fillMaxWidth()
            .background(Panel, RoundedCornerShape(22.dp))
            .border(1.dp, Color(0xFF17283B), RoundedCornerShape(22.dp))
    ) { content() }
}

@Composable
fun StatCard(title: String, value: String, accent: Color, modifier: Modifier) {
    Box(
        modifier.height(80.dp)
            .background(Panel, RoundedCornerShape(16.dp))
            .border(1.dp, accent.copy(alpha = .25f), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(title, color = Muted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(value, color = accent, fontSize = 20.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun ModuleCard(title: String, status: String, accent: Color, modifier: Modifier) {
    Box(
        modifier.height(92.dp)
            .background(Panel, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFF17283B), RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(title, color = Text, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("● $status", color = accent, fontSize = 9.sp)
        }
    }
}
