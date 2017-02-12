//Project for CS2336.501 by Mathews Fazza

public abstract class Player {
	
protected static final int x = 88;//used ascii values for X and O
protected static final int o = 79;
public static int size = 8; //eight is a random default size. The program can take values starting at 4 though

protected GameBoard b;


public Player(GameBoard b)
{
	this.b=b;  //assigning the object here makes it so each sub class is able to access the same board 
			   //when calling methods from GameBoard, b.
			   //Once the Gameboard object is created, each sub class of player passes up the same one.
}

public abstract void nextMove(); //Since this method is used differently between Person and AI, it needs to be abstract.

}
