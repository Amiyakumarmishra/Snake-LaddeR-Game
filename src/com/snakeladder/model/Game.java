package com.snakeladder.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

	Board board;
	Deque<Player> players = new LinkedList<>();
	Dice dice;
	
	Player winner = null;

	public Game(int size,int count) {
		board = new Board(size);
		dice = new Dice(count);
		initializeGame();
	}

	public void initializeGame() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of players");
		int numberOfPlayers = sc.nextInt();
		int k=1;
		while(k<=numberOfPlayers){
			System.out.println("Enter " + k + " no. player name");
			String name = sc.nextLine();
			Player player = new Player(k, name);
			players.add(player);
			k++;
		}
		System.out.println("Enter the number of Snakes");
		int numberOfSnakes = sc.nextInt();

		int i = 1;
		while (i <= numberOfSnakes) {
			System.out.println("enter head of snake");
			int head = sc.nextInt();
			System.out.println("enter tail of snake");
			int tail = sc.nextInt();
//			System.out.println("enter the position of snake");
//			int position = sc.nextInt();
			if (tail >= head) {
				System.out.println("Invalid input for snake head & tail ,Tail should be less than Head");
				continue;
			} else {
				Jump jump = new Jump(head, tail);
				int row = head / board.board.length;
				int col = head % board.board.length;
				Cell cell = new Cell(jump);
				board.board[row][col] = cell;
				i++;
			}
		}

		System.out.println("Enter the number of Ladder");
		int numberOfLadder = sc.nextInt();

		i = 1;
		while (i <= numberOfLadder) {
			System.out.println("enter head of ladder");
			int head = sc.nextInt();
			System.out.println("enter tail of ladder");
			int tail = sc.nextInt();
//			System.out.println("enter the position of ladder");
//			int position = sc.nextInt();
			if (tail <= head) {
				System.out.println("Invalid input for snake head & tail ,Tail should be greater than Head");
				continue;
			} else {
				Jump jump = new Jump(head, tail);
				int row = head / board.board.length;
				int col = head % board.board.length;
				Cell cell = new Cell(jump);
				board.board[row][col] = cell;
				i++;
			}
		}

	}
	
	public void startGame() {
		
		while(this.winner==null) {
			
			Player player = this.players.removeFirst();
			
			System.out.println("it is "+player.getName()+"'s turn"+" & his current position is "+player.getCurrentPosition());
			System.out.println(" rolling dice");
			int diceNumber = dice.rollDice();
			
			int newPosition = player.getCurrentPosition()+diceNumber;
			newPosition = jumpCheck(newPosition);
			player.setCurrentPosition(newPosition);
			

			
			if(newPosition >= this.board.board.length*this.board.board.length -1 ) {
				this.winner = player;
				break;
			}
			
			if(diceNumber==6) {
				System.out.println("You have got a 6 ,Again your turn");
				this.players.addFirst(player);
			}else {
				this.players.addLast(player);
			}
		}
		
		System.out.println("Winner is : "+winner.getName());
	}

	private int jumpCheck(int newPosition) {
			
		int row = newPosition/this.board.board.length;
		int col= newPosition%this.board.board.length;
		
		if(this.board.board[row][col].getJump()!=null) {
			int head = this.board.board[row][col].getJump().getStart();
			int tail = this.board.board[row][col].getJump().getEnd();
			
			if(tail>head) {
				System.out.println("You are at position "+newPosition +" & you got a ladder");
				return tail;
			}else {
				System.out.println("You are at position "+newPosition +" & you got a snake");
				return tail;
			}
		}
		System.out.println("You are at position "+newPosition +" & here no snake or ladder is present");
		return newPosition;
	}

}
