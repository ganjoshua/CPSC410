package ca.ubc.jpacman.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jpacman.framework.ui.ButtonPanel;

public class UndoButtonPanel extends ButtonPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton undoButton;

	// override super's initialize() to include creating and initializing the undoButton
	@Override
	public void initialize() {
		super.initialize();
		undoButtonInit();
		addButton(undoButton);
		// //startButton = new JButton("Start");
		// //stopButton = new JButton("Stop");
		// initializeStartButton();
		// initializeStopButton();
		//
		// //JButton exitButton = createExitButton();
		//
		// setName("jpacman.buttonPanel");
		// addButton(startButton);
		// addButton(stopButton);
		// addButton(exitButton);
	}

	private void undoButtonInit() {
	    undoButton = new JButton("Undo");
	    initializeUndoButton();
    }

	// check if undoButton is pressed, if yes, call undo()
	private void initializeUndoButton() {
		undoButton.setEnabled(false);
		undoButtonEventListener();
		undoButton.requestFocusInWindow();
	}

	private void undoButtonEventListener() {
	    undoButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    undo();
		    }
	    });
	    undoButton.setName("jpacman.undo");
    }

	@Override
	public void start() {
		super.start();
		undoButton.setEnabled(true);

	}

	// Call undo() function in UndoablePacman.java
	protected void undo() {
		// assert invariant();
		System.out.println("calling UndoPacmanInteraction.undo()");
		((UndoIPacmanInteraction) getPacmanInteractor()).undo();
		pause();
		// enableStartStop();

		// this.undoPacman.undo();

		// assert invariant();

	}

	@Override
	public UndoPacmanInteraction getPacmanInteractor() {
		return (UndoPacmanInteraction) super.getPacmanInteractor();
	}

}
