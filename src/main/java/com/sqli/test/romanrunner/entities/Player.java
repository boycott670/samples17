package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.ObstacleHitedException;
import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;

public abstract class Player extends Slot
{
	private final String name;
	
	private boolean left = true;
	private Circenses circenses;
	int score = 0;
	private boolean earnedCoin = false;
	boolean isDead = false;
	boolean byPassedObstacle = false;
	
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
	
	public Player left () throws ObstacleHitedException
	{
		left = true;
		return forward();
	}
	
	public Player right () throws ObstacleHitedException
	{
		left = false;
		return forward();
	}
	
	abstract void whenArrived ();
	abstract void whenEarnedCoin ();
	abstract void whenHitObstacle ();
	abstract boolean isGameEnded ();
	
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
	
	public Player forward () throws ObstacleHitedException
	{
		if (isGameEnded())
		{
			throw new ObstacleHitedException();
		}
		
		if (ComparableIntPair.of(0, 0).equals(getPosition()))
		{
			circenses.setSlot(ComparableIntPair.of(0, 0), new PreviousPlayerStartPosition());
		}
		else
		{
			if (earnedCoin)
			{
				earnedCoin = false;
				circenses.setSlot(getPosition(), new EarnedCoin(getPosition()));
			}
			else if (byPassedObstacle)
			{
				byPassedObstacle = false;
				circenses.setSlot(getPosition(), new Obstacle(getPosition()));
			}
			else
			{
				circenses.setSlot(getPosition(), new EmptySlot(getPosition()));
			}
		}
		
		final ComparableIntPair playerNextPosition = getNextPosition();
		
		setPosition(playerNextPosition);

		if (circenses.isFinalSlot(getPosition()))
		{
			whenArrived();
		}
		else if (circenses.isCoin(getPosition()))
		{
			earnedCoin = true;
			whenEarnedCoin();
		}
		else if (circenses.isObstacle(getPosition()))
		{
			whenHitObstacle();
		}
		
		circenses.setSlot(getPosition(), this);
		
		return this;
	}
	
	public int score ()
	{
		return score;
	}
}
