package hotgammon.domain.variance;

import hotgammon.domain.common.Color;
import hotgammon.domain.common.DiceStrategy;
import hotgammon.domain.common.Game;

public class HandicapDiceStrategy implements DiceStrategy {

    private DiceStrategy currentState;
    private DiceStrategy handicappedDiceStrategy;
    private DiceStrategy normalDiceStrategy;
    private Color colorOfHandicappedPlayer;

    public HandicapDiceStrategy(DiceStrategy handicappedDiceStrategy, DiceStrategy normalDiceStrategy, Color colorOfHandicappedPlayer) {
        this.handicappedDiceStrategy = handicappedDiceStrategy;
        this.normalDiceStrategy = normalDiceStrategy;
        this.colorOfHandicappedPlayer = colorOfHandicappedPlayer;
    }

    public void updateDiceValuesLeft(int indexOfValidDice, Game game) {
        setState(game);
    }

    private boolean isHandicappedPlayerInTurn(Game game) {
        return game.getPlayerInTurn() == colorOfHandicappedPlayer;
    }

    private void setState(Game game) {
        if (isHandicappedPlayerInTurn(game)) {
            currentState = handicappedDiceStrategy;
        } else {
            currentState = normalDiceStrategy;
        }
    }
}
