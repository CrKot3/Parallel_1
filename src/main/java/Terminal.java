import java.text.DecimalFormat;

public class Terminal extends Thread {
    static private final DecimalFormat df = new DecimalFormat("0.00");
    static private final double bound = 0.01;
    private final double upperTemperature;
    private final double lowerTemperature;
    private final double upperHumidity;
    private final double lowerHumidity;
    private final Room room;

    public Terminal(Room room, int preferedTemperature, int preferedHumidity) {
        this.room = room;
        this.lowerTemperature = (1 - bound) * preferedTemperature;
        this.upperTemperature = (1 + bound) * preferedTemperature;
        this.lowerHumidity = (1 - bound) * preferedHumidity;
        this.upperHumidity = (1 + bound) * preferedHumidity;
    }

    @Override
    public void run() {
        while (true) {
            double temp = room.getTemperature();
            double humid = room.getHumidity();
            String roomStats = "Room " + currentThread().getName();
            roomStats += ": Temperature is " + df.format(temp);
            roomStats += ", Humidity is " + df.format(humid) + "%. ";
            if (temp > upperTemperature && room.isHeater()) {
                room.setHeater(false);
                roomStats += "Heater is now OFF. ";
            } else if (temp < lowerTemperature && !room.isHeater()) {
                room.setHeater(true);
                roomStats += "Heater is now ON. ";
            }
            if (humid > upperHumidity && room.isFan()) {
                room.setFan(false);
                roomStats += "Fan is now OFF. ";
            } else if (humid < lowerHumidity && !room.isFan()) {
                room.setFan(true);
                roomStats += "Fan is now ON. ";
            }
            System.out.println(roomStats);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
