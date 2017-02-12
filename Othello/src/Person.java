import java.util.Scanner;

//Project for CS2336.501 by Mathews Fazza

public class Person extends Player{
	int type = 0;
	int row, col;
	Scanner scan = new Scanner(System.in);
	public Person(GameBoard b, int storeKind)
	{
		super(b);//passes the board object up to player.
		this.type = storeKind;
	}
	
	public void nextMove()
	{
		boolean loop;
		do{
			loop = false;
			System.out.print("row: ");
			this.row = scan.nextInt();//prompts user for row and column
			System.out.print("column: ");
			this.col = scan.nextInt();
			while(this.row <= 0 || this.col <= 0)
			{//outputs if the user entered an out of bounds coordinate.
				System.out.println("Oops, it appears you entered a zero or nonnegative number. Please try again.");
				System.out.print("row: ");
				this.row = scan.nextInt();
				System.out.print("column: ");
				this.col = scan.nextInt();
			}
			if(b.checkLegalMove(row, col, type))//makes sure the move is valid
			{
				b.setMove();//will place move and also flip the appropriate types
			}
			else//if not valid, then the user is once again prompted for a row/column response.
			{
				System.out.println("Not a valid move. Please try again");
				loop = true;
			}
		}while(loop);
		
	}
}
