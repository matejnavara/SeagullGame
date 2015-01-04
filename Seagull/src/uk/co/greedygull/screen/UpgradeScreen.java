package uk.co.greedygull.screen;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class UpgradeScreen extends Screen{

	private Stage stage;
	private Skin skin;
	
	private Table table;
	
	private LabelStyle ls, ls2;
	private Label skillLabel, staminaLabel, frLabel, speedLabel;

	private TextButtonStyle tbs, tbs1;
	private TextButton stamUp, firerateUp, speedUp, playAgain, mainMenu;

	int skillPoints;
	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		
		skillPoints = Skills.getSKILLPOINTS();
		
		//PIXMAP
		Pixmap pixmap0 = new Pixmap(200, 100, Format.RGBA8888);
		pixmap0.setColor(Color.GREEN);
		pixmap0.fill();
		skin.add("black", new Texture(pixmap0));
		
		Pixmap pixmap1 = new Pixmap(50, 50, Format.RGBA8888);
		pixmap1.setColor(Color.GREEN);
		pixmap1.fill();
		skin.add("white", new Texture(pixmap1));
		
		//FONT
		BitmapFont font = new BitmapFont();
		font.scale(1);
		BitmapFont fontBig = new BitmapFont();
		fontBig.scale(2);
		skin.add("defaultFont", font);
		skin.add("bigFont", fontBig);
		
		//LABEL STYLE
		ls = new LabelStyle(fontBig, Color.BLACK);
		ls2 = new LabelStyle(font, Color.BLACK);
		
		//TEXTBUTTON STYLE
		tbs = new TextButtonStyle();
		tbs.up = skin.newDrawable("black", Color.BLUE);
		tbs.font = skin.getFont("defaultFont");
		
		tbs1 = new TextButtonStyle();
		tbs1.up = skin.newDrawable("white", Color.BLUE);
		tbs1.font = skin.getFont("defaultFont");
		
		//LABELS
		String finalSkill = "SKILL POINTS: " + skillPoints;	
		skillLabel = new Label(finalSkill, ls);
		skillLabel.setPosition(50, 200);
		
		String stamina = "   Stamina   ";	
		staminaLabel = new Label(stamina, ls2);
		staminaLabel.setPosition(50, 300);
		
		String firerate = "   Fire Rate   ";	
		frLabel = new Label(firerate, ls2);
		frLabel.setPosition(200, 300);
		
		String speed = "   Speed   ";	
		speedLabel = new Label(speed, ls2);
		speedLabel.setPosition(350, 300);
		
		
		//BUTTONS
		playAgain = new TextButton("PLAY AGAIN",tbs);
		playAgain.setPosition(50, 50);
		
		mainMenu = new TextButton("MAIN MENU",tbs);
		mainMenu.setPosition(260, 50);
		
		stamUp = new TextButton("+",tbs1);
		//stamUp.setPosition(50, 350);
		
		firerateUp = new TextButton("+",tbs1);
		//firerateUp.setPosition(200, 350);
		
		speedUp = new TextButton("+",tbs1);
		//speedUp.setPosition(350, 350);
		
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.add(stamUp);
		table.add(firerateUp);
		table.add(speedUp);
		table.row();
		table.add(staminaLabel);
		table.add(frLabel);
		table.add(speedLabel);
		table.debug();
		
//		stage.addActor(stamUp);
//		stage.addActor(firerateUp);
//		stage.addActor(speedUp);
		
		stage.addActor(skillLabel);
//		stage.addActor(staminaLabel);
//		stage.addActor(frLabel);
//		stage.addActor(speedLabel);
		
		stage.addActor(table);
		stage.addActor(playAgain);
		stage.addActor(mainMenu);
			
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
				if(skillPoints > 0){
					Skills.addSTAMINA(100);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					System.out.println("Stamina added");
				}
				
			}
		});
		
		firerateUp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skillPoints > 0){
					Skills.addFIRERATE(50);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					System.out.println("FireRate added");
				}
				
			}
		});
		
		speedUp.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(skillPoints > 0){
					Skills.addMOVEMENTSPEED(50);
					skillPoints--;
					Skills.addSKILLPOINTS(-1);
					updateSkillPoints();
					System.out.println("MovementSpeed added");
				}
				
			}
		});
		//ProgressBar pb = new ProgressBar();
		
		
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
	
	private void updateSkillPoints(){
		skillLabel.setText("SKILL POINTS: " + skillPoints);
	}

}
