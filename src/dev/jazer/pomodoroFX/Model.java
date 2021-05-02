package dev.jazer.pomodoroFX;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Model {

	private PropertyChangeListener observer;
	
	public long durationRemaining, endTime;
	
	public ClockState timerState;
	
	private Timer timer;
	
	public Model() {
		timerState = ClockState.STOPPED;
		durationRemaining = 0;
		endTime = 0;
	}
	
	public void setObserver(PropertyChangeListener observer) {
		this.observer = observer;
	}
	
	public void start() {

		timerState = ClockState.RUNNING;
		if (durationRemaining == 0) durationRemaining = 1000*60*25;
		endTime = new Date().getTime()+durationRemaining;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				durationRemaining = endTime - new Date().getTime();
				if (new Date().getTime() > endTime) timer.cancel();
				if (observer != null) observer.onChange();
			}
		}, 0, 100);
		
	}
	
	public void pause() {
		timerState = ClockState.PAUSED;
		timer.cancel();
	}
	
	public void stop() {
		timerState = ClockState.STOPPED;
		timer.cancel();
		endTime = 0;
		durationRemaining = 1000*60*25;
		observer.onChange();
	}
	
}
