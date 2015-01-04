package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends Screen{
	
	Skin skin;
	Stage stage;
	
	TextButtonStyle textButtonStyle;

	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		BitmapFont bfont = new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
		
		Pixmap pixmap = new Pixmap(150, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		Texture introTex = Assets.INTRO;
		Image intro = new Image(introTex);
		intro.setPosition(0, Constants.VIEWPORT_HEIGHT-introTex.getHeight());
		
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.RED);		
		textButtonStyle.font = skin.getFont("default");
		
		final TextButton textButton = new TextButton("PLAY",textButtonStyle);
		textButton.setPosition(100 + textButton.getWidth()/2, 270);
		
		final TextButton textButton2 = new TextButton("HOW TO", textButtonStyle);
		textButton2.setPosition(100 + textButton2.getWidth()/2, 160);
		
		final TextButton textButton3 = new TextButton("UPGRADE", textButtonStyle);
		textButton3.setPosition(100 + textButton3.getWidth()/2, 50);
		
		stage.addActor(intro);
		stage.addActor(textButton);
		stage.addActor(textButton2);
		stage.addActor(textButton3);

		textButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new GameScreen());
			}
		});
		
		textButton3.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new UpgradeScreen());
			}
		});


	}
	
	@Override
	public void update() {

		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		stage.draw();
		sb.end();		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);			
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


}
