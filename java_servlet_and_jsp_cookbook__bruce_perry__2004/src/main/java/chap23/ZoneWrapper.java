package chap23;

import java.util.TimeZone;

public class ZoneWrapper {

    public ZoneWrapper() {
    }

    public String[] getAvailableIDs() {
        return TimeZone.getAvailableIDs();
    }

}
