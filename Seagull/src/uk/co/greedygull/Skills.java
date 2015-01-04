package uk.co.greedygull;

public class Skills {
	
	private static int SKILLPOINTS = 100;
	private static int STAMINA = 1000;
	private static int FIRERATE = 750;
	private static int MOVEMENTSPEED = 250;
	
	public static int getSKILLPOINTS(){
		return SKILLPOINTS;
	}
	
	public static void addSKILLPOINTS(int s){
		SKILLPOINTS = SKILLPOINTS + s;
	}
	
	public static int getSTAMINA(){
		return STAMINA;
	}
	
	public static int getFIRERATE(){
		return FIRERATE;
	}
	
	public static int getMOVEMENTSPEED(){
		return MOVEMENTSPEED;
	}
	
	public static void addSTAMINA(int s){
		STAMINA = STAMINA + s;
	}
	
	public static void addFIRERATE(int s){
		FIRERATE = FIRERATE - s;
	}
	
	public static void addMOVEMENTSPEED(int s){
		MOVEMENTSPEED = MOVEMENTSPEED + s;
	}

}
