package uk.co.greedygull.entities;


import uk.co.greedygull.Assets;
import uk.co.greedygull.GUI;
import uk.co.greedygull.Skills;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

/**
 * Creates the player
 * @author Matej
 *
 */
public class Player extends Entity{
	
	private EntityManager entityManager;
	public PlayerShadow shadow;
	public boolean canSwoop;
	private boolean canShoot;
	private boolean hasStamina;
	
	private long lastFire;
	
	private long lastAnim;
	private static int currentAnim = 0;
	
	public static boolean gameOver;
	
	private static int distance;
	private static int stamina;
	private static float fireRate;
	private static int movement;

	/**
	 * Constructs the player with position, scale, direction and the entity manager. Initialises stats from @Skills and sets various boolean variables used in game operations.
	 * @param pos
	 * @param scale
	 * @param direction
	 * @param entityManager
	 */
	public Player(Vector2 pos, Vector2 scale, Vector2 direction, EntityManager entityManager) {
		super(Assets.playerAnim[currentAnim], pos, scale, direction);
		shadow = new PlayerShadow(new Vector2(pos.x +40,pos.y +100),direction);
		this.entityManager = entityManager;
		
		stamina = Skills.getSTAMINA();
		fireRate = Skills.getFIRERATE();
		movement = Skills.getMOVEMENTSPEED();
		canShoot = true;
		canSwoop = false;
		hasStamina = true;
		gameOver = false;
		distance = 0;
	}
	
	/**
	 * The player's update method handles animation, movement, shooting and swooping using many of the boolean variables declared.
	 */
	@Override
	public void update() {
		stamina--;
		distance++;
		
		//FLYING ANIMATION
		if(!canSwoop){
			if(System.currentTimeMillis() - lastAnim >= 200){	
				if(currentAnim < 2){
					currentAnim++;
				} else {
					currentAnim = 0;
				}
				lastAnim = System.currentTimeMillis();		
				}
			}
			this.texture = Assets.playerAnim[currentAnim];
			
		
		//MOVEMENT CONTROLS	
		if(stamina > 0){
			hasStamina = true;
		if((pos.x > 0) && 
				(GUI.goLeft || (Gdx.input.isKeyPressed(Keys.LEFT)))){
			moveLeft();
		
		} else if((pos.x +Assets.PLAYER.getWidth() < Assets.MAP.getWidth()) && 
				(GUI.goRight || (Gdx.input.isKeyPressed(Keys.RIGHT)))){
			moveRight();
		
		} else
			setDirection(0,0);
		
		pos.add(direction);
		shadow.pos.add(direction);
		
		}
	
		
		if(stamina <= 0){
			System.out.println("OUT OF STAMINA");
			hasStamina = false;
			canShoot = false;
			setDirection(0, -100);
			pos.add(direction);
			shadow.pos.add(direction);
			if(pos.y < 0 - Assets.PLAYER.getHeight()){
				gameOver = true;
			}
		}
	
		
		//SHOOTING ANIMATION
		if(GUI.goShoot || Gdx.input.isKeyPressed(Keys.SPACE)){
			this.shoot();
			}
		
		//SWOOPING ANIMATION
		//Swooping in
		if(canSwoop){
			if(scale.y > 11){
				scale.add(-1, -1);
				shadow.pos.add(0, -5);
				shadow.scale.scl(0.9f);

			} else {
				canSwoop = false;
				shadow.canSwoop = false;
			}
		}
		//Swooping out
		if(!canSwoop){
			 if(scale.y < Assets.PLAYER.getHeight()+1)
				scale.add(1, 1);
				
			 if(shadow.scale.y < Assets.PLAYERSHADOW.getHeight()+1){
				shadow.scale.scl(1.1f);
			 }
			if(shadow.pos.y < pos.y + 151){
				shadow.pos.add(0, 5);
			 }
				canShoot = true;
		}
				
		}
	
	/**
	 * Sets player direction left
	 */
	public void moveLeft(){
		if(pos.x > 0){
			setDirection(-movement, 0);
		
		} 
		
	}
	
	/**
	 * Sets player direction right
	 */
	public void moveRight(){
		if(pos.x +Assets.PLAYER.getWidth() < Assets.MAP.getWidth()){
			setDirection(movement, 0);
		
		} 
	}
	
	/**
	 * Returns the private {@link #hasStamina} boolean variable
	 * @return
	 */
	public boolean hasStamina(){
		return hasStamina;
	}
	
	/**
	 * Returns the private {@link #gameOver} boolean variable
	 * @return
	 */
	public static boolean isGameover(){
		return gameOver;
	}
	

	public static int getStamina(){
		return stamina;
	}
	
	public static int getDistance(){
		return distance;
	}
	
	/**
	 * Adds the parameter to player's stamina up to the maximum of their stamina stat
	 * @param s
	 */
	public void addStamina(int s){
		stamina = stamina + s;
		if(stamina > Skills.getSTAMINA()){
			stamina = Skills.getSTAMINA();
		}
	}
	
	/**
	 * Checks time between the last shot fired and the player's fire rate stat to fire accordingly
	 */
	public void shoot(){
		if(canShoot){
			if(System.currentTimeMillis() - lastFire >= fireRate){
				entityManager.addEntity(new Ammo(pos.cpy().add(Assets.PLAYER.getWidth()/2,0)));
				lastFire = System.currentTimeMillis();
			}		
		}
	}
	
	/**
	 * Sets the swoop animation in motion which is processed within {@link #update()}
	 */
	public void swoop(){
		canShoot = false;
		canSwoop = true;
		shadow.canSwoop = true;
	}
	
	
	
}
