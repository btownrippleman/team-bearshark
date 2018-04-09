package main.test.wait;

public abstract class WaitManager {
	public static void WaitMillis(long milliseconds) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < start + milliseconds) {}
	}
	
	public static void WaitSeconds(int seconds) {
		WaitMillis(seconds * 1000);
	}
}
