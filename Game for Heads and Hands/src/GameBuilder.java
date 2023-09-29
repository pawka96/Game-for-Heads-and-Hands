public class GameBuilder {
    /*
     * This class using for building of whole process of the game.
     */

    Player player;
    Monster monster;

    public void initialization(Player p, Monster m) {
        /*
         * This method using for initial random generation of variables
         * for participants of the game.
         */

        player = p;
        monster = m;
        int n = 30;     // max value of variables

        player.setAttack((int) (Math.random() * n) + 1);    // player's attack from 1 to 30
        monster.setAttack((int) (Math.random() * n) + 1);    // monster's attack from 1 to 30

        player.setDefense((int) (Math.random() * n) + 1);   // player's defense from 1 to 30
        monster.setDefense((int) (Math.random() * n) + 1);    // monster's defence from 1 to 30

        player.setHealth((int) (Math.random() * (n+1)) + 1);   // player's health from 1 to 30
        player.setMaxHealth(player.getHealth());
        monster.setHealth((int) (Math.random() * (n+1)) + 1);   // monster's health from 1 to 30

        player.setDamageMin((int) (Math.random() * ((n / 2) + 1)) + 1);   // player's min damage from 1 to 15
        monster.setDamageMin((int) (Math.random() * ((n / 2) +1 )) + 1);   // monster's min damage from 1 to 15
        int minP = p.getDamageMin();
        int minM = m.getDamageMin();
        player.setDamageMax((int) (Math.random() * (n - minP + 1)) + minP + 1);   // player's max damage from min to 30
        monster.setDamageMax((int) (Math.random() * (n - minM + 1)) + minM + 1);   // monster's max damage from min to 30
    }

    public Entity start(Player p, Monster m) {
        /*
         * This is basic method of the game,
         * which set null reference for object whose health is equzal zero,
         * and return an object of the winner.
         */

        player = p;
        monster = m;
        int attMod;     // attack modificator
        int damage, health;
        boolean success;
        int healCnt = 0;        // heal count of player
        int stage = 1;

        // While both of objects are alive, this game cycle will perform.
        while (!(player == null) & !(monster == null)) {
            System.out.println("\nNumber of stage: " + stage);

            attMod = player.calcMod(player.getAttack(), monster.getDefense());
            System.out.println("\nPlayer's attack modificator: " + attMod);
            success = player.calcSucc(attMod);
            System.out.println("Player's success: " + success);

            if (success) {
                damage = (int) (Math.random() * (player.getDamageMax() - player.getDamageMin()) + 1) + player.getDamageMin();
                System.out.println("Player's damage: " + damage);
                health = player.toAttack(success, damage, monster.getHealth());
                monster.setHealth(health);
                System.out.println("Monster's health after recieved attack: " + monster.getHealth());
            }

            if (monster.getHealth() == 0) {
                System.out.println(player.getClass().getCanonicalName() + " killed " + monster.getClass().getCanonicalName());
                monster = null;
                break;
            }

            attMod = monster.calcMod(monster.getAttack(), player.getDefense());
            System.out.println("\nMonster's attack modificator: " + attMod);
            success = monster.calcSucc(attMod);
            System.out.println("Monster's success: " + success);

            if (success) {
                damage = (int) (Math.random() * (monster.getDamageMax() - monster.getDamageMin()) + 1) + monster.getDamageMin();
                System.out.println("Monster's damage: " + damage);
                health = monster.toAttack(success, damage, player.getHealth());
                player.setHealth(health);
                System.out.println("Players's health after recieved attack: " + player.getHealth());
            }

            if (player.getHealth() == 0) {
                System.out.println(monster.getClass().getCanonicalName() + " killed " + player.getClass().getCanonicalName());
                player = null;
                break;
            }

            if ((player.getHealth() < player.getMaxHealth() / 2) & (healCnt < 3)) {
                System.out.println(player.getClass().getCanonicalName() + " is going to use heal spell");
                player.toHeal();
                healCnt++;
                System.out.println("Player's health after healing: " + player.getHealth());
            }
            stage++;
        }

        // return object of the winner
        if (monster == null) {
            return player;
        } else {
            return monster;
        }
    }

    public void finish(Entity winner) {
        /*
         * Method which output message with info about winner by getting argument.
         */

        if (winner instanceof Player) {
            System.out.println("Player won the game!");
        } else {
            System.out.println("Player lost the game!");
        }
    }
}
