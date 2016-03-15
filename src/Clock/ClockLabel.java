package Clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//ClockLabel.java
//An extension of the JLabel class that listens to events from
//a Timer object to update itself with the current date & time.
//
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

public class ClockLabel extends JLabel implements ActionListener {

	public ClockLabel() {
		super("" + new Date());
		Timer t = new Timer(1000, this);
		t.start();
	}

	public void actionPerformed(ActionEvent ae) {
		setText((new Date()).toString());
	}
}