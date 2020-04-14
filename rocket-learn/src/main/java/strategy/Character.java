package strategy;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-23 21:52
 **/
public abstract class Character {

    protected WeaponBehavior weaponBehavior;

    abstract void fight();

    public WeaponBehavior getWeaponBehavior() {
        return weaponBehavior;
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}
