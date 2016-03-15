package Clock;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClockTest extends JFrame {

	public ClockTest() {
		super("Timer Demo");
		setSize(300, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ClockLabel clock = new ClockLabel();
		getContentPane().add(clock, BorderLayout.NORTH);
	}

	public static void main(String args[]) {
		ClockTest ct = new ClockTest();
		ct.setVisible(true);
	}
}