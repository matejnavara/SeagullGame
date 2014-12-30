package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity{
	
	private int num = 0;
	private int attack = 100;
	private boolean canAttack = true;

	public Enemy(Vector2 pos, Vector2 direction) {
		super(Assets.CROW, pos, new Vector2(Assets.CROW.getWidth(),Assets.CROW.getHeight()), direction);
	}

	@Override
	public void update() {
		num++;
		pos.add(new Vector2((float)(3 * Math.sin((num * 0.5 * Math.PI) / 100)),direction.y));
		
	}
	
	public boolean checkEnd(){
		return pos.y < 0-texture.getHeight();
	}
	
	public int getAttack(){
		return attack;
	}
	
	public void attack(){
		canAttack = false;
	}
	
	public boolean canAttack(){
		return canAttack;
	}


}
