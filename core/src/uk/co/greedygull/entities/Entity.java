package uk.co.greedygull.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class is inherited by all other entities and defines the constructors,render method and operations.
 * @author Matej
 *
 */
public abstract class Entity {
	
	protected Texture texture;
	protected Vector2 pos, direction, scale;
	protected Rectangle bounds;
	
	/**
	 * An entity must comprise of these variables.
	 * @param texture
	 * @param pos
	 * @param scale
	 * @param direction
	 */
	public Entity(Texture texture, Vector2 pos, Vector2 scale, Vector2 direction){
		
		this.texture = texture;
		this.pos = pos;
		this.scale = scale;
		this.direction = direction;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch sb){
		sb.draw(texture, pos.x, pos.y, scale.x, scale.y);
	}
	
	/**
	 * Returns the protected vector2 Position {@link #pos}
	 * @return
	 */
	public Vector2 getPosition(){
		return pos;
	}
	
	/**
	 * Returns the protected vector2 Direction {@link #direction}
	 * @return
	 */
	public Vector2 getDirection(){
		return direction;
	}
	
	/**
	 * Sets the direction based on x, y inputs
	 * @param x
	 * @param y
	 */
	public void setDirection(float x, float y){
		direction.set(x,y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}
	
	/**
	 * Sets the scale based on x, y inputs
	 * @param x
	 * @param y
	 */
	public void setScale(float x, float y){
		scale.set(x, y);
		scale.scl(Gdx.graphics.getDeltaTime());
	}
	
	/**
	 * Returns the width of the entity based on its texture width
	 * @return
	 */
	public float getWidth(){
		return this.texture.getWidth();			
		}
	
	/**
	 * Returns the height of the entity based on its texture height
	 * @return
	 */
	public float getHeight(){
		return this.texture.getHeight();			
		}
	
	/**
	 * Creates and returns a rectangle bounding box used for collision detection.
	 * @return
	 */
	public Rectangle getBounds(){
		return new Rectangle(pos.x,pos.y,texture.getWidth(),texture.getHeight());
	}
	
}
