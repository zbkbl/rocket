package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:51
 **/
public class AxeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("use axe");
    }
}
