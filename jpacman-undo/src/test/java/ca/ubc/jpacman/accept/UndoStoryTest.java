package ca.ubc.jpacman.accept;

import static org.junit.Assert.*;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.IBoardInspector;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.PacmanInteraction.MatchState;
import org.junit.Test;

import ca.ubc.jpacman.ui.UndoablePacman;
import static org.hamcrest.Matchers.*;

public class UndoStoryTest extends MovePlayerStoryTest {

	private UndoablePacman undoUI;

	@Override
	public UndoablePacman makeUI() {
		undoUI = new UndoablePacman();
		return undoUI;
	}

	@Override
	public UndoablePacman getUI() {
		return undoUI;
	}

	@Test
	public void test_S7_1_UndoPlayer() {
		// given
		getEngine().start();
		// when
		Tile startingTile = getPlayer().getTile();
		getEngine().left();
		getUI().undo();
		// then
		assertEquals(startingTile, getPlayer().getTile());
		assertEquals(MatchState.PAUSING, getEngine().getCurrentState());
		assertTrue(getPlayer().isAlive());
	}

	@Test
	public void test_S7_2_UndoFood() {

		// given
		getEngine().start();
		// when
		Tile startingTile = getPlayer().getTile();
		getEngine().left();
		Tile foodTile = getPlayer().getTile();
		assertThat(getPlayer().getPoints(), greaterThan(0));
		getUI().undo();
		// then
		assertEquals(startingTile, getPlayer().getTile());
		assertEquals(getPlayer().getPoints(), 0);
		assertEquals(IBoardInspector.SpriteType.FOOD, foodTile.topSprite().getSpriteType());

	}

	@Test
	public void test_S7_3_UndoGhost() {
		Tile emptyTile = tileAt(2, 2);
		Tile oriTile = tileAt(2, 1); // added
		// given
		getEngine().start();
		// when
		getUI().getGame().moveGhost(theGhost(), Direction.DOWN);
		// then
		assertEquals(emptyTile, theGhost().getTile());

		getUI().undo(); // added
		assertEquals(oriTile, theGhost().getTile()); // added
	}

	/**
	 * Test that a ghost can move over food.
	 */
	@Test
	public void test_S7_3_UndoGhostFood() {
		Tile oriTile = tileAt(2, 1); // added
		Tile foodTile = tileAt(2, 0);
		// given
		getEngine().start();
		// when
		getUI().getGame().moveGhost(theGhost(), Direction.UP);
		// then
		assertEquals(foodTile, theGhost().getTile());
		assertEquals(IBoardInspector.SpriteType.GHOST, foodTile.topSprite().getSpriteType());

		getUI().undo(); // added
		assertEquals(oriTile, theGhost().getTile()); // added
		assertEquals(IBoardInspector.SpriteType.GHOST, oriTile.topSprite().getSpriteType()); // added
		assertEquals(IBoardInspector.SpriteType.FOOD, foodTile.topSprite().getSpriteType()); // added
	}

	/**
	 * Test that the ghost causes the player's death if the ghost bumps into the player.
	 */
	@Test
	public void test_S7_3_UndoGhostKillsPlayer() {
		// given
		getEngine().start();
		// when
		getUI().getGame().moveGhost(theGhost(), Direction.LEFT);
		// then
		assertFalse(getPlayer().isAlive());

		getUI().undo(); // added

		assertFalse(!getPlayer().isAlive());
	}

	@Test
	public void test_S7_4_GameOver() {
		// given the game has started
		getEngine().start();
		// get the position of the player
		Tile startTile = getPlayer().getTile();
		getEngine().left();
		getPlayer().die();
		// and the player dies at that tile
		assertFalse(getPlayer().isAlive());

		// when undo button is pressed
		getUI().undo();

		// then
		assertTrue(getPlayer().isAlive());
		assertEquals(startTile, getPlayer().getTile());
	}

	@Test
	public void test_S7_5_UndoPoints() {

		// given
		getEngine().start();
		Tile startingTile = getPlayer().getTile();
		getEngine().left();
		assertThat(getPlayer().getPoints(), greaterThan(0));

		// when
		getUI().undo();

		// then
		assertEquals(startingTile, getPlayer().getTile());
		assertEquals(getPlayer().getPoints(), 0);
		assertEquals(MatchState.PAUSING, getEngine().getCurrentState());
		assertTrue(getPlayer().isAlive());

	}

	@Test
	public void test_S7_6_PauseUndo() {
		// given the game has started
		getEngine().start();

		// when undo button is pressed
		getUI().undo();

		// then pause game
		assertEquals(MatchState.PAUSING, getEngine().getCurrentState());
		assertTrue(getPlayer().isAlive());
	}
}