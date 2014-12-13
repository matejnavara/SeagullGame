package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Basicgame;
import uk.co.greedygull.Constants;
import uk.co.greedygull.GUI;
import uk.co.greedygull.util.CameraHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	public final Player player;
	
	private int maxTarget = 8;
	private int maxFood = 2;
	
	private long lastSpawn;
	private float scrollSpeed;

	
	public EntityManager(){

		//Initiate Player
		player = new Player(new Vector2(Assets.MAP.getWidth()/2, 250),
				new Vector2(Assets.PLAYER.getWidth(),Assets.PLAYER.getHeight()), 
				new Vector2(0,0), 
				this);
					
		spawnEntities();
	}
	
	public void update(){
		
		spawnEntities();
		for(Entity e : entities){
			e.update();
		}
		for(Ammo a : getAmmo()){
			if(a.checkEnd())
				entities.removeValue(a, false);
		}
		player.update();
		checkCollisions();
		checkFood();
	}
	
	public void render(SpriteBatch sb){
		for(Entity e : entities){
			e.render(sb);
		}
		player.render(sb);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void spawnEntities(){
		if(entities.size < 2){
			scrollSpeed = Constants.MAP_SPEED;
			System.out.println("Spawning Entities");
		//Initiate Targets
				for(int i = 0; i < maxTarget; i++){
					float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
					float y = (Constants.VIEWPORT_HEIGHT + MathUtils.random(i*100, i*200));
					addEntity(new Target(Assets.TARGET,new Vector2(x,y),new Vector2(0,-scrollSpeed),false));			
				}
				
				//Initiate Food
				for(int i = 0; i < maxFood/2; i++){
					float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
					float y = MathUtils.random(Constants.VIEWPORT_HEIGHT, (Constants.VIEWPORT_HEIGHT)*2);
					addEntity(new Food(new Vector2(x,y),new Vector2(0,-scrollSpeed)));
				}
		}
	}
	
	private void checkCollisions(){
		for(Target t : getTargets()){
			for(Ammo a : getAmmo()){
				if(a.pos.y < 100){
				if(t.getBounds().contains(a.getBounds())){
					
					t.texture = Assets.TARGETHIT;
					t.hit = true;
								
					entities.removeValue(a, false);
					GUI.addScore(t.getScore());
					System.out.println("HIT");
				}
				}
				if(t.hit && t.pos.y < 0 - t.getHeight()){
					entities.removeValue(t, false);
					System.out.println("HIT REMOVED");
				}
			}
		}
	}
	
	private void checkFood(){
		for(Food f : getFood()){
			if(f.getBounds().overlaps(player.getBounds())){
				player.swoop();
				if(player.scale.y < 11){
				player.addStamina(f.getScore());
				entities.removeValue(f, false);
				System.out.println("YUM");
				;
				}
			}
		}
	}
	
	//Retrieve Target entities from Entity array
	private Array<Target> getTargets(){
		Array<Target> trgt = new Array<Target>();
		for(Entity e : entities){
			if(e instanceof Target){
				trgt.add((Target) e);
			}
		}
		return trgt;
	}
	
	//Retrieve Ammo entities from Entity array
	private Array<Ammo> getAmmo(){
		Array<Ammo> ammo = new Array<Ammo>();
		for(Entity e : entities){
			if(e instanceof Ammo){
				ammo.add((Ammo) e);
			}
		}
		return ammo;
	}
	
	//Retrieve Food entities from Entity array
	private Array<Food> getFood(){
		Array<Food> food = new Array<Food>();
		for(Entity e : entities){
			if(e instanceof Food){
				food.add((Food) e);
			}
		}
		return food;
	}

}
