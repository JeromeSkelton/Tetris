// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import org.w3c.dom.css.Rect;
import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a translation move.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public abstract class AbstractTranslation extends AbstractMove implements Move {
	/**
	 * Amount to translate x-coordinate.
	 */
	private final int dx;
	/**
	 * Amount to translate y-coordinate.
	 */
	private final int dy;

	/**
	 * Construct new TranslationMove for a given amount of horizontal and vertical
	 * translation.
	 *
	 * @param dx
	 *            Amount to translate in horizontal direction.
	 * @param dy
	 *            Amount to translate in vertical direction.
	 */
	public AbstractTranslation(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public Board apply(Board board) {
		// Create copy of the board to prevent modifying its previous state.
		board = new Board(board);
		// Apply translation for this move
		ActiveTetromino tetromino = board.getActiveTetromino();
		board = attemptTranslation(board, tetromino, dx, dy);
		// Return updated version of board
		return board;
	}

	/***
	 * 	Creates a new board to apply the translation too, if the board is still valid after the translation
	 * 	return the new board, otherwise return the old board before the translation
	 *
	 * @param board
	 * @param t	the current Tetromino
	 * @param dx	Change in x
	 * @param dy	Change in y
	 * @return
	 */

	public Board attemptTranslation(Board board, ActiveTetromino t, int dx, int dy) {
		Board translated = new Board(board);
		t = t.translate(dx,dy);
		translated.setActiveTetromino(t);

		if(isValid(translated)) {
			return translated;
		}
		return board;
	}

}
