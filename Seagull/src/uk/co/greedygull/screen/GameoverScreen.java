package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Basicgame;
import uk.co.greedygull.Constants;
import uk.co.greedygull.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverScreen extends Screen {
	

	Texture go = Assets.GAMEOVER;
	OrthographicCamera camera;
	BitmapFont font;
	private String finalScore;
	private int score;
	
	@Override
	public void create() {
		score = GUI.getScore();
		finalScore = "SCORE: " + score;
		font = new BitmapFont();
		font.scale(2);
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
		sb.draw(go, Basicgame.getCam().position.x - Basicgame.getCam().viewportWidth/2, 0);
		font.draw(sb, finalScore, 100, 100);
		sb.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
