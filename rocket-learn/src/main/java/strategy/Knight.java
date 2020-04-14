package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:55
 **/
public class Knight extends Character {
    @Override
    void fight() {
        super.weaponBehavior.useWeapon();
    }
}
