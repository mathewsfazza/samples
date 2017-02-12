//Project for CS2336.501 by Mathews Fazza

import java.util.ArrayList;
import java.util.Random;
public class AI extends Robot{
	
int type = 0;

public AI(GameBoard b, int storeKind)
{
	super(b);//passes the board object up to Robot.
	this.type = storeKind;
}

public void nextMove(){
	delay();
	
	ArrayList<Integer> r = new ArrayList<Integer>();//row
	ArrayList<Integer> c = new ArrayList<Integer>();//col
	Random rand = new Random();
	for(int i = 1; i<b.getSize(); i++)
	{
		for(int j=1; j<b.getSize(); j++)
		{
			if(b.checkLegalMove(i, j, this.type))
			{
				r.add(i);
				c.add(j);
			}
		}
	}
	
	int hold = rand.nextInt(r.size());//from 0 to size-1. why? the nextInt method goes from 0(inclusive) to n(exclusive)
//thus, picks a row/column index randomly from our array. (note: they are the same size!)
	System.out.println("row: " + r.get(hold));//prints pick
	System.out.println("column: " + c.get(hold));
	if(b.checkLegalMove(r.get(hold), c.get(hold), type))
	{
		b.setMove();//will place move and also flip the appropriate types
	}
}
}
