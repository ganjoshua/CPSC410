package ca.ubc.jpacman.ui;

import org.jpacman.framework.ui.IPacmanInteraction;

public interface UndoIPacmanInteraction extends IPacmanInteraction {
	/**
	 * Undo the game
	 */
	void undo();

}
