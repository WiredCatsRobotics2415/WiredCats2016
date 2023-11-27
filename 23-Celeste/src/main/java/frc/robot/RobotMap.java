package frc.robot;

public class RobotMap {
    // Port numbers
    public static final int LEFT_TALON_BACK=1, LEFT_TALON_FRONT=2, RIGHT_TALON_BACK=3, RIGHT_TALON_FRONT=4;

    public static final int PIVOT_INTAKE_TALON=6, SPIN_INTAKE_TALON=5;

    public static final int CATAPULT_SOLENOID_1=7,CATAPULT_SOLENOID_2=0;

    public static final int[] LONOG_SOLENOID={5,6}, SHORT_SOLENOID={2,4};

    public static final int[] CATAPULT_SOLENOIDS={CATAPULT_SOLENOID_1, CATAPULT_SOLENOID_2};

    public static final int[] LEFT_ENCODER={4,5}, RIGHT_ENCODER={2,3};

    public static final int[] INTAKE_IR={6,1}, WINCH_LOCK={0,3};
    public static final int WINCH_MOTOR=0;

    public static final int LIGHTSWITCH_PORT=0;

    public static final int PIXYCAM_ANALOG=0, PIXYCAM_DIGITAL=0;

    public static final int PCM_ID=20;
}