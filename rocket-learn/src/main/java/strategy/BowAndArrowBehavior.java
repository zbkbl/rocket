package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:51
 **/
public class BowAndArrowBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("use bow and arrow");
    }
}
