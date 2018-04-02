package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public final class Coin extends Slot
{
	public Coin(ComparableIntPair position)
	{
		super(position);
	}

	@Override
	public char draw()
	{
		return 'o';
	}
}
