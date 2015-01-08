package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Basicgame;
import uk.co.greedygull.GUI;
import uk.co.greedygull.Skills;
import uk.co.greedygull.entities.Player;

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
 * This screen is displayed upon the Game Over scenario when the player runs out of stamina. It displays their final score, distance and any skill points gained.
 * It also displays if the score or distance has surpassed the high score.
 * @author Matej
 * 
 */
public class GameoverScreen extends Screen {
	
	Stage stage;
	Skin skin;
	
	LabelStyle ls, ls2;
	TextButtonStyle tbs;
	
	Label highscoreLabel, bestdistanceLabel, scoreLabel, distanceLabel, skillLabel;

	private int score;
	private int distance;
	private int skillPoints;
	
	/**
	 * Creates the stage, labels and buttons for the gameover screen
	 */
	@Override
	public void create() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		Assets.GULL2.play();
		
		Texture goTex = Assets.GAMEOVER;
		Image go = new Image(goTex);
		go.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		
		//FONT
		BitmapFont font = new BitmapFont();
		font.scale(1);
		BitmapFont fontBig = new BitmapFont();
		fontBig.scale(2);
		skin.add("defaultFont", font);
		skin.add("bigFont", fontBig);
		
		//LABEL STYLE
		ls = new LabelStyle(fontBig, Color.WHITE);
		ls2 = new LabelStyle(font, Color.CYAN);
		
		//TEXT BUTTON STYLE
		tbs = new TextButtonStyle();
		tbs.up = new TextureRegionDrawable(new TextureRegion(Assets.BUTTON));
		tbs.font = skin.getFont("defaultFont");
		
		
		
		//SCORE AND SKILL POINTS
		score = GUI.getScore();
		distance = Player.getDistance();
		
		skillPoints = score/500;
		Skills.addSKILLPOINTS(skillPoints);		
		
		String finalSkill = "GAINED: " + skillPoints + " skill points";
		String finalScore = "SCORE: " + score;
		String finalDistance = "DISTANCE: " + distance + "m";
		
		skillLabel = new Label(finalSkill, ls);
		skillLabel.setPosition(50, 120);
		
		scoreLabel = new Label(finalScore, ls);
		scoreLabel.setPosition(60, Gdx.graphics.getHeight() - 150);
		
		distanceLabel = new Label(finalDistance, ls);
		distanceLabel.setPosition(60,Gdx.graphics.getHeight() - 230);
		
		
			
		//HIGHSCORES		
		highscoreLabel = new Label(" ", ls2);
		highscoreLabel.setPosition(230, Gdx.graphics.getHeight()-300);
		
		bestdistanceLabel = new Label(" ", ls2);
		bestdistanceLabel.setPosition(230, Gdx.graphics.getHeight() - 350);
		
		if(score > Skills.getHighscore()){
			highscoreLabel.setText("BEST SCORE");
			Skills.setHighscore(score);
			Basicgame.prefs.putInteger("bestscore", score);
		} 
		
		if(distance > Skills.getBestDistance()){
			bestdistanceLabel.setText("BEST DISTANCE");
			Skills.setBestDistance(distance);
			Basicgame.prefs.putInteger("bestdistance", distance);
		}
		Basicgame.prefs.flush();

		
		//BUTTONS
		final TextButton playAgain = new TextButton("PLAY AGAIN",tbs);
		playAgain.setPosition(30, 20);
		
		final TextButton upgrade = new TextButton("UPGRADE",tbs);
		upgrade.setPosition(Gdx.graphics.getWidth() - upgrade.getWidth() - 30, 20);
		
		stage.addActor(go);
		stage.addActor(scoreLabel);
		stage.addActor(distanceLabel);
		stage.addActor(skillLabel);
		stage.addActor(playAgain);
		stage.addActor(highscoreLabel);
		stage.addActor(bestdistanceLabel);
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
		skin.dispose();
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
