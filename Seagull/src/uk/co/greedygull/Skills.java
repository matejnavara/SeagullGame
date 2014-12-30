package uk.co.greedygull;

public class Skills {
	
	private static int SKILLPOINTS = 0;
	private static int STAMINA = 1000;
	public static int FIRERATE = 750;
	public static int MOVEMENTSPEED = 250;
	
	public static int getSKILLPOINTS(){
		return SKILLPOINTS;
	}
	
	public static void addSKILLPOINTS(int s){
		SKILLPOINTS = SKILLPOINTS + s;
	}
	
	public static int getSTAMINA(){
		return STAMINA;
	}
	
	public void addSTAMINA(int s){
		STAMINA = STAMINA + s;
	}
	
	public void addFIRERATE(int s){
		FIRERATE = FIRERATE - s;
	}
	
	public void addMOVEMENTSPEED(int s){
		MOVEMENTSPEED = MOVEMENTSPEED + s;
	}

}
