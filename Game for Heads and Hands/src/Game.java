public class Game {
    /*
     * The key class of the game with main method.
     */

    public static void main(String[] args) {

        GameBuilder game = new GameBuilder();
        Player player = new Player();
        Monster monster = new Monster();
        Entity winner;

        game.initialization(player, monster);
        System.out.println(player);
        System.out.println(monster);
        winner = game.start(player, monster);
        game.finish(winner);
    }
}
