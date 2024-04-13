package com.snakeladder.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	private int diceCount;
	
	private static final int min =1;
	private static final int max=6;
	
	Dice(int count){
		this.diceCount = count;
	}
	
	public int getDiceCount() {
		return diceCount;
	}

	public void setDiceCount(int diceCount) {
		this.diceCount = diceCount;
	}
	
	public int rollDice() {
		int totalSum=0;
		int diceUsed=0;
		
		while(diceUsed<this.diceCount) {
			totalSum += ThreadLocalRandom.current().nextInt(min,max+1); 
			diceUsed++;
		}
		
		return  totalSum;
	}
	
}
