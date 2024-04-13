package com.snakeladder.model;

public class Board {
	
	 Cell[][] board = null;
	 
	 Board(int size){
		 this.board = new Cell[size][size];
		 for(int i=0;i<size;i++) {
			 for(int j=0;j<size;j++) {
				 board[i][j] = new Cell();
			 }
		 }
	 }
	 
}
