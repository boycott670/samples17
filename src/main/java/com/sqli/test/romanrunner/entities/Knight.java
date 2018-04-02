package com.sqli.test.romanrunner.entities;

public final class Knight extends Player
{
	public Knight(String name)
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
		score += 20;
	}

	@Override
	void whenHitObstacle()
	{
		score -= 10;
		byPassedObstacle = true;
	}

	@Override
	boolean isGameEnded()
	{
		return false;
	}

}
