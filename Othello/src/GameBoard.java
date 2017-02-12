//Project for CS2336.501 by Mathews Fazza

public class GameBoard implements Board{
	
//I found this configuration to work better than what I had before (Enum)
//The idea to add oppType, and type came from a similar project on StackOverflow.
	
private int size = 0;
private int row = 0;
private int column = 0;
public int type = 0;
private int oppType = 0;
int[][] b;

public GameBoard(int size)
{
this.size = size;
setUpBoard();//sets starting pieces.
}



//This method finds the right spots for the initial pieces and fills the 
//other spaces with asterisks.
private void setUpBoard()			
{										
	int x = 88;
	int o = 79;
	b = new int[size+1][size+1];
	
	int h1 = size/2;					//centers the pieces
	int	h2 = (size/(2))+1;				
	
	
	for (int i = 0; i<b.length; i++){
		for (int j = 0; j<b.length; j++){
			b [i][j] = 42;
		}//This loop just sets all the blank spaces to asterisks
	}
	b [0][0] = 48;
	b [h1][h1] = x;
	b [h1][h2] = o;
	b [h2][h1] = o;
	b [h2][h2] = x;

	for (int i=49, j=1; j<b.length; i++, j++){
		b [0][j] = i;
		b [j][0] = i;
	}
}

//what's actually going on here is using ascii values to display the board
//this method is evoked each turn.
public void displayBoard()
{
	for (int i = 0; i<b.length; i++){
		for (int j = 0; j<b.length; j++){
			System.out.print((char)b[i][j]+" ");
		}
		System.out.println();
	}
}

//checks if move is legal or not
public boolean checkLegalMove(int row,int column, int type)
{
	//assigns conditions/variables for specific player.
	this.row = row;
	this.column = column;
	setType(type);//set the TYPE. important to do when switching between p1 and p2.
		//checks to make sure there is a valid move within every direction and that the space isn't already occupied.
		if((checkRowR()||checkRowL()||checkColumnU()||checkColumnD()||checkNW()||checkNE()||checkSW()||checkSE())&&b[row][column]!=type&&b[row][column]!=oppType)
		{	
			return true;	
		}
		else
			return false;
}
public void setMove()//after conditions are met, use set move within each player to place the value on the board.
{
		b[this.row][this.column] = this.type;
		flip();
}



public boolean checkRowR()
{
	if(column==b.length-1)//fails if the point is on the far right column
	{
		return false;
	}
	if(b[row][column+1]!=oppType)//fails if the point next in the position we are considering is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = column+1; i<b.length && b[row][i] == oppType; i++ )
	{
			if(i==b.length-1)
			{
				return false;
			}
			count++;
	}

	if(b[row][column+count]==type)//if the stopping point is of the same point, you're good to go!
		return true;
	else
		return false;
}
public boolean checkRowL()
{
	if(column==1)//fails if the point is on the far left column
	{
		return false;
	}
	if(b[row][column-1]!=oppType)//fails if the point next in the position we are considering is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = column-1; i>0 && b[row][i] == oppType; i-- ){
			if(i==1)
			{
				return false;
			}
			count++;
		}

	if(b[row][column-count]==type)
		return true;
	else
		return false;
}
public boolean checkColumnU()
{
	if(row==1)//fails if the point is on the first row
	{
		return false;
	}
	if(b[row-1][column]!=oppType)//fails if the next point in the position we are considering is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = row-1; i>0 && b[i][column] == oppType; i-- ){
			if(i==1)
			{
				return false;
			}
			count++;
		}

	if(b[row-count][column]==type)
		return true;
	else
		return false;
}
public boolean checkColumnD()//fails if the point is on the bottom row
{

	if(row==b.length-1)
	{
		return false;
	}
	if(b[row+1][column]!=oppType)//fails if the next point in the position we are considering is not of the opp type.
	{
		return false;
	}
	
	int count = 1;
	for (int i = row+1; i<b.length && b[i][column] == oppType; i++ ){
		if(i==(b.length-1))	
		{
			return false;
		}
		count++;
		}

	if(b[row+count][column]==type)
		return true;
	else
		return false;
}
public boolean checkNW()//fails if the point is on the top row or far left column
{
	if(row==1 || column==1)
	{
		return false;
	}
	if((b[row-1][column-1]!=oppType))//fails if the next point in the position we are considering is is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = row-1, j = column-1; i>0 && j>0 && b[i][j] == oppType; i--, j-- ){
		if((i == 1)||(j == 1))
		{
			return false;
		}
			count++;
		}

	if(b[row-count][column-count]==type)
		return true;
	else
		return false;
}
public boolean checkNE()
{
	if(row==1 || column==b.length-1)//fails if the point is on the top row or far right column
	{
		return false;
	}
	if((b[row-1][column+1]!=oppType))//fails if the next point in the position we are considering is is not of the opp type.
	{
		return false;
	}
	
	int count = 1;
	for (int i = row-1, j = column+1; i>0 && j<b.length && b[i][j] == oppType; i--, j++ ){
			if((i == 1)||(j == b.length-1))
			{
				return false;
			}
			count++;
		}

	if(b[row-count][column+count]==type)
		return true;
	else
		return false;
}
public boolean checkSW()
{
	if(row==b.length-1 || column==1)//fails if the point is on the bottom row or far left column
	{
		return false;
	}
	if((b[row+1][column-1]!=oppType))//fails if the next point in the position we are considering is is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = row+1, j = column-1; i<b.length && j>0 && b[i][j] == oppType; i++, j-- ){
		if((i == b.length-1)||(j == 1))
		{
			return false;
		}
			count++;
		}
	
	if(b[row+count][column-count]==type)
		return true;
	else
		return false;
}
public boolean checkSE()
{
	if(row==b.length-1 || column==b.length-1)//fails if the point is on the bottom row or far right column
	{
		return false;
	}
	if((b[row+1][column+1]!=oppType))//fails if the next point in the position we are considering is is not of the opp type.
	{
		return false;
	}
	int count = 1;
	for (int i = row+1, j = column+1; i<b.length && j<b.length && b[i][j] == oppType; i++, j++ ){
		if((i == b.length-1)||(j == b.length-1))
		{
			return false;
		}
			count++;
		}
	
	if(b[row+count][column+count]==type)
		return true;
	else
		return false;
}



private void flip()
{
	if(checkRowR())
	{
		flipRowR();
	}
	if(checkRowL())
	{
		flipRowL();
	}
	if(checkColumnU())
	{
		flipColumnU();
	}
	if(checkColumnD())
	{
		flipColumnD();
	}
	if(checkNW())
	{
		flipNW();
	}
	if(checkNE())
	{
		flipNE();
	}
	if(checkSW())
	{
		flipSW();
	}
	if(checkSE())
	{
		flipSE();
	}
}


private void flipRowR()
{
	for (int i = column+1; b[row][i] !=type; i++ ){
		b[row][i]=type;
	}
}
private void flipRowL()
{
	for (int i = column-1; b[row][i] !=type; i-- ){
		b[row][i]=type;
	}
}
private void flipColumnU()
{
	for (int i = row-1; b[i][column] != type; i-- ){
		b[i][column]=type;
	}
}
private void flipColumnD()
{
	for (int i = row+1; b[i][column] != type; i++ ){
		b[i][column]=type;
	}
}

private void flipNE()
{
	for (int i = row-1, j = column+1; b[i][j] != type; i--, j++ ){
		b[i][j]=type;
	}
}
private void flipNW()
{
	for (int i = row-1, j = column-1; b[i][j] != type; i--, j-- ){
		b[i][j]=type;
	}
}
private void flipSE()
{
	for (int i = row+1, j = column+1;  b[i][j] != type; i++, j++ ){
		b[i][j]=type;
	}
}
private void flipSW()
{
	for (int i = row+1, j = column-1; b[i][j] != type; i++, j-- ){
		b[i][j]=type;
	}
}


public int getSize()
{
	return (b.length);
}

public boolean availMoves(int type)//checks to see if there are available moves for the given type.
{
	int counter = 0;
	setType(type);//sets the specific type.
	for(int i = 1; i<getSize(); i++)
	{
		for(int j=1; j<getSize(); j++)
		{
			if(checkLegalMove(i, j, this.type))
			{
				counter++;
			}
		}
	}
	if(counter>0)//if there exist valid moves then the method will return true.
		return true;
	else
		return false;
}
public boolean isFull()//checks to see if the board is full
{
	int counter = 0;
	for(int i = 1; i<getSize(); i++)
	{
		for(int j = 1; j<getSize(); j++)
		{
			if(b[i][j]==Player.x || b[i][j]==Player.o)
				counter++;
		}
	}
	if(counter==(Math.pow(getSize()-1,2)))
	{
		return true;
	}
	else
		return false;
}



public void setCoordinate(int row, int column){
	this.row=row;
	this.column=column;
}

int xCount = 0, oCount=0;
public int getScore()
{
	for(int i = 1; i<b.length; i++)
	{
		for(int j=1; j<b.length; j++)
		{
			if(b[i][j]==Player.x)//finds the score for p1
			{
				xCount++;
			}	
			if(b[i][j]==Player.o)//finds score for p2
			{
				oCount++;
			}
		}
	}
	return (xCount-oCount);//returns the difference between scores.
}//if positive, then p1 wins. if 0, then tie. if negative, then p2 wins.

public void printScore()
{//outputs results at endgame.
	int holdScore = getScore();
	if(holdScore>0)
	{
		System.out.println("Player X wins.");
	}
	else if(holdScore==0)
	{
		System.out.println("You tied.");
	}
	else
	{
		System.out.println("Player O wins");
	}
	System.out.println("Score: ");
	System.out.println("X Player: " + xCount);
	System.out.println("O Player: " + oCount);
}
public void setType(int type)//simple set type and oppType method.
{
	this.type = type;
	if(this.type==Player.x)
	{
		this.oppType=Player.o;
	}
	else
	{
		this.oppType=Player.x;
	}
}



}
