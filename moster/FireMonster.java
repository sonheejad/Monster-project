package moster;

public class FireMonster extends Monster {
    private int fireSkillDamage;

    public FireMonster(String name, int maxHp, int attack, int defense, int fireSkillDamage) {
        super(name, maxHp, attack, defense);
        this.fireSkillDamage = fireSkillDamage;
    }


    @Override
    public int attack(Monster target) {

        int basicDamage = this.attack - target.getDefense();
        basicDamage = Math.max(0, basicDamage); // 최소 0 데미지


        if (Math.random() < 0.35) {

            int totalDamage = basicDamage + this.fireSkillDamage;
            System.out.println(this.name + "의 화염 스킬 발동! (추가 피해량: " + this.fireSkillDamage + ")");
            return totalDamage;
        } else {

            return basicDamage;
        }
    }

    @Override
    public void info() {
        System.out.println(name + " (HP: " + hp + "/" + maxHp + ", 공격력: " + attack + ", 방어력: " + defense + ", 화염스킬: " + fireSkillDamage + ")");
    }


    public int getFireSkillDamage() {
        return fireSkillDamage;
    }
}