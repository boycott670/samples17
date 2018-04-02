package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.ObstacleHitedException;
import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public abstract class Player extends Slot
{
	private final String name;
	
	private boolean left = true;
	private Circenses circenses;
	int score = 0;
	
	Player (ComparableIntPair position, String name, int score)
	{
		super(position);
		this.name = name;
		this.score = score;
	}
	
	Player (ComparableIntPair position, String name)
	{
		this(position, name, 0);
	}
	
	Player(String name)
	{
		this (ComparableIntPair.of(0, 0), name);
	}

	public String getName()
	{
		return name;
	}

	@Override
	public char draw()
	{
		return Character.toUpperCase(name.charAt(0));
	}
	
	abstract Player clone (ComparableIntPair nextPosition);
	abstract Player whenArrived ();
	
	public void startGame (final Circenses circenses)
	{
		this.circenses = circenses;
		this.circenses.setPlayer(this);
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
	
	public void forward () throws ObstacleHitedException
	{
		if (ComparableIntPair.of(0, 0).equals(getPosition()))
		{
			circenses.setSlot(ComparableIntPair.of(0, 0), new PreviousPlayerStartPosition());
		}
		
		final ComparableIntPair playerNextPosition = getNextPosition();

		if (circenses.isFinalSlot(playerNextPosition))
		{
			circenses.setSlot(playerNextPosition, whenArrived());
		}
		else
		{
			circenses.setSlot(playerNextPosition, clone(playerNextPosition));
		}
	}
	
	public int score ()
	{
		return score;
	}
}
