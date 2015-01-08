package uk.co.greedygull.screen;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Basicgame;
import uk.co.greedygull.Skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *  
 *  The upgrade screen is built using Scene2D, adding buttons, labels and progress bars to the stage. Skills are based on the persistent stats of the player.
 * @author Matej
 * 
 */

public class UpgradeScreen extends Screen{

	private Stage stage;
	private Skin skin;
	
	private TextureRegion progress;
	private TextureRegion progressBg;
	
	private Table table;
	
	private LabelStyle ls, ls2;
	private Label skillLabel, staminaMax, frMax, speedMax;
	
	private ProgressBar stamBar;
	private ProgressBar frBar;
	private ProgressBar speedBar;
	
	BitmapFont font;
	BitmapFont fontBig;

	private TextButtonStyle tbs; 
	private TextButton playAgain, mainMenu;
	private Button stamUp, firerateUp, speedUp;

	int skillPoints;
	
	
	@Override
	public void create() {
		stage = new Stage();	
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		skillPoints = Skills.getSKILLPOINTS();
		
		Texture upgradeTex = Assets.UPGRADE;
		Image bg = new Image(upgradeTex);
		bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//FONT
		font = new BitmapFont();
		font.scale(1);

		fontBig = new BitmapFont();
		fontBig.scale(2);
		skin.add("defaultFont", font);
		skin.add("bigFont", fontBig);
		
		//LABEL STYLE
		ls = new LabelStyle(fontBig, Color.BLACK);
		ls2 = new LabelStyle(font, Color.BLACK);
		
		//LABELS
		String finalSkill = "SKILL POINTS: " + skillPoints;	
		skillLabel = new Label(finalSkill, ls);
		
		
		staminaMax = new Label(" ", ls2);
		frMax = new Label(" ", ls2);
		speedMax = new Label(" ", ls2);
		checkMax();
		
		//TEXTBUTTON STYLE
		tbs = new TextButtonStyle();
		tbs.up = new TextureRegionDrawable(new TextureRegion(Assets.BUTTON));
		tbs.down = new TextureRegionDrawable(new TextureRegion(Assets.BUTTONdwn1));
		tbs.font = skin.getFont("defaultFont");
	
		//BUTTONS
		playAgain = new TextButton("PLAY",tbs);
		playAgain.setPosition(30, 50);
		
		mainMenu = new TextButton("MAIN MENU",tbs);
		mainMenu.setPosition(Gdx.graphics.getWidth()- mainMenu.getWidth() - 30, 50);
		
		//stamUp = new TextButton("+",tbs1);		
		stamUp = new Button(new TextureRegionDrawable(new TextureRegion(Assets.UPGRADEstam)));
		firerateUp = new Button(new TextureRegionDrawable(new TextureRegion(Assets.UPGRADEfr)));		
		speedUp = new Button(new TextureRegionDrawable(new TextureRegion(Assets.UPGRADEspeed)));

		
		//PROGRESS BAR STYLE
		ProgressBarStyle pbs = new ProgressBarStyle();
		progress = new TextureRegion(Assets.PROGRESS);
		progressBg = new TextureRegion(Assets.PROGRESSBG);
		pbs.background = new TextureRegionDrawable(progressBg);
		pbs.knobBefore = new TextureRegionDrawable(progress);
		
		//PROGRESS BAR
		stamBar = new ProgressBar(0,200,10,true,pbs);
		stamBar.setValue((stamBar.getMaxValue()/(Skills.maxStamina-Skills.minStamina))*(Skills.getSTAMINA()-Skills.minStamina));
		
		frBar = new ProgressBar(10,200,10,true,pbs);
		frBar.setValue((frBar.getMaxValue()/(Skills.maxFirerate-Skills.minFirerate))*(Skills.getFIRERATE()-Skills.minFirerate));
		
		speedBar = new ProgressBar(10,200,10,true,pbs);
		speedBar.setValue((speedBar.getMaxValue()/(Skills.maxSpeed-Skills.minSpeed))*(Skills.getMOVEMENTSPEED()-Skills.minSpeed));
		
				
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.add(skillLabel).colspan(3);
		table.row();
		table.add(staminaMax);
		table.add(frMax);
		table.add(speedMax).height(speedMax.getHeight());
		table.row();
		table.add(stamBar);
		table.add(frBar);
		table.add(speedBar);
		table.row();
		table.add(stamUp);
		table.add(firerateUp);
		table.add(speedUp);
		table.row();
		
//		table.debug();
		
		stage.addActor(bg);
		stage.addActor(table);
		stage.addActor(playAgain);
		stage.addActor(mainMenu);
		
		//INPUT LISTENERS
		
		playAgain.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new GameScreen());
			}
		});
		
		mainMenu.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.setScreen(new MenuScreen());
			}
		});
		
		stamUp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skillPoints > 0 && Skills.getSTAMINA() < Skills.maxStamina){
					Skills.addSTAMINA(100);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					stamBar.setValue(stamBar.getValue()+10);
					Basicgame.prefs.putInteger("stamina", Skills.getSTAMINA());
					System.out.println("Stamina is now: " + Skills.getSTAMINA());
				}else if(Skills.getSTAMINA() == Skills.maxStamina){
					staminaMax.setText("MAX");
				}
			}
		});
		
		firerateUp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skillPoints > 0 && Skills.getFIRERATE() > Skills.maxFirerate){
					Skills.addFIRERATE(12.5f);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					frBar.setValue(frBar.getValue()+10);
					Basicgame.prefs.putFloat("firerate", Skills.getFIRERATE());
					System.out.println("FireRate is now: " + Skills.getFIRERATE());
				} else if(Skills.getFIRERATE() == Skills.maxFirerate){
					frMax.setText("MAX");
				}
				
			}
		});
		
		speedUp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skillPoints > 0 && Skills.getMOVEMENTSPEED() < 750){
					Skills.addMOVEMENTSPEED(25);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					speedBar.setValue(speedBar.getValue()+10);
					Basicgame.prefs.putInteger("speed", Skills.getMOVEMENTSPEED());
					System.out.println("MovementSpeed is now: " + Skills.getMOVEMENTSPEED());
				} else if(Skills.getMOVEMENTSPEED() == Skills.maxSpeed){
					speedMax.setText("MAX");
				}
			}
		});
		
		
		
		Basicgame.prefs.flush();
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Updates skill points upon user selecting to upgrade a stat
	 */
	private void updateSkillPoints(){
		skillLabel.setText("SKILL POINTS: " + skillPoints);
	}
	
	/**
	 * Checks for max stats upon create to amend the labels
	 */
	private void checkMax(){
		if(Skills.getSTAMINA() == Skills.maxStamina)
			staminaMax.setText("MAX");
		if(Skills.getFIRERATE() == Skills.maxFirerate)
			frMax.setText("MAX");
		if(Skills.getMOVEMENTSPEED() == Skills.maxSpeed)
			speedMax.setText("MAX");
	}

}
