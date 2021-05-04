package dev.jazer.pomodoroFX;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Model {

	private PropertyChangeListener observer;
	
	private long defaultWorkDuration, defaultRestDuration;
	
	public long durationRemaining, endTime;
	
	public ClockState timerState;
	
	private Timer timer;
	
	public Model() {
		timerState = ClockState.STOPPED;
		durationRemaining = 0;
		endTime = 0;
		defaultWorkDuration = 1000*5;
		defaultRestDuration = 1000*5;
	}

	public void setObserver(PropertyChangeListener observer) {
		this.observer = observer;
	}
	
	public void start() {
		
		if (timerState == ClockState.STOPPED) timerState = ClockState.WORKING;
		if (timerState == ClockState.WORKING) View.playWAV("work");
		if (timerState == ClockState.RESTING) View.playWAV("break");
		
		if (durationRemaining == 0) durationRemaining = defaultWorkDuration;
		endTime = new Date().getTime()+durationRemaining;
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				durationRemaining = endTime - new Date().getTime();
				if (new Date().getTime() > endTime) {
					timer.cancel();
					if (timerState == ClockState.WORKING) {
						timerState = ClockState.RESTING;
						durationRemaining = defaultRestDuration;
						start();
					} else {
						timerState = ClockState.WORKING;
						durationRemaining = defaultWorkDuration;
						start();
					}
				}
				if (observer != null) observer.onChange();
			}
		}, 0, 50);
		
	}
	
	public void stop() {
		View.playWAV("stop");
		timerState = ClockState.STOPPED;
		timer.cancel();
		endTime = 0;
		durationRemaining = defaultWorkDuration;
		observer.onChange();
	}
	
}
