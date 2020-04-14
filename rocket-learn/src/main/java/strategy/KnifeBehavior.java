package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:50
 **/
public class KnifeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("use knife");
    }
}
