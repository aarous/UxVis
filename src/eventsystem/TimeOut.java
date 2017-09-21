package eventsystem;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple resetable timer that triggers a task.
 * 
 * @author void
 */
public class TimeOut {
	// ==========================================================================
	// === Private Fields
	// ==========================================================================

	/** The timer. */
	private static Timer timer;
	/** The seconds until the timer task is triggered. */
	private static long TIME_OUT = 300;

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================

	/**
	 * Constructor.
	 */
	public TimeOut() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// Task
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, TIME_OUT);
	}

	/**
	 * Resets the Timer.
	 */
	public static void reset() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				// Task
			}
		};
		timer.cancel();
		timer = new Timer();
		timer.schedule(timerTask, TIME_OUT);
	}
}
