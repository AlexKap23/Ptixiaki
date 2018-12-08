package Model;
import java.util.Date;

//@Entity
public class Sensor {
    /* @Id
     @GeneratedValue(strategy=Generation.Type.AUTO_)
     private Long id;*/
    private String sensorName;
    private Date date;

    public Sensor(String sensorName, Date date) {
        this.sensorName = sensorName;
        this.date = date;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
