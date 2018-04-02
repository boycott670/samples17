package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public final class FinishLine extends Slot
{
	public FinishLine(ComparableIntPair position)
	{
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char draw()
	{
		return '#';
	}
}
