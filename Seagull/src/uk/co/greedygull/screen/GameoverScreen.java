package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.GUI;
import uk.co.greedygull.Skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameoverScreen extends Screen {
	
	Stage stage;
	Skin skin;

	private int score;
	private int skillPoints;
	
	
	@Override
	public void create() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		//PIXMAP
		Pixmap pixmap = new Pixmap(200, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		//FONT
		BitmapFont font = new BitmapFont();
		font.scale(1);
		BitmapFont fontBig = new BitmapFont();
		fontBig.scale(2);
		skin.add("defaultFont", font);
		skin.add("bigFont", fontBig);
		
		//LABEL STYLE
		LabelStyle ls = new LabelStyle(fontBig, Color.WHITE);
		
		//TEXT BUTTON STYLE
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.up = skin.newDrawable("white", Color.BLUE);
		tbs.font = skin.getFont("defaultFont");
		
		
		Texture goTex = Assets.GAMEOVER;
		Image go = new Image(goTex);
		go.setPosition(0, 0);
		
		
		//SCORE AND SKILL POINTS
		score = GUI.getScore();
		
		skillPoints = score/1000;
		Skills.addSKILLPOINTS(skillPoints);		
		//int skill = Skills.getSKILLPOINTS();
		
		String finalSkill = "GAINED: " + skillPoints + " skill points";
		String finalScore = "SCORE: " + score;
		
		Label skillLabel = new Label(finalSkill, ls);
		skillLabel.setPosition(50, 200);
		
		Label scoreLabel = new Label(finalScore, ls);
		scoreLabel.setPosition(50, 300);
		
		//BUTTONS
		final TextButton playAgain = new TextButton("PLAY AGAIN",tbs);
		playAgain.setPosition(50, 50);
		
		final TextButton upgrade = new TextButton("UPGRADE",tbs);
		upgrade.setPosition(260, 50);
		
		stage.addActor(go);
		stage.addActor(scoreLabel);
		stage.addActor(skillLabel);
		stage.addActor(playAgain);
		stage.addActor(upgrade);
		
		playAgain.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new GameScreen());
			}
		});
		
		upgrade.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new UpgradeScreen());
			}
		});
	}

	@Override
	public void update() {

		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			ScreenManager.setScreen(new GameScreen());
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACKSPACE)){
			ScreenManager.setScreen(new MenuScreen());
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		stage.draw();
		sb.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
