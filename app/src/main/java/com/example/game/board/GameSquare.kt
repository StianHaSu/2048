package com.example.game.board

data class GameSquare (
    var xPos: Int,
    var yPos: Int,
    var value: Int,
    val board: Board
){
    fun moveDown() {
        if (xPos < 3) {
            if (!board.tryMove(this, xPos + 1, yPos)) {
                board.tryMerge(this, xPos + 1, yPos)
            } else {
                board.matrix[xPos+1][yPos]?.moveDown()
            }
        }
    }

    fun moveUp() {
        if (xPos > 0) {
            if (!board.tryMove(this, xPos - 1, yPos)) {
                board.tryMerge(this, xPos - 1, yPos)
            } else {
                board.matrix[xPos-1][yPos]?.moveUp()
            }
        }
    }

    fun moveRight() {
        if (yPos < 3) {
            if (!board.tryMove(this, xPos, yPos+1)) {
                board.tryMerge(this, xPos, yPos+1)
            } else {
                board.matrix[xPos][yPos+1]?.moveRight()
            }
        }
    }

    fun moveLeft() {
        if (yPos > 0) {
            if (!board.tryMove(this, xPos, yPos-1)) {
                board.tryMerge(this, xPos, yPos-1)
            } else {
                board.matrix[xPos][yPos-1]?.moveLeft()
            }
        }
    }

    fun setEmpty() {
        value = 0
    }

    fun getCoord (): String {
        return "xPos $xPos \n yPos $yPos"
    }

    fun test (){
        value = 10
    }

}