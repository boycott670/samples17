package com.sqli.test.romanrunner.entities;

import java.util.Comparator;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public abstract class Slot implements Comparable<Slot>
{
	private final ComparableIntPair position;
	
	public Slot(ComparableIntPair position)
	{
		this.position = position;
	}

	public ComparableIntPair getPosition()
	{
		return position;
	}

	public abstract char draw ();

	@Override
	public int compareTo(Slot other)
	{
		return Comparator.comparing(Slot::getPosition).compare(this, other);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if ((obj instanceof Slot))
			return false;
		
		Slot other = (Slot) obj;
		
		return compareTo(other) == 0;
	}
}
