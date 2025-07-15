package moster;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    public static void main(String[] args) {

        ArrayList<Monster> monsters = new ArrayList<>();

        monsters.add(new NormalMonster("슬라임", 300, 80, 57));
        monsters.add(new NormalMonster("고블린", 50, 12, 4));
        monsters.add(new NormalMonster("오크", 80, 15, 7));
        monsters.add(new NormalMonster("스켈레톤", 60, 14, 10));
        monsters.add(new NormalMonster("트롤", 120, 18, 6));
        monsters.add(new NormalMonster("골렘", 100, 20, 25));
        monsters.add(new NormalMonster("와이번", 150, 25, 15));
        monsters.add(new NormalMonster("리치", 130, 35, 12));
        monsters.add(new NormalMonster("키메라", 200, 30, 20));
        monsters.add(new NormalMonster("드래곤", 300, 40, 30));

        monsters.add(new FireMonster("이프리트", 140, 22, 18, 35));
        monsters.add(new FireMonster("헬하운드", 90, 28, 10, 25));
        monsters.add(new FireMonster("파이어 골렘", 180, 25, 22, 20));

        System.out.println("=== 전투 준비! ===");
        System.out.println();

        System.out.println("=== 등록된 몬스터 목록 ===");
        for (int i = 0; i < monsters.size(); i++) {
            System.out.print(i + ". ");
            monsters.get(i).info();
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("첫 번째 몬스터를 선택하세요 (0-" + (monsters.size()-1) + "): ");
        int choice1 = scanner.nextInt();

        while (choice1 < 0 || choice1 >= monsters.size()) {
            System.out.print("잘못된 선택입니다. 다시 선택하세요 (0-" + (monsters.size()-1) + "): ");
            choice1 = scanner.nextInt();
        }

        System.out.print("두 번째 몬스터를 선택하세요 (0-" + (monsters.size()-1) + "): ");
        int choice2 = scanner.nextInt();

        while (choice2 < 0 || choice2 >= monsters.size() || choice2 == choice1) {
            if (choice2 == choice1) {
                System.out.print("같은 몬스터는 선택할 수 없습니다. 다른 몬스터를 선택하세요: ");
            } else {
                System.out.print("잘못된 선택입니다. 다시 선택하세요 (0-" + (monsters.size()-1) + "): ");
            }
            choice2 = scanner.nextInt();
        }

        Monster monster1 = createMonsterCopy(monsters.get(choice1));
        Monster monster2 = createMonsterCopy(monsters.get(choice2));

        System.out.println();
        System.out.println("=== 전투 시작! ===");
        System.out.println("전투 참가자:");
        monster1.info();
        monster2.info();
        System.out.println();

        System.out.println("전투 준비...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int round = 1;

        while (monster1.getHp() > 0 && monster2.getHp() > 0) {

            int damage1to2 = monster1.attack(monster2);
            monster2.setHp(monster2.getHp() - damage1to2);

            String attack1Result = monster1.getName() + " → " + monster2.getName() + " (데미지: " + damage1to2 + ", 남은HP: " + monster2.getHp() + ")";

            String attack2Result = "";
            if (monster2.getHp() > 0) {
                int damage2to1 = monster2.attack(monster1);
                monster1.setHp(monster1.getHp() - damage2to1);
                attack2Result = " | " + monster2.getName() + " → " + monster1.getName() + " (데미지: " + damage2to1 + ", 남은HP: " + monster1.getHp() + ")";
            }

            System.out.println("라운드 " + round + ": " + attack1Result + attack2Result);

            round++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("=== 전투 종료! ===");
        if (monster1.getHp() > 0) {
            System.out.println(monster1.getName() + "이(가) 승리했습니다!");
            System.out.println("승리자 정보: ");
            monster1.info();
        } else {
            System.out.println(monster2.getName() + "이(가) 승리했습니다!");
            System.out.println("승리자 정보: ");
            monster2.info();
        }

        scanner.close();
    }

    public static Monster createMonsterCopy(Monster original) {
        if (original instanceof FireMonster) {
            FireMonster fireMonster = (FireMonster) original;
            return new FireMonster(
                    fireMonster.getName(),
                    fireMonster.getMaxHp(),
                    fireMonster.getAttack(),
                    fireMonster.getDefense(),
                    fireMonster.getFireSkillDamage()
            );
        } else {

            return new NormalMonster(
                    original.getName(),
                    original.getMaxHp(),
                    original.getAttack(),
                    original.getDefense()
            );
        }
    }

}