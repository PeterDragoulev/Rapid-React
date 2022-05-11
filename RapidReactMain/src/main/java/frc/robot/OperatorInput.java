package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Commands.ShooterToggleCommand;

public class OperatorInput {
    XboxController controllers[];

    public OperatorInput() {
        controllers[0] = new XboxController(0);
        controllers[1] = new XboxController(1);

        Button shootButton = new Button(controllers[1]::getAButtonPressed);
        Command shooterToggleCommand =  new ShooterToggleCommand();
        shootButton.toggleWhenPressed(shooterToggleCommand);
    }

    public double getChassisSpeed(){
        return controllers[0].getLeftY();
    }
    public double getChassisTurn(){
        return controllers[0].getRightX();
    }
    
    public boolean shouldResetTurret(){
        return controllers[1].getBButton();
    }

    public boolean shouldIntake(){
        return controllers[1].getXButton();
    }

    public boolean shouldToggleIntake(){
        return controllers[1].getYButton();
    }
    

}