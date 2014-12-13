package uk.co.greedygull.util;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;
import uk.co.greedygull.entities.Player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {
	
	private static final String TAG = CameraHelper.class.getName();
	
	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	
	private Vector2 position;
	private float zoom;
	private Player target;
	 
	public CameraHelper () {
		position = new Vector2();
		zoom = 1.0f;
	}
	
	public void update (float deltaTime) {
		if(!hasTarget()) return;
		
		position.x = target.getPosition().x;
		position.y = target.getPosition().y;
	}
	
	public void setPosition (float x, float y) {
		this.position.set(x, y);
	}	
	public Vector2 getPosition () {
		return position;
	}
	
	//Zoom
	public void addZoom (float amount) {
		setZoom(zoom +amount);
	}	
	public void setZoom (float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}	
	public float getZoom () {
		return zoom;
	}
	
	//Target
	public void setTarget (Player target) {
		if((target.getPosition().x > Constants.VIEWPORT_WIDTH/2) 
				&& (target.getPosition().x < Assets.MAP.getWidth() - Constants.VIEWPORT_WIDTH/2)){
		this.target = target;
	} else {
		this.target = null;	
		}
	}
	public Player getTarget () {
		return target;
	}
	public boolean hasTarget () {
		return target != null;
	}
	public boolean hasTarget(Player target) {
		return hasTarget() && this.target.equals(target);
	}
	
	public void applyTo (OrthographicCamera camera){
		camera.position.x = position.x;
		camera.position.y = position.y + 150;
		//TODO FIX RANDOM HACK ---------^^^^^
		camera.zoom = zoom;
		camera.update();
	}
}
