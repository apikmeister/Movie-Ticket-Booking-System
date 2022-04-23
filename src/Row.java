import java.util.*;

public class Row {

    private final ArrayList<Seat> seats;
    private final int rowNumber;
    private final int seatCount;
    private int rowClass;

    public Row(int rowClass, int seatCount, int rowNumber) {
        this.rowNumber = rowNumber;
        this.rowClass = rowClass;
        this.seatCount = seatCount;
        seats = new ArrayList<Seat>();
        createSeats(this.seatCount);
    }

    public int getRowClass() {
        return this.rowClass;
    }

    public void setRowClass(int rowClass) {
        this.rowClass = rowClass;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void createSeats(int seatCount) {
        for (int i = 1; i <= seatCount; i++) {
            seats.add(new Seat(false, i));
        }
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
