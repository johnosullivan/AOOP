package edu.comp373.model.facility;

import java.time.LocalDateTime;

public class LocalTimeRange {

	private final LocalDateTime from;
    private final LocalDateTime to;

    public LocalTimeRange(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

	public boolean overlaps(LocalTimeRange other) {
        return isBetween(other.from, this.from, this.to)
                || isBetween(other.to, this.from, this.to)
                || isBetween(this.from, other.from, other.to)
                || isBetween(this.to, other.from, other.to);
    }

    private static boolean isBetween(LocalDateTime from2, LocalDateTime from3, LocalDateTime to) {
        if (from3.isBefore(to)) {  return from3.isBefore(from2) && from2.isBefore(to);
        } else {  return from3.isBefore(from2) || from2.isBefore(to);
        }
    }
	
}
