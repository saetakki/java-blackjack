package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import camp.nextstep.edu.missionutils.Console;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("참가자를 입력하세요.(쉼표로 구분)");
        String input = "dealer," + Console.readLine();













        List<String> participantsList = Arrays.asList(input.split(","));
        List<String> playerWhoGotBlackJack = new ArrayList<>();
        List<String> winners = new ArrayList<>();
        boolean isGameOver = false;

        List<Integer> cardList = new ArrayList<>(CardFactory.create().stream().map(Card::getCardData).toList());
        Collections.shuffle(cardList);

        Queue<Integer> deck = new LinkedList<>(cardList);

        Boolean[] flags = new Boolean[participantsList.size()];
        Arrays.fill(flags, Boolean.TRUE);

        Map<String, List<Integer>> participantsCards = new LinkedHashMap<>();
        for (String participant : participantsList) {
            participantsCards.put(participant, new ArrayList<>());
        }

        for (int player = 0; player < participantsList.size(); player++) {
            ArrayList<Integer> cards = new ArrayList<>();
            cards.add(deck.poll());
            cards.add(deck.poll());

            // 블랙잭 뽑음
            if (cards.contains(1) && (cards.contains(10) || cards.contains(11))) {
                cards = new ArrayList<>(List.of(11, 10));
                if (player == 0) {
                    playerWhoGotBlackJack.add("dealer");
                } else {
                    playerWhoGotBlackJack.add(participantsList.get(player));
                }
            }

            // 플레이어의 뽑은 카드 목록에 추가
            participantsCards.put(participantsList.get(player), cards);

            // 현재 카드 뽑은사람
            String currentPlayer = participantsList.get(player);
            // 현재까지 카드 누적합
            int currentPlayersTotalScore = participantsCards.get(currentPlayer).stream().reduce(0, Integer::sum);
            // 만약 현재 dealer의 턴이고 누적합이 17이상이라면 더이상 뽑지 않음
            if (currentPlayer.equals("dealer") && currentPlayersTotalScore >= 17) {
                flags[player] = false;
            }
        }

        System.out.println(participantsCards);

        if (playerWhoGotBlackJack.size() == 1 && playerWhoGotBlackJack.contains("dealer")) {
            System.out.println("딜러 승");
            isGameOver = true;
        }
        if (!playerWhoGotBlackJack.isEmpty() && !playerWhoGotBlackJack.contains("dealer")) {
            System.out.println(String.join(", ", playerWhoGotBlackJack) + " 승");
            isGameOver = true;
        }

        if (playerWhoGotBlackJack.size() > 1 && playerWhoGotBlackJack.contains("dealer")) {
            System.out.print("무승부");
            isGameOver = true;
        }
        // 블랙잭 아무도 못뽑았을때
        while (!isGameOver) {
            for (int remainPlayer = 0; remainPlayer < participantsList.size(); remainPlayer++) {

                String currentPlayer = participantsList.get(remainPlayer);
                int currentPlayersTotalScore = participantsCards.get(currentPlayer).stream().reduce(0, Integer::sum);

                if (flags[remainPlayer] && currentPlayer.equals("dealer")) {
                    if (currentPlayersTotalScore >= 17) {
                        flags[remainPlayer] = false;
                        isGameOver = true;
                    }
                    if (currentPlayersTotalScore <= 16) {
                        int nextCard = deck.poll();
                        participantsCards.get(currentPlayer).add(nextCard);
                        if (currentPlayersTotalScore + nextCard > 21) {
                            System.out.println("플레이어들이 이겼습니다.");
                            System.out.println(
                                    "딜러의 카드 : " + participantsCards.get(currentPlayer) + " 뽑은 카드 : " + nextCard);
                            Arrays.fill(flags, Boolean.FALSE);
                            isGameOver = true;
                            break;
                        }
                    }
                    System.out.println("dealer's cards");
                    System.out.println(participantsCards.get(currentPlayer));
                }

                if (flags[remainPlayer] && !currentPlayer.equals("dealer")) {
                    System.out.println(participantsCards.get(currentPlayer));
                    System.out.println(currentPlayer + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
                    String keepGoing = Console.readLine();
                    //안뽑음
                    if (keepGoing.equals("n")) {
                        flags[remainPlayer] = false;
                    }
                    //뽑음
                    if (keepGoing.equals("y")) {
                        int nextCard = deck.poll();
                        System.out.println("넥스트 : " + nextCard + " 현재 : " + currentPlayersTotalScore);
                        if (currentPlayersTotalScore + nextCard > 21) {
                            System.out.println(currentPlayer + "는 busted 되었습니다.");
                            System.out.println(
                                    currentPlayer + "의 카드 : " + participantsCards.get(currentPlayer) + " 뽑은 카드 : "
                                            + nextCard);
                            flags[remainPlayer] = false;
                            participantsCards.get(currentPlayer).clear();
                        }
                        else {
                            participantsCards.get(participantsList.get(remainPlayer)).add(nextCard);
                        }
                    }
                }
            }
            boolean hasTrue = Arrays.asList(flags).contains(true);
            if (!hasTrue) {
                isGameOver = true;
                int gap = 10000;
                for (int player = 0; player < participantsList.size(); player++) {
                    int playerScore = participantsCards.get(participantsList.get(player)).stream()
                            .reduce(0, Integer::sum);
                    int playerGap = 21 - playerScore;
                    if (gap < playerGap) {
                        winners.clear(); // 새로운 최고값을 찾았으므로 우승자 리스트를 초기화
                        winners.add(participantsList.get(player)); // 현재 플레이어를 추가
                        gap = playerGap;
                    } else if (gap == playerGap) {
                        winners.add(participantsList.get(player)); // 동점인 경우 현재 플레이어를 추가
                    }
                }
            }
        }

        if (!winners.isEmpty()) {
            System.out.println("우승자: " + String.join(", ", winners));
        };
    }
}
