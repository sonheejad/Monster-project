package moster;


public abstract class Monster {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;

    public Monster(String name, int maxHp, int attack, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }


    public abstract int attack(Monster target);

    public void info() {
        System.out.println(name + " (HP: " + hp + "/" + maxHp + ", 공격력: " + attack + ", 방어력: " + defense + ")");
    }


    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }


    public void setHp(int hp) { this.hp = hp; }
}