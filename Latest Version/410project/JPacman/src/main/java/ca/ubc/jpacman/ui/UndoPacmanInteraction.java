package ca.ubc.jpacman.ui;

import org.jpacman.framework.ui.PacmanInteraction;

import ca.ubc.jpacman.model.UndoIGameInteractor;

public class UndoPacmanInteraction extends PacmanInteraction implements UndoIPacmanInteraction {

	/**
	 * Undo the last move.
	 */
	@Override
	public void undo() {
		// updateState();
		System.out.println("undo() in UndoPacmanInteraction.java");
		stop();
		((UndoIGameInteractor) this.getGame()).undo();

	}
}
