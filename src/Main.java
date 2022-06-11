import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int roundNumber;
    public static int medic = 290;
    public static int golem = 600;
    public static int lucky = 200;
    public static int berserk = 240;
    public static int thor = 200;
    public static int[] heroesHealth = {270, 260, 250, medic, golem, lucky, berserk, thor};
    public static int[] heroesDamage = {10, 15, 20, 0, 5, 10, 15, 20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseDefence();
        bossHit();
        heroesHit();
        healHero();
        printStatistics();

    }

    public static void healHero() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && medic > 0) {
                Random randomHero = new Random();
                int healByRandomNumberOfPieces = randomHero.nextInt(70) + 1;
                heroesHealth[i] = heroesHealth[i] + healByRandomNumberOfPieces;
                break;
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        if (roundNumber >= 1) {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (heroesAttackType[i] == bossDefence) {

                        Random random = new Random();
                        int coefficient = random.nextInt(9) + 2;
                        int critical_damage = coefficient * heroesDamage[i];

                        if (bossHealth - critical_damage < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - critical_damage;
                        }
                        System.out.println("Critical damage: " + critical_damage + " [" + coefficient + "]");
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

        public static void chooseDefence () {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
            bossDefence = heroesAttackType[randomIndex];

            System.out.println("Boss chose: " + bossDefence);

            int golemAbility = 0;
            boolean luckyAbility;
            boolean berserkAbility;
            boolean thorAbility;
            if (bossDefence.equals(heroesAttackType[4])) {
                for (int i = 0; i < heroesHealth.length; i++) {
                    golemAbility = bossDamage;
                }
                golem = golemAbility;
            }

            if (bossDefence.equals(heroesAttackType[5])) {
                luckyAbility = random.nextBoolean();
                if (luckyAbility == true) {
                    lucky = lucky - 0;
                } else {
                    lucky = lucky - bossDamage;
                }
            }

            if (bossDefence.equals(heroesAttackType[6])) {
                berserkAbility = random.nextBoolean();
                if (berserkAbility == true) {
                    int avoidDamage = random.nextInt(50);
                    berserk = berserk - 0;
                    heroesDamage[6] = heroesDamage[6] + avoidDamage;
                } else {
                    berserk = berserk - bossDamage;
                }
            }

            if (bossDefence.equals(heroesAttackType[7])) {
                thorAbility = random.nextBoolean();
                if (thorAbility == true) {
                    for (int j = 0; j < heroesHealth.length; j++) {
                        heroesHealth[j] = heroesHealth[j] - 0;
                        if (j == heroesHealth.length) {
                            break;
                        }
                    }
                } else {
                    thor = thor - bossDamage;
                }
            }

        }

        public static boolean isGameFinished () {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!");
                return true;
            }

            boolean allHeroesDead = true;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss won!");
            }
            return allHeroesDead;
        }

        public static void printStatistics () {
            if (roundNumber == 0) {
                System.out.println("BEFORE GAME STARTED _______");
            } else {
                System.out.println("ROUND NUMBER: " + roundNumber);
            }

            System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] + " heath: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
            }
        }
}