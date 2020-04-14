package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:54
 **/
public class Queuen extends Character {
    @Override
    void fight() {
        super.weaponBehavior.useWeapon();
    }
}
