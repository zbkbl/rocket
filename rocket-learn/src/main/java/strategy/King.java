package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:53
 **/
public class King extends Character {

    @Override
    void fight() {
        super.weaponBehavior.useWeapon();
    }
}
