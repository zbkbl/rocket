package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:56
 **/
public class TestStrategy {
    public static void main(String[] args) {
        Character c = new King();
        WeaponBehavior w = new KnifeBehavior();
        c.setWeaponBehavior(w);

        c.fight();

        Character c1 = new Queuen();
        WeaponBehavior w1 = new SwordBehavior();
        c1.setWeaponBehavior(w1);
        c1.fight();

        Character c2 = new Troll();
        WeaponBehavior w2 = new AxeBehavior();
        c2.setWeaponBehavior(w2);
        c2.fight();

        Character c3 = new Knight();
        WeaponBehavior w3 = new BowAndArrowBehavior();
        c3.setWeaponBehavior(w3);
        c3.fight();
    }
}
