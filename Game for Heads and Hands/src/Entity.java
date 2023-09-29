public abstract class Entity {
    /*
     * Abstract class standard variables and methods for any entities in the game.
     */

    private int attack, defense, health;
    private int damageMin, damageMax;

    public String toString() {
        /*
         * Overridden method toString() with info about entity.
         */

        return "Features of new character " + getClass().getCanonicalName() + ": "
                + "\nAttack: " + getAttack()
                + "\nDefense: " + getDefense()
                + "\nDamage: " + getDamageMin() + "-" + getDamageMax()
                + "\nHealth: " + getHealth();
    }

    // Below are setters and getters of this class.
    public int getAttack() {

        return attack;
    }

    public int getDefense() {

        return defense;
    }

    public int getDamageMin() {

        return damageMin;
    }

    public int getDamageMax() {

        return damageMax;
    }

    public int getHealth() {

        return health;
    }

    public void setAttack(int att) {

        this.attack = att;
    }

    public void setDefense(int def) {

        this.defense = def;
    }

    public void setDamageMin(int min) {

        damageMin = min;
    }

    public void setDamageMax(int max) {

        damageMax = max;
    }

    public void setHealth(int hlth) {

        this.health = hlth;
    }

    public int calcMod(int att, int def) {
        /*
         * Method which calculate attack modificator.
         */

        int attMod = att - def + 1;
        if (attMod <= 0) {
            attMod = 1;
        }
        return attMod;
    }

    public boolean calcSucc(int attMod) {
        /*
         * This method define success by getting attack modificator.
         */

        int cube;       // variable for dice roll
        int count = 0;      // count of success dice rolls (5 or 6)

        for (int i = 1; i <= attMod; i++) {
            cube = (int) (Math.random() * 6) + 1;
            if (cube > 4) {
                count ++;
                break;
            }
        }
        if (count >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public int toAttack(boolean succ, int dmg, int hlth) {
        /*
         * This method using for attack, if it was success.
         */

        if (succ == true) {
            hlth -= dmg;
            if (hlth <= 0) {
                hlth = 0;
            }
        }
        return hlth;
    }
}
