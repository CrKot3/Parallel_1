public class Room extends Thread {
    static private final double delta = 0.5;
    private double currentTemperature;
    private double currentHumidity;
    private boolean Heater;
    private boolean Fan;

    public Room(double currentTemperature, double currentHumidity) {
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        Heater = Fan = false;
    }

    public double getTemperature() {
        return currentTemperature;
    }

    public double getHumidity() {
        return currentHumidity;
    }

    public boolean isHeater() {
        return Heater;
    }

    public boolean isFan() {
        return Fan;
    }

    public void setHeater(boolean mode) {
        Heater = mode;
    }

    public void setFan(boolean mode) {
        Fan = mode;
    }

    @Override
    public void run() {
        while (true) {
            if (Heater) {
                currentTemperature += delta * Math.random();
            } else {
                currentTemperature -= delta * Math.random();
            }
            if (Fan) {
                currentHumidity += delta * Math.random();
                if (currentHumidity > 100) currentHumidity = 100;
            } else {
                currentHumidity -= delta * Math.random();
                if (currentHumidity < 0) currentHumidity = 0;
            }
            try {
                Thread.sleep(50 + (int) (100 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
