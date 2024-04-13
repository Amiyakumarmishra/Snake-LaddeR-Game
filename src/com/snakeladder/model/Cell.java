package com.snakeladder.model;

public class Cell {
	
	private Jump jump=null;
	
	Cell(){
		
	}
	
	Cell(Jump jump){
		this.jump = jump;
	}

	public Jump getJump() {
		return jump;
	}

	public void setJump(Jump jump) {
		this.jump = jump;
	}
}
