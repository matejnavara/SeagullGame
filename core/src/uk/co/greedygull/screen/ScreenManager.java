package uk.co.greedygull.screen;

/**
 * The screen manager handles the switching between screens
 * @author Matej
 *
 */
public class ScreenManager {
	
	private static Screen currentScreen;
	
	/**
	 * This method disposes the current screen and calls the create method of the input screen parameter
	 * @param screen
	 */
	public static void setScreen(Screen screen){
		if(currentScreen != null)
			currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create();
	}
	
	/**
	 * Returns the private screen variable {@link #currentScreen}
	 * @return
	 */
	public static Screen getCurrentScreen(){
		return currentScreen;
	}

}
