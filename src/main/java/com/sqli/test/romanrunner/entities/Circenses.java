package com.sqli.test.romanrunner.entities;

import java.util.Map;

import com.sqli.test.romanrunner.entities.utils.ComparableIntPair;
import com.sqli.test.romanrunner.presenters.CircensesDrawer;

public final class Circenses
{
	private final Map<ComparableIntPair, Slot> slots;
	private final CircensesDrawer drawer;
	
	private Player player;
	
	public Circenses(Map<ComparableIntPair, Slot> slots, CircensesDrawer drawer)
	{
		this.slots = slots;
		this.drawer = drawer;
	}	

	public Map<ComparableIntPair, Slot> getSlots()
	{
		return slots;
	}

	public String draw ()
	{
		return drawer.draw(this);
	}
	
	public void setPlayer (final Player player)
	{
		this.player = player;
		
		slots.put(this.player.getPosition(), this.player);
	}
	
	public void setSlot (ComparableIntPair position, Slot slot)
	{
		slots.put(position, slot);
	}
	
	public boolean isFinalSlot (ComparableIntPair position)
	{
		return slots.get(position) instanceof FinishLine;
	}
	
	public boolean isCoin (ComparableIntPair position)
	{
		return slots.get(position) instanceof Coin;
	}
	
	public boolean isObstacle (ComparableIntPair position)
	{
		return slots.get(position) instanceof Obstacle;
	}
}
