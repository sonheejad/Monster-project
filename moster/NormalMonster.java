package moster;

public class NormalMonster extends Monster {

    public NormalMonster(String name, int maxHp, int attack, int defense) {
        super(name, maxHp, attack, defense);
    }


    @Override
    public int attack(Monster target) {

        if (Math.random() < 0.2) {

            int criticalDamage = this.attack * 2;
            System.out.println("💥 " + this.name + "의 치명타 공격!");
            return criticalDamage;
        } else {

            int normalDamage = this.attack - target.getDefense();
            return Math.max(0, normalDamage); // 최소 0 데미지 (음수 방지)
        }
    }
}