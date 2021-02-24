import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BattleShip {
    private static final int FIELD_SIZE = 3;
    private static int[][] player1Field = new int[FIELD_SIZE][FIELD_SIZE];
    private static int[][] player2Field = new int[FIELD_SIZE][FIELD_SIZE];
    private static boolean isOver = false;
    private static String player1Name;
    private static String player2Name;
    private static boolean isPlayer1Winner;
    private static int shotX;
    private static int shotY;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    private static void takeATurn(int playerNumber) throws IOException {
        //todo
        // запросить координаты выстрела
        // по ним проверить куда попали и соответсвенно изменить матрицу противника
        System.out.println("Please input the coordinates for the shot (X)");
        shotX = Integer.parseInt(reader.readLine());
        System.out.println("Please input the coordinates for the shot (Y)");
        shotY = Integer.parseInt(reader.readLine());
        if (playerNumber == 1) {
            switch (playerNumber == 1 ? player2Field[shotX][shotY] : player1Field[shotX][shotY]) {
                case 0: {
                    player2Field[shotX][shotY] = 1;
                    System.out.println("Missed!");
                }
                case 1: {
                    player2Field[shotX][shotY] = 1;
                    System.out.println("???");
                }
                case 2: {
                    player2Field[shotX][shotY] = 3;
                    System.out.println("Hit!");
                }
                case 3: {
                    player2Field[shotX][shotY] = 3;
                    System.out.println("???");
                }
            }
        } else {
            switch (playerNumber == 1 ? player2Field[shotX][shotY] : player1Field[shotX][shotY]) {
                case 0: {
                    player1Field[shotX][shotY] = 1;
                    System.out.println("Missed!");
                }
                case 1: {
                    player1Field[shotX][shotY] = 1;
                    System.out.println("???");
                }
                case 2: {
                    player1Field[shotX][shotY] = 3;
                    System.out.println("Hit!");
                }
                case 3: {
                    player1Field[shotX][shotY] = 3;
                    System.out.println("???");

                }
            }
        }
    }

    private static boolean checkPossibilityToPlaceShip
            (int[][] monitor, String coordinateX, String coordinateY, String direction, int shipLength) {
        //todo

        return true;
    }

    private static void placeShips(int playerNumber) throws IOException {

        for (int i = 4; i > 0; i--) {
            for (int j = 1; j < 6 - i; j++) {

                boolean isPlaced = false;

                while (!isPlaced) {
                    System.out.println("Please enter coordinates for ship with length " + i);
                    System.out.println("X = ");
                    String x = reader.readLine();
                    System.out.println("Y = ");
                    String y = reader.readLine();
                    System.out.println("Please enter the orientation of the ship - up(u),down(d),left(l) or right(r)");
                    String shipDirection = reader.readLine();
                    if (checkPossibilityToPlaceShip(
                            playerNumber == 1 ? player1Field : player2Field, x, y, shipDirection, i)
                    ) {
                        placeShip();
                        isPlaced = true;
                    } else System.out.println("Something went wrong, please enter new data ....");
                }
            }

        }

        //todo
        // запросить координаты лев/верх точки  + запростить ориентацию
        // проверить можно ли там поставить корабль

    }

    private static void placeShip() {

    }

    private static String convertSymbol(int value, boolean myMonitor) {
        switch (value) {
            case 0: {
                return "O";
            } // no shot, no ship
            case 1: {
                return "X";
            } //  shot , no ship
            case 2: {
                return myMonitor ? "5" : "O";
            } //  ship , not shot
            case 3: {
                return "8";
            }
        }
        return "";
    }

    private static void showMonitors(int playerNumber) {
        boolean playerNumOne = playerNumber == 1;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(convertSymbol(playerNumOne ? player1Field[i][j] : player2Field[i][j], true));
                System.out.print(j == (FIELD_SIZE - 1) ? "" : " ");
            }
            System.out.print("         ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(convertSymbol(playerNumOne ? player2Field[i][j] : player1Field[i][j], false));
                System.out.print(j == (FIELD_SIZE - 1) ? "" : " ");
            }
            System.out.print(System.lineSeparator());
        }
    }

    private static void startGame() throws IOException {

        System.out.println("Please enter a name for Player 1 ");
        BufferedReader reader = null;
        player1Name = (reader.readLine());
        System.out.println("Please enter a name for Player 2 ");
        player2Name = (reader.readLine());
        placeShips(1);
        placeShips(2);
    }

    private static void gameOver(boolean isPlayer1Winner) {
        // todo
        //  действия при окончании игры
        System.out.println("Game over!");
        System.out.println("Congratulations! Player " + (isPlayer1Winner ? "1" : "2" + " wins!"));

    }

    public static void gameMain(String[] args) throws IOException {
        startGame();
        int currentPlayer = 0;
        while (isOver) {
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            takeATurn(currentPlayer);
        }
        gameOver(isPlayer1Winner);
    }

    public static void main(String[] args) throws IOException {
        player1Field = new int[][]{
                {0, 0, 0},
                {2, 3, 3},
                {1, 1, 1}};
        player2Field = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {2, 2, 3}};
        showMonitors(1);
        // placeShips(1);
        takeATurn(1);
        showMonitors(1);

    }


}