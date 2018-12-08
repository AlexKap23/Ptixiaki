package Model;

import java.util.Date;

//@Entity
public class MotionSensor extends Sensor {
   /* @Id
    @GeneratedValue(strategy=Generation.Type.AUTO_)
    private Long id;*/
    private boolean motion;

    public MotionSensor(String sensorName, Date date, boolean motion) {
        super(sensorName, date);
        this.motion = motion;
    }

    public boolean isMotion() {
        return motion;
    }

    public void setMotion(boolean motion) {
        this.motion = motion;
    }
}
