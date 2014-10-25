package ca.ubc.jpacman.factory;

import org.jpacman.framework.factory.DefaultGameFactory;
import ca.ubc.jpacman.model.UndoableGame;

public class UndoGameFactory extends DefaultGameFactory {
	private transient UndoableGame theGame;

	@Override
	public UndoableGame makeGame() {
		theGame = new UndoableGame();
		return theGame;
	}

	@Override
	protected UndoableGame getGame() {
		assert theGame != null;
		return theGame;
	}

}
