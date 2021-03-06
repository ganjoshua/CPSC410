package ca.ubc.jpacman.accept;

import static org.junit.Assert.*;
import org.jpacman.framework.model.Tile;
import org.jpacman.test.framework.accept.AbstractAcceptanceTest;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MovePlayerStoryTest extends AbstractAcceptanceTest {
	@Test
	public void test_S2_1_PlayerMoves() {
		Tile emptyTile = tileAt(1, 0);
		// given
		getEngine().start();
		assertEquals(1, getPlayer().getTile().getY());
		// when
		getEngine().up();
		// then
		assertEquals(emptyTile, getPlayer().getTile());
	}

	@Test
	public void test_S2_2_PlayerEats() {
		// given
		getEngine().start();
		// when
		getEngine().left();
		// then
		assertThat(getPlayer().getPoints(), greaterThan(0));
	}

	@Test
	public void test_S2_3_PlayerDies() {
		// given
		getEngine().start();
		// when
		getEngine().right();
		// then
		assertFalse(getPlayer().isAlive());
	}

	@Test
	public void test_S2_4_PlayerWall() {
		Tile nextToWall = tileAt(1, 0);
		// given
		getEngine().start();
		getEngine().up(); // move next to Wall
		assertEquals(nextToWall, getPlayer().getTile());
		// when
		getEngine().left();
		// then still next to wall.
		assertEquals(nextToWall, getPlayer().getTile());
	}

	@Test
	public void test_S2_5_PlayerWins() {
		// given
		getEngine().start();
		getEngine().left(); // eat first food
		getEngine().right(); // go back
		getEngine().up(); // move next to final food
		// when
		getEngine().right(); // eat final food
		// then
		assertTrue(getUI().getGame().getPointManager().allEaten());
	}
}