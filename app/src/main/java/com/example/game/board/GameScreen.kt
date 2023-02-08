package com.example.game.board

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.reflect.KProperty


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen (
    board: Board
) {
    /*
    val board: Board by remember {
        mutableStateOf( Board() )
    }
    */
    var list by remember {
        mutableStateOf( board.getListOfSquares() )
    }

    var score by remember {
        mutableStateOf(board.score)
    }
 
    Surface(
        modifier = remember {
            Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->

                        val (x, y) = dragAmount
                        when {
                            x / 50 > 1 -> {
                                board.moveRight(); list = board.getListOfSquares(); score =
                                    board.score
                            }
                            y / 50 > 1 -> {
                                board.moveDown(); list = board.getListOfSquares(); score =
                                    board.score
                            }
                            x / 50 < -1 -> {
                                board.moveLeft(); list = board.getListOfSquares(); score =
                                    board.score
                            }
                            y / 50 < -1 -> {
                                board.moveUp(); list = board.getListOfSquares(); score = board.score
                            }

                        }

                        change.consume()
                    }

                }
        }
            

    ) {

        Column(
            modifier = Modifier.fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Text(
                text = "SCORE: $score",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                Modifier.border(BorderStroke(2.dp, Color.Black))
            ) {
                Square (list[0]?.value.toString())
                Square (list[1]?.value.toString())
                Square (list[2]?.value.toString())
                Square (list[3]?.value.toString())

            }

            Row(
                Modifier.border(BorderStroke(1.dp, Color.Black))
            ) {
                Square (list[4]?.value.toString())
                Square (list[5]?.value.toString())
                Square (list[6]?.value.toString())
                Square (list[7]?.value.toString())

            }

            Row(
                Modifier.border(BorderStroke(1.dp, Color.Black))
            ) {
                Square (list[8]?.value.toString())
                Square (list[9]?.value.toString())
                Square (list[10]?.value.toString())
                Square (list[11]?.value.toString())

            }

            Row(
                Modifier.border(BorderStroke(2.dp, Color.Black))
            ) {
                Square (list[12]?.value.toString())
                Square (list[13]?.value.toString())
                Square (list[14]?.value.toString())
                Square (list[15]?.value.toString())
            }

            Spacer(modifier = Modifier.height(70.dp))
            
            Button(onClick = {
                board.newGame()
                list = board.getListOfSquares()
            }) {
                Text(
                    text = "New Game",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                   
                )
            }
        }
    }
}


@Composable
fun Square (
    value: String,
) {
    Surface(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(10),

        color =  when (value) {
                  "0" -> Color.LightGray
                  "2" -> Color(0xFFF9DCC4)
                  "4" -> Color(0xFFFEC89A)
                  "8" -> Color(0xFFFCD5CE)
                  "16" -> Color(0xFFFFB5A7)
                  "32" -> Color(0xFFF9DCC4)
                  "64" -> Color(0xFFF9DCC4)
                  "128" -> Color(0xFFF9DCC4)
                  "256" -> Color(0xFFF9DCC4)
                  else -> Color.Green
            }
                                                          
    ) {
        Text(
            text = value,
            textAlign = TextAlign.Center,
            modifier = Modifier.size(80.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
    }

}
