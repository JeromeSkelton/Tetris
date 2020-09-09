// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN221 assignment.
// You may not distribute it in any other way without permission.
package swen221.tetris.moves;

import swen221.tetris.logic.Board;
import swen221.tetris.logic.Rectangle;
import swen221.tetris.tetromino.ActiveTetromino;
import swen221.tetris.tetromino.Tetromino;

/**
 * Implements a "hard drop". That is, when the tetromino is immediately dropped
  * all the way down as far as it can go.
 *
 * @author David J. Pearce
 * @author Marco Servetto
 *
 */
public class DropMove implements Move {
	@Override
	public boolean isValid(Board board) {
		Rectangle box = board.getActiveTetromino().getBoundingBox();

		// Checks bounding box to ensure tetromino is within the board
		if(box.getMinX() >= 0 && box.getMaxX() < board.getWidth()
		&& box.getMinY() >= 0 && box.getMaxY() < board.getHeight()) {
			return board.canPlaceTetromino(board.getActiveTetromino());
		}
		return false;
	}

	@Override
	public Board apply(Board board) {
		ActiveTetromino curr = board.getActiveTetromino();

		while (board.canPlaceTetromino(curr)) {
			curr = curr.translate(0,-1);
		}
		curr = curr.translate(0, 1);


		board.setActiveTetromino(curr);

		return board;

	}

	@Override
	public String toString() {
		return "drop";
	}
}
