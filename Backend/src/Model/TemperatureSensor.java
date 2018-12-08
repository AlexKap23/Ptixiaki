package Model;

import java.util.Date;

//@Entity
public class TemperatureSensor extends Sensor {
    /* @Id
      @GeneratedValue(strategy=Generation.Type.AUTO_)
      private Long id;*/
    private double tempValue;

    public TemperatureSensor(String sensorName, Date date, double tempValue) {
        super(sensorName, date);
        this.tempValue = tempValue;
    }

    public double getTempValue() {
        return tempValue;
    }

    public void setTempValue(double tempValue) {
        this.tempValue = tempValue;
    }
}
