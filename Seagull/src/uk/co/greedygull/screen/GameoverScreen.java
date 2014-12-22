package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Basicgame;
import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverScreen extends Screen {
	

	Texture go = Assets.GAMEOVER;
	OrthographicCamera camera;
	
	@Override
	public void create() {
		
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
