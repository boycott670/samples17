package com.sqli.test.romanrunner.entities.utils;

public final class ComparableIntPair extends ComparablePair<Integer, Integer>
{
	private ComparableIntPair(Integer first, Integer second)
	{
		super(first, second);
	}
	
	public static ComparableIntPair of (int y, int x)
	{
		return new ComparableIntPair(y, x);
	}
}
