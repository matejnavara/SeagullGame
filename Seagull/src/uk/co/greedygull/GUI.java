package uk.co.greedygull;

import uk.co.greedygull.entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUI {
	
	private static int score;
	private static int fps;
	private String scoreName;
	private String staminaName;
	private String fpsCounter;
	private BitmapFont font;
	
	
	
	public GUI(){
		score = 0;
		scoreName = "Score: 0";
		staminaName = "Stamina: ";
		fpsCounter = "FPS: ";
		font = new BitmapFont();
		
	}
	
	public void render(SpriteBatch sb){
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(sb, scoreName, Constants.VIEWPORT_WIDTH - 100, Constants.VIEWPORT_HEIGHT - 50);
		font.draw(sb, staminaName, 20, Constants.VIEWPORT_HEIGHT - 50);
		
		if(fps >= 45){
			font.setColor(0, 1.0f, 0, 1.0f);
		} else if(fps >= 30){
			font.setColor(1.0f, 1.0f, 0, 1.0f);
		} else {
			font.setColor(1.0f, 0, 0, 1.0f);
		}
		font.draw(sb, fpsCounter, 20, 20);
	}

	public void update(){
		scoreName = "Score: " + score;
		staminaName = "Stamina: " + Player.getStamina();
		fps = Gdx.graphics.getFramesPerSecond();
		fpsCounter = "FPS: " + fps;
	}
	
	public static void addScore(int s){
		score = score + s;
	}
	
	public int getScore(){
		return score;
	}
	
	
}
