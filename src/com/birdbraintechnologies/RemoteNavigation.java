package com.birdbraintechnologies;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RemoteNavigation {

	/**
	 * Retraces the command
	 * 
	 * @param finch   The finch
	 * @param command The command
	 */
	private static void retrace(Finch finch, Command command) {

		String key = command.getKey();
		List<Integer> values = command.getValues();
		if ("F".equals(key)) {
			finch.setWheelVelocities(values.get(1) * -1, values.get(1) * -1, values.get(0));
		} else if ("R".equals(key)) {
			finch.setWheelVelocities(values.get(1), values.get(2), values.get(0));
		} else if ("L".equals(key)) {
			finch.setWheelVelocities(values.get(2), values.get(1), values.get(0));
		}
	}

	/**
	 * Execution starts from here
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {

		Scanner commandScanner = new Scanner(System.in);
		Finch finch = new Finch();
		Stack<Command> commandsStack = new Stack<>();

		while (true) {
			String command = commandScanner.nextLine();
			String[] splitted = command.split(" ");
			if (splitted.length > 0) {
				if ("F".equals(splitted[0])) {
					if (splitted.length != 3) {
						System.out.println("Improper argumetns found. Please try again!");
					} else {
						try {
							Integer duration = Integer.parseInt(splitted[1]);
							Integer speed = Integer.parseInt(splitted[2]);

							if (duration < 1 || duration > 6) {
								System.out.println("The duration should be between 1-6 seconds. Please try again!");
							} else if (speed < -100 || speed > 100) {
								System.out.println("The speed should be between -100 to +100. Please try again!");
							} else {
								finch.setWheelVelocities(speed, speed, duration);
								commandsStack.add(new Command(splitted[0], Arrays.asList(duration, speed)));
							}
						} catch (NumberFormatException numberFormatException) {
							System.out.println("The command expects numeric duration and speed. Please try again!");
						}
					}
				} else if ("R".equals(splitted[0])) {
					if (splitted.length != 4) {
						System.out.println("Improper argumetns found. Please try again!");
					} else {
						try {
							Integer duration = Integer.parseInt(splitted[1]);
							Integer rightWheelSpeed = Integer.parseInt(splitted[2]);
							Integer leftWheelSpeed = Integer.parseInt(splitted[3]);

							if (duration < 1 || duration > 6) {
								System.out.println("The duration should be between 1-6 seconds. Please try again!");
							} else if (rightWheelSpeed < -100 || rightWheelSpeed > 100) {
								System.out.println(
										"The right wheel speed should be between -100 to +100. Please try again!");
							} else if (leftWheelSpeed < -100 || leftWheelSpeed > 100) {
								System.out.println(
										"The left wheel speed should be between -100 to +100. Please try again!");
							} else if (leftWheelSpeed <= rightWheelSpeed) {
								System.out.println("The finch won't move towards right. Please try again!");
							} else {
								finch.setWheelVelocities(leftWheelSpeed, rightWheelSpeed, duration);
								commandsStack.add(new Command(splitted[0],
										Arrays.asList(duration, rightWheelSpeed, leftWheelSpeed)));
							}
						} catch (NumberFormatException numberFormatException) {
							System.out.println(
									"The command expects numeric duration, left wheel speed, and right wheel speed. Please try again!");
						}
					}
				} else if ("L".equals(splitted[0])) {
					if (splitted.length != 4) {
						System.out.println("Improper argumetns found. Please try again!");
					} else {
						try {
							Integer duration = Integer.parseInt(splitted[1]);
							Integer rightWheelSpeed = Integer.parseInt(splitted[2]);
							Integer leftWheelSpeed = Integer.parseInt(splitted[3]);

							if (duration < 1 || duration > 6) {
								System.out.println("The duration should be between 1-6 seconds. Please try again!");
							} else if (rightWheelSpeed < -100 || rightWheelSpeed > 100) {
								System.out.println(
										"The right wheel speed should be between -100 to +100. Please try again!");
							} else if (leftWheelSpeed < -100 || leftWheelSpeed > 100) {
								System.out.println(
										"The left wheel speed should be between -100 to +100. Please try again!");
							} else if (rightWheelSpeed <= leftWheelSpeed) {
								System.out.println("The finch won't move towards left. Please try again!");
							} else {
								finch.setWheelVelocities(leftWheelSpeed, rightWheelSpeed, duration);
								commandsStack.add(new Command(splitted[0],
										Arrays.asList(duration, rightWheelSpeed, leftWheelSpeed)));
							}
						} catch (NumberFormatException numberFormatException) {
							System.out.println(
									"The command expects numeric duration, left wheel speed, and right wheel speed. Please try again!");
						}
					}
				} else if ("B".equals(splitted[0])) {
					if (splitted.length != 2) {
						System.out.println("Improper argumetns found. Please try again!");
					} else {
						try {
							Integer stepsToRetrace = Integer.parseInt(splitted[1]);
							if (stepsToRetrace <= 0) {
								System.out.println("Steps to retrace cannot be negative or zero. Please try again!");
							} else if (stepsToRetrace > commandsStack.size()) {
								System.out.println("Finch havn't moved specified steps yet. Please try again!");
							} else {
								while (stepsToRetrace-- > 0) {
									Command retracedCommand = commandsStack.pop();
									retrace(finch, retracedCommand);
								}
							}
						} catch (NumberFormatException numberFormatException) {
							System.out.println("The command expects numeric steps. Please try again!");
						}
					}
				} else if ("S".equals(splitted[0])) {
					if (splitted.length > 1) {
						System.out.println("Improper argumetns found. Please try again!");
					} else {
						break;
					}
				} else {
					System.out.println("Invalid command. Please try again!");
				}
			} else {
				System.out.println("No command captured. Please try again!");
			}
		}

		commandScanner.close();
	}
}
