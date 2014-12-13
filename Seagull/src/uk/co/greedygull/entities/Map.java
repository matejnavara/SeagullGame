package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Map {

	private Texture mapTex;
	private TextureRegion map;
	
	private float mapOffset;
	private static float scroll;
	private float mapSpeed = Constants.MAP_SPEED;
	
	public Map() {

		mapTex = Assets.MAP;
		map = new TextureRegion(mapTex,0,0,mapTex.getWidth(),mapTex.getHeight());
		mapOffset = mapTex.getHeight();
	}


	public void update() {

		mapOffset -= mapSpeed;
		if(mapOffset <= 0)
			mapOffset = mapTex.getHeight();
		
	}
	
	public void render(SpriteBatch sb){
		
		sb.draw(map, 0, mapOffset - mapTex.getHeight(), mapTex.getWidth(), mapTex.getHeight());
		sb.draw(map, 0, mapOffset, mapTex.getWidth(), mapTex.getHeight());

	}
	
	public static float getScroll(){
		return scroll;
	}

}
