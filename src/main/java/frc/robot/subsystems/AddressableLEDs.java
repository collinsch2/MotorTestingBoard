package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AddressableLEDs extends SubsystemBase {

    PWMSparkMax m_blinkin;

    public AddressableLEDs() {
        this.m_blinkin = new PWMSparkMax(0);
    }

    // public void setIndexerColor(double color1,  double color2) {
    //     m_blinkin.set();
    // }

    //method overloading for if there's only one color needed
    // public void setIndexerColor(double color1) {
 
    // }

    public void update() {
        m_blinkin.set(SmartDashboard.getNumber("LED PWM power", 0));
    }

 
}