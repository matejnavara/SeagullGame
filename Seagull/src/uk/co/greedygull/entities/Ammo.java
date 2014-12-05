package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ammo extends Entity{
	
	final static int DROPSPEED = 500;
	public Boolean active;

	public Ammo(Vector2 pos) {
		super(Assets.AMMO, pos, new Vector2(0,-1));	
		this.active = true;
	}

	@Override
	public void update() {
		pos.add(direction);
		
	}
	
	public boolean checkEnd(){
		return pos.y < 0;
	}
	


}
