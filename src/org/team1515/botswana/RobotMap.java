package org.team1515.botswana;

import org.team1515.botswana.util.Pair;

public class RobotMap {

	public static final int[] MOTORS_WHEEL_TOP_LEFT = {31};
	public static final int[] MOTORS_WHEEL_TOP_RIGHT = {32};
	public static final int[] MOTORS_WHEEL_BOTTOM_LEFT = {33};
	public static final int[] MOTORS_WHEEL_BOTTOM_RIGHT = {34};

	public static final Pair<Integer> ENCODER_WHEEL_TOP_LEFT = new Pair<>(2, 3);
	public static final Pair<Integer> ENCODER_WHEEL_TOP_RIGHT = new Pair<>(6, 7);
	public static final Pair<Integer> ENCODER_WHEEL_BOTTOM_LEFT = new Pair<>(4, 5);
	public static final Pair<Integer> ENCODER_WHEEL_BOTTOM_RIGHT = new Pair<>(0, 1);
	
	public static final int LIMIT_SWITCH = 8;

	public static final int PDP = 10;
	public static final int PCM = 11;

	public static final Pair<Integer> SOLENOID_GEAR_HOLDER = new Pair<>(0, 1);

	public static final int[] MOTORS_WINCH = {42};
	public static final int[] MOTORS_FRONT_SHOOTER = {40};
	public static final int[] MOTORS_BACK_SHOOTER = {41};
	
}
