package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public final class Charioteer extends Player
{
	private Charioteer (ComparableIntPair position, String name, int score)
	{
		super(position, name, score);
	}
	
	public Charioteer(String name)
	{
		super(name);
	}

	@Override
	void whenArrived()
	{
		score += 100;
	}

	@Override
	void whenEarnedCoin()
	{
		score += 10;
	}
}
