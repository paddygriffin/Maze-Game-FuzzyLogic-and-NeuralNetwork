package ie.gmit.sw.ai.weapon;

public class Weapon implements Wieldable {

	private String weaponName;
	private int attackValue;
	
	//constructor
	public Weapon(String weaponName, int attackValue) {
		super();
		this.weaponName = weaponName;
		this.attackValue = attackValue;
	}


	public String getWeaponName() {
		return this.weaponName;
	}

	
	public int getAttackValue() {
		return this.attackValue;
	}

}
