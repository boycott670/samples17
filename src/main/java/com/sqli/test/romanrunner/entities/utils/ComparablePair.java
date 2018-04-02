package com.sqli.test.romanrunner.entities.utils;

import java.util.Comparator;

public abstract class ComparablePair<F extends Comparable<? super F>, S extends Comparable<? super S>> implements Comparable<ComparablePair<? extends F, ? extends S>>
{
	private final F first;
	private final S second;
	
	ComparablePair(F first, S second)
	{
		this.first = first;
		this.second = second;
	}

	public F getFirst()
	{
		return first;
	}

	public S getSecond()
	{
		return second;
	}	
	
	@Override
	public int compareTo(ComparablePair<? extends F, ? extends S> other)
	{
		return Comparator.comparing(ComparablePair<? extends F, ? extends S>::getFirst).thenComparing(ComparablePair<? extends F, ? extends S>::getSecond).compare(this, other);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!(obj instanceof ComparablePair))
			return false;
		
		@SuppressWarnings("rawtypes")
		ComparablePair other = (ComparablePair) obj;
		
		return compareTo(other) == 0;
	}
}
