package ca.ubc.jpacman.model;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Sprite;
import org.jpacman.framework.model.Tile;



public class CurrentState 
{
	protected Tile playerTile;
	protected Direction dirPlayer;
	protected int earnP;
	protected int minusP;
	protected int pWorth;
	protected int collectedP;
	protected boolean food = false;
	
	protected int turn;
	protected Ghost ghost;
	protected Tile ghostTile;
	protected Direction ghostDir;

	public CurrentState(UndoableGame game, Direction dir) 
	{
		//getting the player's tile position
		this.playerTile = game.getPlayer().getTile();
		
		//getting the player's direction that they are facing (left, right, up, down)
		this.dirPlayer = game.getPlayer().getDirection();
		
		// Store the points earned
		this.earnP = game.getPointManager().getFoodEaten();
		
		this.turn = 1;
		
		// Check food present or not
		Tile checkFood = game.getBoard().tileAtDirection(game.getPlayer().getTile(), dir);
		Sprite checkSprite = checkFood.topSprite();
		
		if(checkSprite != null && checkSprite.getSpriteType() == SpriteType.FOOD){
			this.food = true;
		}
	}
	
	public CurrentState(UndoableGame game, Ghost g, Direction dir)
	{

		this.ghostTile = g.getTile();
		this.ghostDir = dir;
		this.ghost = g;
		
		this.turn = 0;
		this.earnP = game.getPointManager().getFoodEaten();
	}

	public void returnState(UndoableGame game) 
	{
		//get player data
		Player player = game.getPlayer();
		
//-------Undo Food------------------------------------------------------------------------------------------------
		
		if(this.food == true){
			Food tempFood = new Food();
			tempFood.occupy(player.getTile());
		}	
		
	
//--------Undo Death-----------------------------------------------------------------------------------------------------
		// if player dead, revive them
		//this solves the test where player dies from ghost and undo button is pressed
		//zombie (return of the dead!)
		if (!player.isAlive())
		{
			player.resurrect();
		}

//-------Undo Player Moves------------------------------------------------------------------------------------------------
		//this solves the test where the user wants to undo a move
		// Reset the player position
		//move player from the tile that they are currently on
		if( this.turn==1 )
		{
			System.out.print("Player undo.\n");
			player.deoccupy();
			// Reset the player direction's that they were moving to
			player.setDirection(this.dirPlayer);
			//and move it to the previous tile
			player.occupy(this.playerTile);
		}
		else
		{
			System.out.print("Ghost undo.\n");
			this.ghost.deoccupy();
			this.ghost.occupy(this.ghostTile);
		}

//-------Undo Points Recalculation-----------------------------------------------------------------------------------------
		//reduce points every time undo button is pressed
		
		//what each food is worth
		pWorth=game.getPointManager().getFoodEaten();
		//collected number of food=points
		collectedP=this.earnP;
		//minus collected points every time undo is pressed
		minusP=collectedP - pWorth;
		
		if(minusP != 0)
		{
			//replace points in the gui with the recalculated one
			game.getPointManager().consumePointsOnBoard(player, minusP);
		}
		

		
	}
}