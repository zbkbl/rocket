package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuyang
 * @description
 * @date 2020/12/16 15:33
 **/
public class ShuffleTest {

    private static ArrayList<String> Card() {
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("A");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
        numbers.add("10");
        numbers.add("J");
        numbers.add("Q");
        numbers.add("K");
        //花式
        ArrayList<String> colors = new ArrayList<>();
        colors.add("黑桃");
        colors.add("红桃");
        colors.add("方片");
        colors.add("草花");
        //定义大小王
        String[] kings = new String[]{"大王", "小王"};
        //组合牌
        ArrayList<String> cardsList = new ArrayList<>();
        for (String color : colors) {
            for (String num : numbers) {
                cardsList.add(color + num);
            }
        }
        //加入两王
        cardsList.add(kings[0]);
        cardsList.add(kings[1]);
        //洗牌打乱
        Collections.shuffle(cardsList);

        return cardsList;
    }


    private static void playChinesePoker(List<String> cards) {
        //3人斗地主 发牌，ArrayList底层是一个数组，根据索引来发牌
        //定义三个集合，用于储存发的牌，最后一个底牌集合
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();
        ArrayList<String> lefCards = new ArrayList<>();//底牌
        //发牌
        for (int i = 0; i < cards.size(); i++) {
            //当索引为51时开始，不再发牌，留作底牌，其他发掉
            if (i >= 51) {
                lefCards.add(cards.get(i));
            } else if (i % 3 == 0) {
                player1.add(cards.get(i));
            } else if (i % 3 == 1) {
                player2.add(cards.get(i));
            } else if (i % 3 == 2) {
                player3.add(cards.get(i));
            }

        }

        // 查看各人牌情况 ：
        System.out.println("player1：" + player1);
        System.out.println("player2：" + player2);
        System.out.println("player3：" + player3);
        System.out.println("底牌:" + lefCards);

    }

    public static void main(String[] args) {
    	System.out.println(Card().size());
    	System.out.println(Card());
        playChinesePoker(Card());
    }
}
