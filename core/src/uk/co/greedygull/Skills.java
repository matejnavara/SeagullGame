package uk.co.greedygull;

/**
 * The Skills class holds all information regarding player stats and high scores.
 * These are retrieved from the persistent Preferences declared in Basicgame.
 * Stats and high scores are all private and only accessed through their respective getter/setter
 * @author Matej
 *
 */
public class Skills {
	
	private static int HIGHSCORE = Basicgame.prefs.getInteger("bestscore");
	private static int BESTDISTANCE = Basicgame.prefs.getInteger("bestdistance");
	
	private static int SKILLPOINTS = 3;
	
	private static int STAMINA = Basicgame.prefs.getInteger("stamina");
	public static int minStamina = 1000;
	public static int maxStamina = 3000;
	
	private static float FIRERATE = Basicgame.prefs.getFloat("firerate");
	public static int minFirerate = 750;
	public static int maxFirerate = 500;
	
	private static int MOVEMENTSPEED = Basicgame.prefs.getInteger("speed");
	public static int minSpeed = 250;
	public static int maxSpeed = 750;
	
	
	/**
	 * Checks for situations where the Preferences have not been configured yet.
	 * Default minimum values will be assigned.
	 */
	public static void checkSkills(){
		
		if(STAMINA == 0){
			STAMINA = minStamina;
		}
		if(FIRERATE == 0){
			FIRERATE = minFirerate;
		}
		if(MOVEMENTSPEED == 0){
			MOVEMENTSPEED = minSpeed;
		}
	}
	
	//BEST SCORE
	public static int getHighscore(){
		return HIGHSCORE;
	}
	
	public static void setHighscore(int s){
		HIGHSCORE = s;
	}
	
	//BEST DISTANCE
	public static int getBestDistance(){
		return BESTDISTANCE;
	}
	
	public static void setBestDistance(int s){
		BESTDISTANCE = s;
	}
	
	//SKILL POINTS
	public static int getSKILLPOINTS(){
		return SKILLPOINTS;
	}
	
	public static void addSKILLPOINTS(int s){
		SKILLPOINTS = SKILLPOINTS + s;
	}
	
	//STAMINA
	public static int getSTAMINA(){
		return STAMINA;
	}
	
	public static void addSTAMINA(int s){
		STAMINA = STAMINA + s;
	}
	
	//FIRE RATE
	public static float getFIRERATE(){
		return FIRERATE;
	}
	
	public static void addFIRERATE(float s){
		FIRERATE = FIRERATE - s;
	}
	
	//MOVEMENT SPEED
	public static int getMOVEMENTSPEED(){
		return MOVEMENTSPEED;
	}
	
	public static void addMOVEMENTSPEED(int s){
		MOVEMENTSPEED = MOVEMENTSPEED + s;
	}

}
