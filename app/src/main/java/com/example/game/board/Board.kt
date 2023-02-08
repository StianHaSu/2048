package com.example.game.board

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class Board () {
    var matrix = Array(4) {Array<GameSquare?>(4){null} }
    private var numVals = 2;
    var score = 0;
    private var gameDone = false

    init {
        newBoard()
    }

    private fun newBoard() {
        for (i in 0 .. 3){
            for (j in 0..3){
                matrix[i][j] = GameSquare(i, j, 0, this);
            }
        }

        matrix[0][0]?.value = 2
        matrix[1][0]?.value = 2
    }

    fun printMatrix(){
        matrix.forEach { it.forEach { println(it) } }
    }

    fun getValue(col: Int, row: Int): GameSquare{
        return matrix[row][col]!!
    }

    fun moveDown() {
        for (i in 2 downTo  0){
            val toMove = matrix[i]
            toMove.forEach {
                it?.moveDown()
            }
        }
        newValue()
    }

    fun moveUp(){
        for (i in 0 .. 3){
            val toMove = matrix[i]
            toMove.forEach {
                it?.moveUp()
            }
        }
        newValue()
    }

    fun moveRight() {
        for (i in 3 downTo 0){
            matrix.forEach { it[i]?.moveRight() }
        }
        newValue()
    }

    fun moveLeft() {
        for (i in 1 .. 3){
            matrix.forEach { it[i]?.moveLeft() }
        }
        newValue()
    }

    fun newValue(){
        if (numVals < 16){
            var placed = false
            while (!placed) {
                val square = Random.nextInt(0, 15)
                val elem = getListOfSquares()[square]
                if (elem?.value == 0){
                    elem.value = 2
                    numVals++
                    placed = true
                    score += 2
                }
            }
        } else {
            gameDone = checkGameDone()
        }
    }


    fun tryMove (from: GameSquare, x: Int, y: Int): Boolean{
        return if (matrix[x][y]?.value == 0){
            matrix[x][y]?.value = from.value
            from.value = 0
            true
        } else false
    }

    fun tryMerge(toMerge: GameSquare, x: Int, y: Int): Boolean{
        val otherSquare = matrix[x][y]

        if (toMerge.value >0 && toMerge.value == otherSquare?.value){
            otherSquare.value += toMerge.value
            toMerge.value = 0
            numVals--
            return true
        }

        return false
    }

    fun getListOfSquares(): List<GameSquare?>{
        val list = mutableStateListOf<GameSquare?>();
        matrix.forEach { it.forEach { elem -> list.add(elem) } }
        return list
    }

    fun newGame() {
        if (gameDone){
            newBoard()
            numVals = 2
            score = 0
            gameDone = false
        }
    }

    fun checkGameDone(): Boolean{
        if (numVals < 16) return false

        for (i in 1 .. 2){
            for (j in 1 .. 2){
                when (matrix[i][j]?.value){
                    matrix[i-1][j]?.value -> return false
                    matrix[i+1][j]?.value -> return false
                    matrix[i][j-1]?.value -> return false
                    matrix[i][j+1]?.value -> return false
                }
            }
        }

        return true;
    }

}