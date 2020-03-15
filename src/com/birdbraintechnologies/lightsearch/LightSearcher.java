package com.birdbraintechnologies.lightsearch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.birdbraintechnologies.Finch;

/**
 * The controller LightSearcher. It holds controllers to let the Finch's robot,
 * search for the light.
 */
public class LightSearcher {

	/**
	 * The searcher bot
	 */
	private Finch searcherBot = new Finch();

	/**
	 * The execution start time
	 */
	private Long executionStartTime = 0l;

	/**
	 * The execution end time
	 */
	private Long executionEndTime = 0l;

	/**
	 * The left light reading
	 */
	private List<Integer> leftLightReading = new ArrayList<>();

	/**
	 * The right light reading
	 */
	private List<Integer> rightLightReading = new ArrayList<>();

	/**
	 * Prepares a label
	 * 
	 * @param text       The text
	 * @param width      The width
	 * @param height     The height
	 * @param x          The x co-ordinate
	 * @param y          The y co-ordinate
	 * @param textColor  The text color
	 * @param background The background color
	 * @return The label
	 */
	private JLabel getLabel(String text, int width, int height, int x, int y, Font font, Color textColor,
			Color background) {

		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setText(text);
		label.setFont(font);
		label.setBounds(x, y, width, height);
		label.setBackground(background);
		label.setForeground(textColor);
		label.setBorder(new CompoundBorder(label.getBorder(), new EmptyBorder(10, 10, 10, 10)));
		return label;
	}

	/**
	 * Shows the instructions on screen
	 */
	private void showInstructions() {

		JFrame frame = new JFrame();
		frame.pack();
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setSize(new Dimension(700, 250));
		frame.setBackground(new Color(192, 192, 192));

		frame.add(this.getLabel("Task I: Search for the light", 700, 30, 20, 20, new Font(Font.SERIF, Font.PLAIN, 20),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel("Instructions:", 700, 30, 20, 60, new Font(Font.SERIF, Font.PLAIN, 18), Color.BLACK,
				Color.WHITE));
		frame.add(this.getLabel("Place the Finch on the floor", 700, 30, 20, 100, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel("To end the program, pick up the Finch and place on its tail for 2 seconds", 700, 30,
				20, 140, new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		frame.add(this.getLabel("Before the program ends, you will be asked if you'd like to view the logs", 700, 30,
				20, 180, new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Get highest recorded value
	 * 
	 * @return The highest recorded value
	 */
	private Integer getHighestRecordedValue() {

		Integer max = Integer.MIN_VALUE;
		for (Integer reading : leftLightReading) {
			if (reading > max) {
				max = reading;
			}
		}
		for (Integer reading : rightLightReading) {
			if (reading > max) {
				max = reading;
			}
		}

		return max;
	}

	/**
	 * Get lowest recorded value
	 * 
	 * @return The lowest recorded value
	 */
	private Integer getLowestRecordedValue() {

		Integer min = Integer.MAX_VALUE;
		for (Integer reading : leftLightReading) {
			if (reading < min) {
				min = reading;
			}
		}
		for (Integer reading : rightLightReading) {
			if (reading < min) {
				min = reading;
			}
		}

		return min;
	}

	/**
	 * Get average recorded value
	 * 
	 * @return The average recorded value
	 */
	private Double getAverageRecordedValue() {

		int sum = 0;
		int totalReadings = leftLightReading.size() + rightLightReading.size();
		for (Integer reading : leftLightReading) {
			sum += reading;
		}
		for (Integer reading : rightLightReading) {
			sum += reading;
		}

		if (totalReadings == 0)
			return 0d;
		return sum / (totalReadings * 1.0);
	}

	/**
	 * Shows the error message on screen
	 * 
	 * @param errorMessage The error message
	 */
	private void showError(String errorMessage) {

		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * View the logs
	 */
	private void viewLogs() {

		JFrame frame = new JFrame();
		frame.pack();
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setSize(new Dimension(550, 350));
		frame.setBackground(new Color(192, 192, 192));

		frame.add(
				this.getLabel("LOG", 510, 30, 20, 20, new Font(Font.SERIF, Font.PLAIN, 20), Color.BLACK, Color.WHITE));
		frame.add(this.getLabel("Starting right light sensor value", 350, 30, 20, 60,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		if (!rightLightReading.isEmpty()) {
			frame.add(this.getLabel(rightLightReading.get(0).toString(), 150, 30, 380, 60,
					new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		} else {
			frame.add(this.getLabel("NA", 150, 30, 380, 60, new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK,
					Color.WHITE));
		}

		frame.add(this.getLabel("Starting left light sensor value", 350, 30, 20, 100,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		if (!leftLightReading.isEmpty()) {
			frame.add(this.getLabel(leftLightReading.get(0).toString(), 150, 30, 380, 100,
					new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));
		} else {
			frame.add(this.getLabel("NA", 150, 30, 380, 100, new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK,
					Color.WHITE));
		}

		frame.add(this.getLabel("Highest recorded value", 350, 30, 20, 140, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel(this.getHighestRecordedValue().toString(), 150, 30, 380, 140,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));

		frame.add(this.getLabel("Lowest recorded value", 350, 30, 20, 180, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel(this.getLowestRecordedValue().toString(), 150, 30, 380, 180,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));

		frame.add(this.getLabel("Duration of execution", 350, 30, 20, 220, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel((executionEndTime - executionStartTime) + " ms.", 150, 30, 380, 220,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));

		frame.add(this.getLabel("Number of light detections", 350, 30, 20, 260, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel(leftLightReading.size() + rightLightReading.size() + "", 150, 30, 380, 260,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));

		frame.add(this.getLabel("Average light sensor value", 350, 30, 20, 300, new Font(Font.SERIF, Font.PLAIN, 16),
				Color.BLACK, Color.WHITE));
		frame.add(this.getLabel(this.getAverageRecordedValue().toString(), 150, 30, 380, 300,
				new Font(Font.SERIF, Font.PLAIN, 16), Color.BLACK, Color.WHITE));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Asks to view the logs
	 */
	private void askToViewLogs() {

		int selection = JOptionPane.showConfirmDialog(null, "Would you like to view the logs?", "View logs",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (selection == JOptionPane.YES_OPTION) {
			this.viewLogs();
		}
	}

	/**
	 * Sleeps for the specified time
	 * 
	 * @param ms The milliseconds
	 */
	private void sleep(long ms) {

		try {
			Thread.sleep(ms);
		} catch (InterruptedException interruptedException) {
			this.showError(interruptedException.getLocalizedMessage());
		}
	}

	/**
	 * Performs the light movement sequence
	 */
	private void doLightMovementSequence() {

		int rightLightSensor = searcherBot.getRightLightSensor();
		int leftLightSensor = searcherBot.getRightLightSensor();
		int sensorAverage = (leftLightSensor + rightLightSensor) / 2;

		while (searcherBot.isFinchLevel() && sensorAverage > 20) {

			if (rightLightSensor == leftLightSensor) {
				searcherBot.setWheelVelocities(200, 200);
			} else if (rightLightSensor > leftLightSensor) {
				searcherBot.setWheelVelocities(150, 200);
				searcherBot.buzz(100, 1000);
			} else {
				searcherBot.setWheelVelocities(200, 150);
				searcherBot.buzz(50, 1000);
			}

			rightLightSensor = searcherBot.getRightLightSensor();
			leftLightSensor = searcherBot.getRightLightSensor();
			sensorAverage = (leftLightSensor + rightLightSensor) / 2;
		}
	}

	/**
	 * Proceeds the Finch
	 */
	private void proceed() {

		searcherBot.setLED(255, 255, 0); // Yellow light

		// Taking the initial readings
		leftLightReading.add(searcherBot.getLeftLightSensor());
		rightLightReading.add(searcherBot.getRightLightSensor());
		this.sleep(1000l);

		// Setting the wheel velocity
		searcherBot.setWheelVelocities(200, 200, 4000); // Moving forward 4 seconds
		while (searcherBot.isFinchLevel()) {
			int leftLightSensor = searcherBot.getLeftLightSensor();
			int rightLightSensor = searcherBot.getRightLightSensor();
			leftLightReading.add(leftLightSensor);
			rightLightReading.add(rightLightSensor);

			int sensorAverage = (leftLightSensor + rightLightSensor) / 2;
			if (sensorAverage > 20) {
				searcherBot.setLED(new Color(sensorAverage, 0, 0));
				this.doLightMovementSequence();
			} else {
				int randomTurn = (int) (System.currentTimeMillis() % 2);
				if (randomTurn == 0) {
					searcherBot.setWheelVelocities(100, 200); // Turning 90 degrees left
				} else {
					searcherBot.setWheelVelocities(200, 100); // Turning 90 degrees right
				}
				searcherBot.setWheelVelocities(200, 200, 1000); // Moving forward 1 second
			}
		}
	}

	/**
	 * Starts the Finch
	 */
	private void start() {

		if (searcherBot.isFinchLevel()) {
			proceed();
		}
		if (!searcherBot.isBeakUp()) { // Wait for 1 second and start again
			sleep(5000l);
			start();
		}
	}

	/**
	 * Execution starts from here
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {

		LightSearcher lightSearcher = new LightSearcher();
		lightSearcher.showInstructions();
		lightSearcher.executionStartTime = System.currentTimeMillis();
		lightSearcher.start();
		lightSearcher.executionEndTime = System.currentTimeMillis();
		lightSearcher.askToViewLogs();
		lightSearcher.searcherBot.quit();
	}
}
