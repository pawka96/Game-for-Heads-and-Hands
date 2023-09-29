public class Player extends Entity {
    /*
     * Class of Player which extends Entity class.
     */

    private int maxHealth;

    public int getMaxHealth() {

        return maxHealth;
    }

    public void setMaxHealth(int maxHlth) {

        maxHealth = maxHlth;
    }

    public void toHeal() {
        /*
         * This method using for healing spell of player.
         */

        int health = getHealth();
        health += getMaxHealth() * 0.3;
        setHealth(health);
    }
}
