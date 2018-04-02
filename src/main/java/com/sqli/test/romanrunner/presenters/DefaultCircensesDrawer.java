package com.sqli.test.romanrunner.presenters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sqli.test.romanrunner.entities.Circenses;
import com.sqli.test.romanrunner.entities.Slot;

public final class DefaultCircensesDrawer implements CircensesDrawer
{
	@Override
	public String draw(Circenses circenses)
	{
		final List<Slot> slots = new ArrayList<>(circenses.getSlots().values());
		
		slots.sort(Comparator.<Slot, Integer>comparing(slot -> slot.getPosition().getFirst()).reversed().thenComparing(slot -> slot.getPosition().getSecond()));
		
		final StringBuilder builder = new StringBuilder();
		
		for (int slotIndex = 0 ; slotIndex < slots.size() ; slotIndex += 2)
		{
			builder.append(String.format("|%c%c|%s", slots.get(slotIndex).draw(), slots.get(slotIndex + 1).draw(), slotIndex + 1 == slots.size() - 1 ? "" : "\n"));
		}
		
		return builder.toString();
	}
}
