package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 
 * @author Matej
 *
 * This is the entry point for the game, and creates a menu screen with buttons to progress to the game screen and upgrade screen.
 * An additional button displays the how to play dialog.
 */
public class MenuScreen extends Screen{
	
	Skin skin;
	Stage stage;
	
	TextButtonStyle textButtonStyle;
	
	Texture button1;
	
	TextButton playBtn, upgradeBtn, howtoBtn;
	
	LabelStyle labelStyle;
	Label highscoreLabel;
	
	Image howTo;

	/**
	 * Creates the screen with buttons, labels and the input listeners.
	 */
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		Skills.checkSkills();
		
		Assets.WAVES1.loop(0.5f);
		
		BitmapFont bfont = new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
		
		
		Texture introTex = Assets.INTRO;
		Image intro = new Image(introTex);
		intro.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		labelStyle = new LabelStyle(bfont, Color.BLACK);
		
		String highScore = "HIGHSCORE: " + Skills.getHighscore();
		highscoreLabel = new Label(highScore, labelStyle);
		highscoreLabel.setPosition(10, Gdx.graphics.getHeight()-highscoreLabel.getHeight());
		
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.BUTTON));
		textButtonStyle.font = skin.getFont("default");
		
		playBtn = new TextButton("PLAY",textButtonStyle);
		playBtn.setPosition(Gdx.graphics.getWidth()/2 - playBtn.getWidth() - 20, 380);
		
		upgradeBtn = new TextButton("EVOLVE", textButtonStyle);
		upgradeBtn.setPosition(Gdx.graphics.getWidth() - upgradeBtn.getWidth() - 20, 380);
		
		howtoBtn = new TextButton("HOW TO", textButtonStyle);
		howtoBtn.setPosition(Gdx.graphics.getWidth() - howtoBtn.getWidth() - 40, 10);
		
		howTo = new Image(Assets.HOWTO);
		howTo.setPosition(0, 0);
		howTo.setVisible(false);
		
		
		stage.addActor(intro);
		stage.addActor(highscoreLabel);
		stage.addActor(playBtn);
		stage.addActor(upgradeBtn);
		stage.addActor(howtoBtn);
		stage.addActor(howTo);

		playBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new GameScreen());
			}
		});
		
		upgradeBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new UpgradeScreen());
			}
		});
		
		howtoBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				howTo.setVisible(true);
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
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void dispose() {
		stage.dispose();
		//skin.dispose();
		
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
