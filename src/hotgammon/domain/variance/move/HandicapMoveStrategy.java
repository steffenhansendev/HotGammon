package hotgammon.domain.variance.move;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.Game;
import hotgammon.domain.common.Location;
import hotgammon.domain.common.MoveStrategy;

public class HandicapMoveStrategy implements MoveStrategy {

    private MoveStrategy currentState;
    private MoveStrategy handicappedMoveStrategy;
    private MoveStrategy normalMoveStrategy;
    private Color colorOfHandicappedPlayer;

    public HandicapMoveStrategy(MoveStrategy handicappedMoveStrategy, MoveStrategy normalMoveStrategy, Color colorOfHandicappedPlayer) {
        this.handicappedMoveStrategy = handicappedMoveStrategy;
        this.normalMoveStrategy = normalMoveStrategy;
        this.colorOfHandicappedPlayer = colorOfHandicappedPlayer;
    }

    public boolean resolveHit(Location from, Location to, Game game) {
        setState(game);
        return currentState.resolveHit(from, to, game);
    }

    public int getIndexOfValidForValidMove(Location from, Location to, Game game) {
        setState(game);
        return currentState.getIndexOfValidForValidMove(from, to, game);
    }

    private boolean isHandicappedPlayerInTurn(Game game) {
        return game.getPlayerInTurn() == colorOfHandicappedPlayer;
    }

    private void setState(Game game) {
        if (isHandicappedPlayerInTurn(game)) {
            currentState = handicappedMoveStrategy;
        } else {
            currentState = normalMoveStrategy;
        }
    }
}
