
# 미션 - 블랙잭

## 🔍 진행 방식

- 미션은 **기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항** 세 가지로 구성되어 있다.
- 세 개의 요구 사항을 만족하기 위해 노력한다. 특히 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## 📮 미션 제출 방법

- 미션 구현을 완료한 후 GitHub을 통해 제출해야 한다.
    - GitHub을 활용한 제출 방법은 [프리코스 과제 제출](https://docs.google.com/document/d/1cmg0VpPkuvdaetxwp4hnyyFC_G-1f2Gr8nIDYIWcKC8/edit?usp=sharing) 문서를 참고해
      제출한다.
- GitHub에 미션을 제출한 후 [우아한테크코스 지원](https://apply.techcourse.co.kr) 사이트에 접속하여 프리코스 과제를 제출한다.
    - 자세한 방법은 [제출 가이드](https://github.com/woowacourse/woowacourse-docs/tree/master/precourse#제출-가이드) 참고
    - **지원 플랫폼을 통해 과제를 제출하지 않으면 최종 제출되지 않은 것으로 처리되므로 주의한다.**

## 🚨 과제 제출 전 체크 리스트 - 0점 방지

- 기능 구현을 모두 정상적으로 했더라도 **요구 사항에 명시된 출력값 형식을 지키지 않을 경우 0점으로 처리**한다.
- 기능 구현을 완료한 뒤 아래 가이드에 따라 테스트를 실행했을 때 모든 테스트가 성공하는지 확인한다.
- **테스트가 실패할 경우 0점으로 처리**되므로, 반드시 확인 후 제출한다.

### 테스트 실행 가이드

- 터미널에서 `java -version`을 실행하여 Java 버전이 17인지 확인한다.
  Eclipse 또는 IntelliJ IDEA와 같은 IDE에서 Java 17로 실행되는지 확인한다.
- 터미널에서 Mac 또는 Linux 사용자의 경우 `./gradlew clean test` 명령을 실행하고,
  Windows 사용자의 경우 `gradlew.bat clean test` 또는 `./gradlew.bat clean test` 명령을 실행할 때 모든 테스트가 아래와 같이 통과하는지 확인한다.

```
BUILD SUCCESSFUL in 0s
```
---

## 🚀 기능 요구 사항

### 라이브러리

   -   사용자가 입력하는 값은  `camp.nextstep.edu.missionutils.Console`의  `readLine()`을 활용한다.

> ### 기능 요구사항
>
> 1. 블랙잭 게임을 진행하는 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
>
> 2. 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다. 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
>
> 3. 게임을 시작하면 플레이어는 두 장의 카드를 지급받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.
>
> 4. 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
>
> 5. 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관없이 승리해 베팅 금액을 받는다.
>
> ### 프로그램 실행 결과
>
> ![프로그램 실행 결과](result_image.jpg "프로그램 실행 결과")
>
> ### 프로그래밍 요구사항
>
> #### 객체1
>
> ```java
> package domain.card;
>
> /**
>  * 카드 한장을 의미하는 객체
>  */
> public class Card {
>     private final Symbol symbol;
>
>     private final Type type;
>
>     public Card(Symbol symbol, Type type) {
>         this.symbol = symbol;
>         this.type = type;
>     }
>
>     // TODO Card 관련 추가 기능 구현
> }
> ```
>
> - 다음 Card 객체를 활용해 구현해야 한다.
>
> - Card 기본 생성자를 추가할 수 없다.
>
> - 필드(인스턴스 변수)인 symbol과 type의 접근 제어자 private을 변경할 수 없다.
>
> - Card에 필드(인스턴스 변수)를 추가할 수 없다.
>
> #### 객체2
>
> ```java
> package domain.user;
>
> import domain.card.Card;
>
> import java.util.ArrayList;
> import java.util.List;
>
> /**
>  * 게임 참여자를 의미하는 객체
>  */
> public class Player {
>     private final String name;
>     private final double bettingMoney;
>     private final List<Card> cards = new ArrayList<>();
>
>     public Player(String name, double bettingMoney) {
>         this.name = name;
>         this.bettingMoney = bettingMoney;
>     }
>
>     public void addCard(Card card) {
>         cards.add(card);
>     }
>
>     // TODO 추가 기능 구현
> }
> ```
>
> - 다음 Player 객체를 활용해 구현해야 한다.
>
> - Player 기본 생성자를 추가할 수 없다.
>
> - 필드(인스턴스 변수)인 name, bettingMoney, cards의 접근 제어자 private을 변경할 수 없다.
>
> - Card에 필드(인스턴스 변수)를 추가할 수 없다.
>
> #### 객체3
>
> ```java
> package domain.user;
>
> import domain.card.Card;
>
> import java.util.ArrayList;
> import java.util.List;
>
> /**
>  * 게임 딜러를 의미하는 객체
>  */
> public class Dealer {
>     private final List<Card> cards = new ArrayList<>();
>
>     public Dealer() {}
>
>
>     public void addCard(Card card) {
>         cards.add(card);
>     }
>
>     // TODO 추가 기능 구현
> }
> ```
>
> - 다음 Dealer 객체를 활용해 구현해야 한다.
>
> - Dealer 기본 생성자 이외 다른 생성자를 추가할 수 없다.
>
> - 필드(인스턴스 변수)인 cards의 접근 제어자 private을 변경할 수 없다.
>
> - Dealer에 필드(인스턴스 변수)를 추가할 수 없다.
>
> #### 추가 선택사항
>
> - 기본으로 제공하는 Card, Player, Dealer 객체에 예외 처리가 되어 있지 않다. 예외 처리를 한다.
>
> - Player와 Dealer를 구현하다보면 중복 코드가 발생할 수 있다. 중복 코드를 제거해 본다.
>
>   - 힌트 : 자바의 상속을 활용해 중복을 제거해 본다.

## 🎯 프로그래밍 요구 사항

- JDK 17 버전에서 실행 가능해야 한다. **JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [Java 코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java) 가이드를 준수하며 프로그래밍한다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그램 구현이 완료되면 `ApplicationTest`의 모든 테스트가 성공해야 한다. **테스트가 실패할 경우 0점 처리한다.**
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- else 예약어를 쓰지 않는다.
    - 힌트: if 조건절에서 값을 return 하는 방식으로 구현하면 else를 사용하지 않아도 된다.
    - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.
