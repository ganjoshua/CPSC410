package ca.ubc.jpacman.model;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.test.framework.model.GameTest;

import ca.ubc.jpacman.factory.UndoGameFactory;
import ca.ubc.jpacman.model.UndoableGame;

public class UndoableGameTest extends GameTest {

	@Override
	protected UndoableGame makePlay(String singleRow) throws FactoryException {
		MapParser p = new MapParser(makeFactory());
		UndoableGame theGame = (UndoableGame) p.parseMap(new String[] { singleRow });
		return (UndoableGame) theGame;
	}

	@Override
	public IGameFactory makeFactory() {
		return new UndoGameFactory();
	}

}
