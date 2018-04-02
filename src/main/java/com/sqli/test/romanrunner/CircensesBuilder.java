package com.sqli.test.romanrunner;

import java.util.HashMap;
import java.util.Map;

import com.sqli.test.romanrunner.entities.Circenses;
import com.sqli.test.romanrunner.entities.Coin;
import com.sqli.test.romanrunner.entities.EmptySlot;
import com.sqli.test.romanrunner.entities.FinishLine;
import com.sqli.test.romanrunner.entities.Obstacle;
import com.sqli.test.romanrunner.entities.Slot;
import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;
import com.sqli.test.romanrunner.presenters.CircensesDrawer;
import com.sqli.test.romanrunner.presenters.DefaultCircensesDrawer;

public final class CircensesBuilder
{
	private final CircensesDrawer drawer = new DefaultCircensesDrawer();
	
	private boolean left = true;
	private ComparableIntPair lastPosition = ComparableIntPair.of(0, 0);
	
	private final Map<ComparableIntPair, Slot> slots = new HashMap<>();
	
	public CircensesBuilder left ()
	{
		left = true;
		return this;
	}
	
	public CircensesBuilder right ()
	{
		left = false;
		return this;
	}
	
	private ComparableIntPair getNextPosition ()
	{
		if (left)
		{
			return ComparableIntPair.of(lastPosition.getFirst() + (lastPosition.getSecond() == 0 ? 1 : 0), 0);
		}
		else
		{
			return ComparableIntPair.of(lastPosition.getFirst() + (lastPosition.getSecond() == 1 ? 1 : 0), 1);
		}
	}
	
	public CircensesBuilder addCoin ()
	{
		lastPosition = getNextPosition();
		
		slots.put(lastPosition, new Coin(lastPosition));
		
		return this;
	}
	
	public CircensesBuilder addEmptySlot ()
	{
		lastPosition = getNextPosition();
		slots.put(lastPosition, new EmptySlot(lastPosition));
		return this;
	}
	
	public CircensesBuilder addObstacle ()
	{
		lastPosition = getNextPosition();
		
		slots.put(lastPosition, new Obstacle(lastPosition));
		
		return this;
	}
	
	private void sealMap ()
	{
		for (int counter = 1 ; counter <= 2 ; counter ++)
		{
			lastPosition = getNextPosition();
			
			slots.put(lastPosition, new FinishLine(lastPosition));
			
			left = !left;
		}
	}
	
	private void fillBlanksWithEmptySlots ()
	{
		for (int y = lastPosition.getFirst() - 1 ; y >= 0 ; y --)
		{
			for (int x = 0 ; x < 2 ; x++)
			{
				final ComparableIntPair currentPosition = ComparableIntPair.of(y, x);
				
				slots.putIfAbsent(currentPosition, new EmptySlot(currentPosition));
			}
		}
	}
	
	public Circenses build ()
	{
		sealMap();
		fillBlanksWithEmptySlots();
		
		return new Circenses(slots, drawer);
	}
}
