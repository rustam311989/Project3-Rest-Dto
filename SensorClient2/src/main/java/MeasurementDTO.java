

public class MeasurementDTO {

    private SensorDTO owner;

    private double value;

    private boolean raining;

    public SensorDTO getOwner() {
        return owner;
    }

    public void setOwner(SensorDTO owner) {
        this.owner = owner;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
