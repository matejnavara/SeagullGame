package uk.co.greedygull;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

	public static final Assets instance = new Assets();

	private AssetManager assetManager;

	public TextureRegion map;
	public TextureRegion player;
	public TextureRegion fish;
	
	public static Texture INTRO = new Texture(Constants.SEAGULL_INTRO);
	
	public static Texture PLAYER = new Texture(Constants.PLAYER_TEX);
	public static Texture PLAYERSHADOW = new Texture(Constants.PLAYERSHADOW_TEX);
	public static Texture FISH = new Texture(Constants.FISH_TEX);
	public static Texture AMMO = new Texture(Constants.AMMO_TEX);
	public static Texture TARGET_1 = new Texture(Constants.TARGET_TEX_1);
	public static Texture TARGETHIT_1 = new Texture(Constants.TARGETHIT_TEX_1);
	public static Texture TARGET_2 = new Texture(Constants.TARGET_TEX_2);
	public static Texture TARGETHIT_2 = new Texture(Constants.TARGETHIT_TEX_2);
	public static Texture TARGET_3 = new Texture(Constants.TARGET_TEX_3);
	public static Texture TARGETHIT_3 = new Texture(Constants.TARGETHIT_TEX_3);
	public static Texture TARGET_4 = new Texture(Constants.TARGET_TEX_4);
	public static Texture TARGETHIT_4 = new Texture(Constants.TARGETHIT_TEX_4);
	public static Texture FOOD = new Texture(Constants.FOOD_TEX);
	public static Texture CROW = new Texture(Constants.CROW_TEX);

	public static Texture[] targetsTex = new Texture[]{
		 TARGET_1,
		 TARGET_2,
		 TARGET_3,
		 TARGET_4
	};
	
	public static Texture[] targetsTexHit = new Texture[]{
		 TARGETHIT_1,
		 TARGETHIT_2,
		 TARGETHIT_3,
		 TARGETHIT_4
	};
	
	
	public static Texture MAP = new Texture(Constants.SEAGULL_MAP);
	
	public static Texture GAMEOVER = new Texture(Constants.GAMEOVER_TEX);


	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		assetManager.setLoader(Texture.class, new TextureLoader(
				new InternalFileHandleResolver()));
		assetManager.load(Constants.SEAGULL_MAP, Texture.class);
		assetManager.load(Constants.PLAYER_TEX, Texture.class);
		assetManager.load(Constants.FISH_TEX, Texture.class);

		assetManager.finishLoading();
		
		//map = new TextureRegion(assetManager.get(Constants.SEAGULL_MAP, Texture.class));
		

	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}


}
