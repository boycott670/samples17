package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public final class Charioteer extends Player
{
	private Charioteer (ComparableIntPair position, String name, int score)
	{
		super(position, name, score);
	}
	
	private Charioteer (ComparableIntPair position, String name)
	{
		super(position, name);
	}
	
	public Charioteer(String name)
	{
		super(name);
	}

	@Override
	Player clone(ComparableIntPair nextPosition)
	{
		return new Charioteer(nextPosition, getName());
	}

	@Override
	Player whenArrived()
	{
		return new Charioteer(getPosition(), getName(), score + 100);
	}
}
