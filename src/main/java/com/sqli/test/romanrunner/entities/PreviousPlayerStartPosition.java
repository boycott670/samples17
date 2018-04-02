package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public final class PreviousPlayerStartPosition extends Slot
{
	public PreviousPlayerStartPosition()
	{
		super(ComparableIntPair.of(0, 0));
	}

	@Override
	public char draw()
	{
		return '@';
	}

}
