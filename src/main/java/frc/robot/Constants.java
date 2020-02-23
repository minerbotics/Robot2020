/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class DriveConstants {
        public final static int kLeftFrontMotorPort = 1;
        public final static int kLeftBackMotorPort = 2;
        public final static int kRightFrontMotorPort = 3;
        public final static int kRightBackMotorPort = 4;
    }

    public final static class IOConstants {
        public final static int kControllerPort = 0;
        public final static int kAButton = 1;
        public final static int kBButton = 2;
        public final static int kRBButton = 6;
        public final static int kLBButton = 5;
        public final static int kYButton = 4;
        public final static int kXButton = 3;
    }

    public final static class ClimbConstants {
        public final static int kClimbMotorPort = 0;
    }

    public final static class ArmConstants {
        public final static int kArmMotorPort = 1;
    }

    public final static class IntakeConstants {
        public final static int kIntakeMotorPort = 2;
    }
}
