public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < Integer.parseInt(args[0]) * 4; i += 4) {
            Room room = new Room(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
            Terminal terminal = new Terminal(room, Integer.parseInt(args[i + 2]), Integer.parseInt(args[i + 3]));
            room.start();
            terminal.start();
        }
    }
}