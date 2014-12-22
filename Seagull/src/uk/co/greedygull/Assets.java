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
	
	public static Texture PLAYER = new Texture(Constants.PLAYER_TEX);
	public static Texture FISH = new Texture(Constants.FISH_TEX);
	public static Texture AMMO = new Texture(Constants.AMMO_TEX);
	public static Texture TARGET = new Texture(Constants.TARGET_TEX);
	public static Texture TARGETHIT = new Texture(Constants.TARGETHIT_TEX);
	public static Texture FOOD = new Texture(Constants.FOOD_TEX);
	
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
