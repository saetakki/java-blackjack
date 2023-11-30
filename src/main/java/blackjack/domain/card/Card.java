package blackjack.domain.card;
import camp.nextstep.edu.missionutils.Console;

/**
 * 카드 한장을 의미하는 객체
 */
public class Card {
    private final Symbol symbol;

    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    // TODO Card 관련 추가 기능 구현

    public int getCardData(){
//        return (symbol.getScore()+type.getType());
        return symbol.getScore();
    }

    private int aceSymbol = Symbol.ACE.getScore();

    public void adjustAceSymbol(Symbol symbol){
        System.out.println("현재 Ace카드의 점수는" + aceSymbol + "입니다. Ace카드의 점수를 바꾸시겠습니까? (예는 y, 아니오는 n)");
        String wantAdjust = Console.readLine();
        if (wantAdjust == "y")
            aceSymbol = 12 - aceSymbol;
    }

}