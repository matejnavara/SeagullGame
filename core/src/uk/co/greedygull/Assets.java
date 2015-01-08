package uk.co.greedygull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class initilizes all the assets in the game as static Textures or Sounds using the constants defined in Constants.java
 * By doing this they can be freely accessed by the rest application.
 * @author Matej
 *
 */
public class Assets implements Disposable {
	
	public static Texture INTRO = new Texture(Constants.SEAGULL_INTRO);
	
	public static Texture HOWTO = new Texture(Constants.HOWTO_TEX);
	
	public static Texture GAMEOVER = new Texture(Constants.GAMEOVER_TEX);
	public static Texture UPGRADE = new Texture(Constants.UPGRADE_TEX);
	
	public static Texture BUTTON = new Texture(Constants.BUTTON);
	public static Texture BUTTONdwn1 = new Texture(Constants.BUTTON_DOWN1);
	public static Texture BUTTONdwn2 = new Texture(Constants.BUTTON_DOWN2);
	
	public static Texture UPGRADEstam = new Texture(Constants.UPGRADEstam_TEX);
	public static Texture UPGRADEfr = new Texture(Constants.UPGRADEfr_TEX);
	public static Texture UPGRADEspeed = new Texture(Constants.UPGRADEspeed_TEX);
	
	public static Texture PROGRESS = new Texture(Constants.PROGRESS_TEX);
	public static Texture PROGRESSBG = new Texture(Constants.PROGRESSBG_TEX);
	
	public static Texture GUIPAUSE = new Texture(Constants.GUIpause);
	public static Texture GUIPLAY = new Texture(Constants.GUIplay);
	public static Texture TARGET = new Texture(Constants.TARGET);
	public static Texture LEFT = new Texture(Constants.LEFT);
	public static Texture RIGHT = new Texture(Constants.RIGHT);
	
	public static Texture PLAYER = new Texture(Constants.PLAYER_TEX1);
	public static Texture PLAYER2 = new Texture(Constants.PLAYER_TEX2);
	public static Texture PLAYER3 = new Texture(Constants.PLAYER_TEX3);
	
	public static Texture PLAYERSHADOW = new Texture(Constants.PLAYERSHADOW_TEX1);
	public static Texture PLAYERSHADOW2 = new Texture(Constants.PLAYERSHADOW_TEX2);
	public static Texture PLAYERSHADOW3 = new Texture(Constants.PLAYERSHADOW_TEX3);
	
	
	public static Texture FISH = new Texture(Constants.FISH_TEX);
	public static Texture AMMO = new Texture(Constants.AMMO_TEX);
	public static Texture SPLAT = new Texture(Constants.SPLAT_TEX);
	
	public static Texture TARGET_1 = new Texture(Constants.TARGET_TEX_1);
	public static Texture TARGETHIT_1 = new Texture(Constants.TARGETHIT_TEX_1);
	public static Texture TARGET_2 = new Texture(Constants.TARGET_TEX_2);
	public static Texture TARGETHIT_2 = new Texture(Constants.TARGETHIT_TEX_2);
	public static Texture TARGET_3 = new Texture(Constants.TARGET_TEX_3);
	public static Texture TARGETHIT_3 = new Texture(Constants.TARGETHIT_TEX_3);
	public static Texture TARGET_4 = new Texture(Constants.TARGET_TEX_4);
	public static Texture TARGETHIT_4 = new Texture(Constants.TARGETHIT_TEX_4);
	public static Texture FOOD = new Texture(Constants.FOOD_TEX);
	
	public static Texture ENEMY = new Texture(Constants.CROW_TEX);
	public static Texture ENEMY2 = new Texture(Constants.CROW_TEX2);
	public static Texture ENEMY3 = new Texture(Constants.CROW_TEX3);
	
	public static Texture HITMARKER = new Texture(Constants.HITMARKER);
	
	public static Texture MAP = new Texture(Constants.SEAGULL_MAP);
	
	public static Sound WAVES1 = Gdx.audio.newSound(Gdx.files.internal("data/waves01.mp3"));
	public static Sound GULL1 = Gdx.audio.newSound(Gdx.files.internal("data/gullshort.mp3"));
	public static Sound GULL2 = Gdx.audio.newSound(Gdx.files.internal("data/gulllong.mp3"));
	public static Sound HAWK = Gdx.audio.newSound(Gdx.files.internal("data/hawk.mp3"));
	
	/**
	 * Textures for player animation
	 */
	public static Texture[] playerAnim = new Texture[]{
		PLAYER,
		PLAYER2,
		PLAYER3
	};
	
	/**
	 * Textures for player shadow animation
	 */
	public static Texture[] shadowAnim = new Texture[]{
		PLAYERSHADOW,
		PLAYERSHADOW2,
		PLAYERSHADOW3
	};
	
	/**
	 * Textures for enemy animation
	 */
	public static Texture[] enemyAnim = new Texture[]{
		ENEMY,
		ENEMY2,
		ENEMY3
	};
	
	/**
	 * Textures for all targets
	 */
	public static Texture[] targetsTex = new Texture[]{
		 TARGET_1,
		 TARGET_2,
		 TARGET_3,
		 TARGET_4
	};
	
	/**
	 * Corresponding hit state textures for all targets
	 */
	public static Texture[] targetsTexHit = new Texture[]{
		 TARGETHIT_1,
		 TARGETHIT_2,
		 TARGETHIT_3,
		 TARGETHIT_4
	};
	
	
	

//
//	public void init(AssetManager assetManager) {
//		this.assetManager = assetManager;
//
////		assetManager.setLoader(Texture.class, new TextureLoader(
////				new InternalFileHandleResolver()));
////		assetManager.load(Constants.SEAGULL_MAP, Texture.class);
////		assetManager.load(Constants.PLAYER_TEX, Texture.class);
////		assetManager.load(Constants.FISH_TEX, Texture.class);
////
////		assetManager.finishLoading();
//		
//		//map = new TextureRegion(assetManager.get(Constants.SEAGULL_MAP, Texture.class));
//		
//
//	}

	@Override
	public void dispose() {
		
	}


}
