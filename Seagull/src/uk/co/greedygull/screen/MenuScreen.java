package uk.co.greedygull.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends Screen{
	
	Skin skin;
	Stage stage;

	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		BitmapFont bfont = new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
		
		
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.RED);
		
		textButtonStyle.font = skin.getFont("default");
		
		final TextButton textButton = new TextButton("PLAY",textButtonStyle);
		textButton.setPosition(200, 200);
		stage.addActor(textButton);
		stage.addActor(textButton);
		stage.addActor(textButton);

		textButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				textButton.setText("Bombs Away!");
				ScreenManager.setScreen(new GameScreen());
			}
		});
//		textButton.addListener(new ChangeListener(){
//			public void changed(ChangeEvent event, Actor actor){
//				textButton.setText("Bombs Away!");
//				ScreenManager.setScreen(new GameScreen());
//			}
//		}
//		);

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
