package ca.ubc.jpacman.ui;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction;

import ca.ubc.jpacman.factory.UndoGameFactory;
import ca.ubc.jpacman.model.UndoableGame;

public class UndoablePacman extends MainUI {

	private static final long serialVersionUID = -1886454523844391664L;

	private transient PacmanInteraction pi;

	public void main() throws FactoryException {

		initialize();
		start();
	}

	// New constructor that uses UndoGameFactory()
	@Override
	public MainUI initialize() throws FactoryException {
		// super();
		pi = new UndoPacmanInteraction();
		withFactory(new UndoGameFactory());
		UndoButtonPanel undoButton = new UndoButtonPanel();
		withButtonPanel(undoButton.withParent(this));
		withModelInteractor(eventHandler());
		return super.initialize();

	}

	// undo method calls the undoPacmaninteraction's undo and pauses the game
	public void undo() {
		System.out.println("undo() in UndoablePacman.java");
		// getGame().undo();
		eventHandler().stop();
		eventHandler().undo();
	}

	public UndoPacmanInteraction eventHandler() {

		return (UndoPacmanInteraction) pi;
	}

	// override to return
	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();

	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

}
