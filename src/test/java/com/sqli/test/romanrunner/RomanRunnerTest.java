package com.sqli.test.romanrunner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sqli.test.romanrunner.entities.Charioteer;
import com.sqli.test.romanrunner.entities.Circenses;
import com.sqli.test.romanrunner.entities.Player;

public class RomanRunnerTest
{

	@Test
	public void circensesBuilderTest()
	{
		Circenses circenses = new CircensesBuilder().addCoin().addEmptySlot().addCoin().addObstacle().build();

		String expectedDisplay = new StringBuilder().append("|##|\n") // the final line
				.append("|_ |\n") // the obstacle
				.append("|o |\n") // the second coin
				.append("|  |\n") // empty slot
				.append("|o |\n") // the first coin
				.append("|  |") // the start line is empty as no player is registered
				.toString();

		assertEquals(expectedDisplay, circenses.draw());
	}

	@Test
	public void aPlayerCanStartTheGame()
	{
		Circenses circenses = new CircensesBuilder().addCoin().addEmptySlot().addCoin().addObstacle().build();

		Player player = new Charioteer("lucius");
		player.startGame(circenses);

		String expectedDisplay = new StringBuilder().append("|##|\n") // the final line
				.append("|_ |\n") // the obstacle
				.append("|o |\n") // the second coin
				.append("|  |\n") // empty slot
				.append("|o |\n") // the first coin
				.append("|L |") // the Charioteer "Lucius" at the start line
				.toString();

		assertEquals(expectedDisplay, circenses.draw());
	}

}
