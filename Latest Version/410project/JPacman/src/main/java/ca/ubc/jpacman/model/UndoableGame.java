package ca.ubc.jpacman.model;

import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;

public class UndoableGame extends Game implements UndoIGameInteractor {
	// Stack for saving moves for undo'ing
	public Stack<CurrentState> stack = new Stack<CurrentState>();

	public void undo() {
		System.out.println("undo() in UndoableGame.java");
		// checks if the stack is empty; thus empty state
		boolean emptyState = this.stack.empty();

		// if stack is not empty proceed with undo functionality
		if (!emptyState) {
			int noPlayerMove = 0;

			while (noPlayerMove == 0) {
				noPlayerMove = checkforPlayerMove(noPlayerMove);
				// update game board with previous state and let user see it
				notifyViewers();
			}
		}
	}

	private int checkforPlayerMove(int noPlayerMove) {
		CurrentState s = this.stack.pop();
		s.returnState(this);
		if (s.turn == 1 || this.stack.empty()) {
			noPlayerMove = 1;
		}
		return noPlayerMove;
	}

	// this moves the player to the direction that they were at
	public void movePlayer(Direction dir) {
		stackPlayer(dir);
		// Move the player back to original state
		super.movePlayer(dir);
	}

	private void stackPlayer(Direction dir) {
		CurrentState s = new CurrentState(this, dir);
		stack.push(s);
	}

	// this moves the ghost to the direction that they were at
	public void moveGhost(Ghost theGhost, Direction dir) {

		stackGhost(theGhost, dir);
		super.moveGhost(theGhost, dir);
	}

	private void stackGhost(Ghost theGhost, Direction dir) {
		CurrentState s = new CurrentState(this, theGhost, dir);
		stack.push(s);
	}
}