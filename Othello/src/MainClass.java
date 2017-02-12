//Project for CS2336.501 by Mathews Fazza

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int play1, play2, bsize;
		
		
		
		int size = Player.size;//default size of 8
		String temp = null, temp2 = null;
		
		
		
		System.out.println("Welcome to Mathews Fazza's Othello game for CS.2336.");
		
		//get the variables necessary to create the objects and start the game
		do{
		System.out.println("You have two options for player one: \n1. Human Player \n2. Computer Player");
			play1 = input.nextInt();
		}while(play1 > 2 || play1 < 1);
		
		do{
			System.out.println("You have two options for player two: \n1. Human Player \n2. Computer Player");
				play2 = input.nextInt();
			}while(play2 > 2 || play2 < 1);
		
		do{
			System.out.println("Now enter a size for the board. It needs to be even and greater than 2.");
				bsize = input.nextInt();
			}while(!(bsize>2 && bsize%2==0));  //
		
		
		int storeKind1 = Player.x; //x
		int storeKind2 = Player.o; //o
		GameBoard b = new GameBoard(bsize);
		
		Player p1 = null;
		Player p2 = null;
		
		
		
		//defines who player one is going to be
		if(play1==1)
		{
			p1 = new Person(b, storeKind1);
		}else if(play1==2)
		{
			p1 = new AI(b, storeKind1);
		}
		
		//defines who player 2 is going to be
		if(play2==1)
		{
			p2 = new Person(b, storeKind2);
		}else if(play2==2)
		{
			p2 = new AI(b, storeKind2);
		}
		
		//And with the players picked, the game begins.
		System.out.println("\n\nWelcome to the game!");
		System.out.println("Your objective is to fill up the board with more pieces \nthan the other player by placing" +
		" pieces in rows, colums, or diagonals \nthat contain at least one enemy piece.  If the move is not legal, \nI'll" +
		" let you know.  Let's play!");
		
		//the initial pieces are already placed by instantiating GameBoard (it is in RB's constructor).
		b.displayBoard();//thus for starting out all we need to do is print out the initially set board
		while(true)
		{
			int skippedCounter = 0;
			if(!b.isFull())//makes sure the board is not full in order to progress.
			{
				System.out.println("Enter your move, X Player:");
				if(p1.b.availMoves(storeKind1))//makes sure that moves actually exist for the player.
				{//if not, do not prompt the player for a move, skip to the next player and increment out counter.
					p1.nextMove();
					b.displayBoard();
				}
				else
				{
					System.out.println("Player X is unable make a move. Turn passed.");
					skippedCounter++;
				}
			}
			else//if the board is full it's game over. declare the winner. print out the scores. exit.
			{
				System.out.println("The board is complete. The game over.");
				b.printScore();
				System.exit(1);
			}
			
			if(!b.isFull())
			{
				System.out.println("Enter your move, O player:");
	
				if(p2.b.availMoves(storeKind2))
				{
					p2.nextMove();
					b.displayBoard();
				}
				else
				{
					System.out.println("Player O is unable make a move. Turn passed.");
					skippedCounter++;
				}
				if(skippedCounter==2)//if both players are skipped then it's clear that neither can make and
				{//move and then the game is over. print out the statistics and exit the game.
					System.out.println("No legal moves left. Game over");
					b.printScore();
					System.exit(1);
				}
			}
			else //if the board is full it's game over. declare the winner. print out the scores. exit.
			{
				System.out.println("The board is complete. Game over.");
				b.printScore();
				System.exit(1);
			}
		}

	}

}
