package uk.co.greedygull;

import uk.co.greedygull.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * The GUI is built using Scene2D and creates the interface seen within the Game Screen.
 * This handles touch input for movement, shooting and pausing/playing the game.
 * @author Matej
 *
 */
public class GUI {
	
	private Stage stage;
	
	private static int score;
	//private static int fps;
	private final float tempStam = Skills.getSTAMINA();
	private String scoreName;
	//private String fpsCounter;
	private BitmapFont font;
	
	private ButtonStyle ppStyle, leftStyle, rightStyle, shootStyle;
	private Button playPause, left, right, shoot;
	
	private LabelStyle ls;
	private Label scoreLabel;
	
	private ProgressBar staminaBar;
	
	public static boolean paused = false;
	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean goShoot = false;
	
	/**
	 * Initializes the GUI and creates the stage, buttons, labels and progress bar to display on screen
	 */
	public GUI(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		score = 0;
		scoreName = "SCORE: 0";
		//fpsCounter = "FPS: ";
		font = new BitmapFont();
		font.scale(1.5f);
		
		
		//LABEL STYLE & LABEL
		ls = new LabelStyle(font, Color.BLACK);
		
		scoreLabel = new Label(scoreName,ls);
		scoreLabel.setPosition(100, Gdx.graphics.getHeight()-100);
		
		
		//BUTTON STYLES & BUTTONS
		ppStyle = new ButtonStyle();
		ppStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.GUIPAUSE));
		ppStyle.checked = new TextureRegionDrawable(new TextureRegion(Assets.GUIPLAY));
		
		leftStyle = new ButtonStyle();
		leftStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.LEFT));
		
		rightStyle = new ButtonStyle();
		rightStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.RIGHT));
		
		shootStyle = new ButtonStyle();
		shootStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.TARGET));
		
		playPause = new Button(ppStyle);
		playPause.setPosition(10, Gdx.graphics.getHeight()-Assets.GUIPAUSE.getHeight());
		
		left = new Button(leftStyle);
		left.setPosition(0, 0);
		
		right = new Button(rightStyle);
		right.setPosition(Gdx.graphics.getWidth()-Assets.RIGHT.getWidth(), 0);
		
		shoot = new Button(shootStyle);
		shoot.setPosition( Gdx.graphics.getWidth()/2 - Assets.TARGET.getWidth()/2, 0);
		
		//STAMINA BAR
		ProgressBarStyle pbs = new ProgressBarStyle();
		pbs.background = new TextureRegionDrawable(new TextureRegion(Assets.PROGRESSBG));
		pbs.knobBefore = new TextureRegionDrawable(new TextureRegion(Assets.PROGRESS));
		
		staminaBar = new ProgressBar(0,400,1,false,pbs);
		staminaBar.setSize(Gdx.graphics.getWidth() - 100, Assets.PROGRESS.getHeight());
		staminaBar.setPosition(playPause.getWidth()-20, Gdx.graphics.getHeight()-50);
		
		
		stage.addActor(staminaBar);
		stage.addActor(playPause);
		stage.addActor(left);
		stage.addActor(right);
		stage.addActor(shoot);
		stage.addActor(scoreLabel);

		//BUTTON INPUT LISTENERS
		
		playPause.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				paused = true;
				if(playPause.isChecked()){
					paused = false;
				}
			}
		});
		
		left.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				goLeft=true;
				return goLeft;	
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				goLeft=false;					
			}
				
		});
		
		right.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				goRight=true;
				return goRight;	
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				goRight=false;					
			}
				
		});
		
		shoot.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				goShoot=true;
				return goShoot;	
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				goShoot=false;					
			}
				
		});
		
		
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		stage.draw();
		sb.end();
	}

	/**
	 * Within GUI.update() the score and stamina bar are constantly being updated.
	 */
	public void update(){
		scoreLabel.setText("SCORE: " + score);
		staminaBar.setValue((400/tempStam) * Player.getStamina());

//		fps = Gdx.graphics.getFramesPerSecond();
//		fpsCounter = "FPS: " + fps;
	}
	
	/**
	 * As score is private to the GUI any class wishing to increment the score uses addScore(int s)
	 * @param s
	 */
	public static void addScore(int s){
		score = score + s;
	}
	
	/**
	 * The private score variable can only be retrieved through getScore()
	 * @return
	 */
	public static int getScore(){
		return score;
	}
	
	/**
	 * The boolean paused is returned through getPaused(), used to check pause state within the game screen.
	 * @return
	 */
	public static boolean getPaused(){
		return paused;
	}
	
	
}
