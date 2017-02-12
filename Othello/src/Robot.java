//Project for CS2336.501 by Mathews Fazza

public abstract class Robot extends Player {

public Robot(GameBoard b)
{
	super(b);	//passes the object to a player
}

public abstract void nextMove();
public void delay()//delay is a method needed to add time between moves, I got it from someone on stackoverflow
{ 				   //It really makes the game experience better, because you have time to think about the next move
	try
	{
		Thread.sleep(400);
	}
	catch(InterruptedException e)
	{
	System.out.println(e);
	}
}

}