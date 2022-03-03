package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberArms extends SubsystemBase {
    private final PWMVictorSPX climberMotor; 
    private final DoubleSolenoid piston1;
    private final DoubleSolenoid piston2;
    private final DigitalInput limitSwitch1;
    private final DigitalInput limitSwitch2;

    public enum State {
        EXTEND, RETRACT;
    }

    /** Creates a new ExampleSubsystem. **/
    public ClimberArms() {
        climberMotor = new PWMVictorSPX(0);
        piston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
        piston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 4);
        limitSwitch1 = new DigitalInput(5);
        limitSwitch2 = new DigitalInput(6);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation

    }

    public DoubleSolenoid getP1() {
        return piston1;
    }

    public DoubleSolenoid getP2() {
        return piston2;
    }

    public DigitalInput getLimitSwitch1() {
        return limitSwitch1;
    }

    public DigitalInput getLimitSwitch2() {
        return limitSwitch2;
    }

    public void rotateMotor() {
        climberMotor.set(-0.1);
    }

    public void stopMotor() {
        climberMotor.set(0);
    }

    public void rotateTimedMotor(double d) {
        Timer time = new Timer();
        time.start();
        while (!time.hasElapsed(d)) {
          climberMotor.set(-0.1);
        }
        time.stop();
    }

    /** Returns the current state of the acquirer pistons */
    public State getState(DoubleSolenoid p) {
        return p.get() == Value.kForward ? State.EXTEND : State.RETRACT;
    }

    public void setState(State state, DoubleSolenoid p) {
        p.set(state == State.EXTEND ? Value.kForward : Value.kReverse);
    }

    public void toggleState(DoubleSolenoid p) {
        setState(getState(p) == State.EXTEND ? State.RETRACT : State.EXTEND, p);
    }
}
