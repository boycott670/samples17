package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public abstract class Player extends Slot
{
	private final String name;
	
	private boolean left = true;
	
	public Player(String name)
	{
		super(ComparableIntPair.of(0, 0));
		this.name = name;
	}

	@Override
	public char draw()
	{
		return Character.toUpperCase(name.charAt(0));
	}
	
	public void startGame (final Circenses circenses)
	{
		circenses.setPlayer(this);
	}
	
	private ComparableIntPair getNextPosition ()
	{
		if (left)
		{
			return ComparableIntPair.of(getPosition().getFirst() + (getPosition().getSecond() == 0 ? 1 : 0), 0);
		}
		else
		{
			return ComparableIntPair.of(getPosition().getFirst() + (getPosition().getSecond() == 1 ? 1 : 0), 1);
		}
	}
	
	public void forward ()
	{
		if (ComparableIntPair.of(0, 0).equals(getPosition()))
		{
			
		}
	}
}
