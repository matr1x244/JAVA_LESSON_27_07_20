package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class XOgame {
    static final int SIZE = 5; // размер поля статическое
    static final int DOTS_TO_WIN = 4; // победная линия Д.З. 4

    static final char DOT_X = 'X'; // константа DOT_X
    static final char DOT_O = 'O'; // константа DOT_O
    static final char DOT_EMPTY = '.'; // константа DOT_EMPTY (пустой ход)

    static char[][] map; // двумерный массив карты

    static Scanner sc = new Scanner(System.in); // сканер ввода пользователя
    static Random random = new Random(); // Д.З. для искуствинного интелекта

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Ты победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

        }
    }


    public static void initMap() { // Иницилизация карты
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() { // Карта заполнение
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() { // ход пользователя
        int x, y;

        do {
            System.out.println("Input X, Y ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) { // проверка пустых клеток
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() { // ход робота класс
        int x;
        int y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;

    }

    public static boolean isFull() { // условие ничья проверка пустых ячеек
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean checkWin(char c) { // проверка победителей ?????!!!!!

        int diagonal1;
        int diagonal2;
        int horizont;
        int vertical;

        horizont = 0;
        vertical = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == c) {
                    horizont++;
                }
                if (map[j][i] == c) {
                    vertical++;
                }
            }
            if (horizont == DOTS_TO_WIN || vertical == DOTS_TO_WIN) {
                return true; //проверка по горизонтали и вертикали
            }
        }
        diagonal1 = 0;
        diagonal2 = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == c) {
                    diagonal1++;
                }
                if (map[j][i] == c) {
                    diagonal2++;
                }
            }
            if (diagonal1 == DOTS_TO_WIN || diagonal2 == DOTS_TO_WIN) {
                return true; //проверка по диагоналям
            }
        }
        return false;
    }


        /*
        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {return true; }
        if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {return true; }
        if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {return true; }

        if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {return true; }
        if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {return true; }
        if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {return true; }

        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {return true; }
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {return true; }


        return false;
        }
        */

}


     /*     1 2 3 4 5
          1 X . . . .
          2 . O . . . ????????
          3 . . X . .
          4 . O . X .
          5 . . O . X
          Ты победил!

     */

     /*   1 2 3 4 5
        1 X X X . X ????
        2 . . O . O
        3 . . . . .
        4 . . . . .
        5 . . . . O
        Ты победил!

     */

     /*     1 2 3 4 5
          1 . . . . .
          2 . . . . O
          3 . . . O O
          4 . . . X X ????
          5 . . . X X ????
          Ты победил!

     */



