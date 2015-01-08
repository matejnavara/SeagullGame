package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;
import uk.co.greedygull.GUI;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * The entity manager handles the spawning of the player and all entities as well as all collision between player/entity.
 * @author Matej
 *
 */

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	public final Player player;
	
	private int maxTarget = 7;
	private int maxFood = 2;
	private int maxEnemy = 2;
	
	public boolean playerHit = false;
	
	private float scrollSpeed;

	
	public EntityManager(){

		//Initiate Player
		player = new Player(new Vector2(Assets.MAP.getWidth()/2, 250),
				new Vector2(Assets.PLAYER.getWidth(),Assets.PLAYER.getHeight()), 
				new Vector2(0,0), 
				this);
					
		spawnEntities();
	}
	
	/**
	 * Update calls all the entity updates, checks for out of bounds entities and for collisions
	 */
	public void update(){
		
		spawnEntities();
		for(Entity e : entities){
			e.update();
		}
		
		for(Ammo a : getAmmo()){
			if(a.checkEnd())
				entities.removeValue(a, false);
		}
		for(Enemy e : getEnemies()){
			if(e.checkEnd())
				entities.removeValue(e, false);
		}
		for(Target t : getTargets()){
			if(t.checkEnd())
				entities.removeValue(t, false);
		}
		
		player.update();
		player.shadow.update();
		checkCollisions();
	}
	
	/**
	 * Uses sprite batch to render all entities, player/playershadow and a player hit marker
	 * @param sb
	 */
	public void render(SpriteBatch sb){
		for(Entity e : entities){
			e.render(sb);
		}
		player.render(sb);
		player.shadow.render(sb);

		if(playerHit){
			sb.draw(Assets.HITMARKER, player.pos.x, player.pos.y);
		}
		
		
	}
	
	/**
	 * Adds the set entity into the entities array {@link #entities}
	 * @param entity
	 */
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	/**
	 * The core logic behind spawning all targets, food and enemies.
	 * Each entity will be randomly spawned until its max amount defined by {@link #maxEnemy}{@link #maxFood}{@link #maxTarget}
	 */
	public void spawnEntities(){
		if(entities.size < 2){
			scrollSpeed = Constants.MAP_SPEED;
			System.out.println("Spawning Entities");
				//Initiate Targets
				for(int i = 0; i < maxTarget; i++){
					
					int rand = MathUtils.random(Assets.targetsTex.length-1);
					Texture Tex = Assets.targetsTex[rand];	
					
					float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Tex.getWidth());
					float y = (Constants.VIEWPORT_HEIGHT + (i*200)+ MathUtils.random(10, 130));
					addEntity(new Target(Tex,new Vector2(x,y),new Vector2(0,-scrollSpeed),false));			
				}
				
				//Initiate Food
				for(int i = 0; i < maxFood/2; i++){
					float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.FOOD.getWidth());
					float y = MathUtils.random(Constants.VIEWPORT_HEIGHT, (Constants.VIEWPORT_HEIGHT)*2);
					addEntity(new Food(new Vector2(x,y),new Vector2(0,-scrollSpeed)));
				}
				
				//Initiate Enemies
				for(int i = 0; i < maxEnemy; i++){
					float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.ENEMY.getWidth());
					float y = MathUtils.random(Constants.VIEWPORT_HEIGHT, (Constants.VIEWPORT_HEIGHT)*2);
					addEntity(new Enemy(new Vector2(x,y),new Vector2(0,-scrollSpeed*2)));
				}
			}
		
			
		}
	
	/**
	 * The core logic of collision between entities. Each 
	 */
	private void checkCollisions(){
		//COLLISION FOR ENEMIES
		for(Enemy e : getEnemies()){
			if(e.getBounds().overlaps(player.getBounds())){
				playerHit = true;
				if(e.canAttack()){
					player.addStamina(-(e.getAttack()));
					e.attack();
					Assets.HAWK.play();
				}
	
			} else {
				
				playerHit = false;
			}
		}
		
		//COLLISON FOR TARGETS
		for(Target t : getTargets()){
			for(Ammo a : getAmmo()){
				
				if(a.pos.y < 150){
				if(t.getBounds().contains(a.getBounds())){
					
					for(int i=0; i<Assets.targetsTex.length; i++ ){
						if(t.texture == Assets.targetsTex[i])
							t.texture = Assets.targetsTexHit[i];	
					}				
					t.hit = true;
								
					entities.removeValue(a, false);
					GUI.addScore(t.getScore());
					System.out.println("HIT");
					Assets.GULL1.play(0.5f);
				}
				}
				if(a.pos.y < 100){
					a.texture = Assets.SPLAT;
					a.scale = new Vector2(Assets.SPLAT.getWidth(),Assets.SPLAT.getHeight());
					a.direction = new Vector2(0,-scrollSpeed);
				}
					
				if(t.hit && t.checkEnd()){
					entities.removeValue(t, false);
					System.out.println("HIT REMOVED");
				}
			}
		}
		
		//COLLISION FOR FOOD
		for(Food f : getFood()){
			if(f.getBounds().overlaps(player.getBounds()) && player.hasStamina()){
				player.swoop();
				if(player.scale.y < 14){
				player.addStamina(f.getScore());
				entities.removeValue(f, false);
				System.out.println("YUM");
				;
				}
			}
		}
		
		
		
	}

	
	/**
	 * Retrieve Target entities from Entity array and create a private Target array
	 * @return
	 */
	private Array<Target> getTargets(){
		Array<Target> trgt = new Array<Target>();
		for(Entity e : entities){
			if(e instanceof Target){
				trgt.add((Target) e);
			}
		}
		return trgt;
	}
	
	/**
	 * Retrieve Ammo entities from Entity array and create a private Ammo array
	 * @return
	 */
	private Array<Ammo> getAmmo(){
		Array<Ammo> ammo = new Array<Ammo>();
		for(Entity e : entities){
			if(e instanceof Ammo){
				ammo.add((Ammo) e);
			}
		}
		return ammo;
	}
	
	/**
	 * Retrieve Food entities from Entity array and create a private Food array
	 * @return
	 */
	private Array<Food> getFood(){
		Array<Food> food = new Array<Food>();
		for(Entity e : entities){
			if(e instanceof Food){
				food.add((Food) e);
			}
		}
		return food;
	}
	
	/**
	 * Retrieve Enemy entities from Entity array and create a private Enemy array
	 * @return
	 */
	private Array<Enemy> getEnemies(){
		Array<Enemy> enemy = new Array<Enemy>();
		for(Entity e : entities){
			if(e instanceof Enemy){
				enemy.add((Enemy) e);
			}
		}
		return enemy;
	}

}
