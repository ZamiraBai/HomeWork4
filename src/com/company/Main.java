package com.company;

import java.util.Random;

public class Main {

    public static int[] health = {700, 250, 250, 250, 250};
    public static int[] damage = {50, 20, 20, 20, 20};
    public static String[] hitTypes = {"Physical", "Physical", "Magical", "Mental", "Medic"};
    public static String [] heroesTypes = new String[]{"Boss", "Warrior", "Mag", "Kinetic", "Medic"};
    public static int heal = 20;


    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            changeBossDefence();
            gameRound();
            toTreat(4);
            heal();
            hitTypes[0] = generateBossDefenceType();
        }
    }

    public static void gameRound() {
        for (int i = 1; i <= 3; i++) {
            if (health[0] <= 0) {
                break;
            }
            health[0] = hitPlayer(i);
        }
        if (health[0] > 0) {
            for (int i = 1; i <= 4; i++) {
                health[i] = hitBoss(i);
            }
        }
        printStatistics();
    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (health[0] <= 0) {
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0) { //4?
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }


    public static int hitBoss(int playerIndex) {
        Random r = new Random();
        int randomNumber = r.nextInt(8) + 1;
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            System.out.println(hitTypes[playerIndex] + "Нанес критический удар" + hitTypes[playerIndex] + randomNumber);
            return health[0] - damage[playerIndex] * randomNumber;
        } else {
            return health[0] - damage[playerIndex];
        }
    }

    public static int hitPlayer(int playerIndex) {
        return health[playerIndex] - damage[0];
    }

    public static String generateBossDefenceType() {
        Random r = new Random();
        int randomNumber = r.nextInt(4) + 1;

        return hitTypes[randomNumber];
    }

    public static void printStatistics() {
        System.out.println("_________________________");
        printStat(heroesTypes, health);
        System.out.println("_________________________");
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        hitTypes[0] = hitTypes[randomInt];
    }

    public static int toTreat(int playerIndex) {
        for (int i = 1; i <= 4; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(3)+1;
            if (health[4] > 0) {
                System.out.println("Medic treated " + hitTypes[randomInt]);
                System.out.println("New Health of " + hitTypes[randomInt] + ": " + (health[randomInt] + damage[4]));
                return health[randomInt] + damage[4];
            } else {
                System.out.println("Medic is dead!");
                break;
            }
        }
        return playerIndex;
    }

    public static void heal() {
        for (int i = 1; i <= 3; i++) {
            health[i] = health[i] + heal;

        }
    }

    public static void printStat(String[] heroesTypes, int[] health) {
        for (int i = 0; i < heroesTypes.length; i++) {

            if (health[i] <= 0) {
                System.out.println(heroesTypes[i] + " is died");
            } else {
                System.out.println(heroesTypes[i] + " health = " + health[i]);
            }

        }
    }
}