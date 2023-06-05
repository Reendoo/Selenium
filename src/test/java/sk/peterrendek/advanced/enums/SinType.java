package sk.peterrendek.advanced.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SinType {
    MURDER("murder"), HIJACK("hijack"), BLACKMAIL("blackmail"), CAR_ACCIDENT("car accident"), ROBBERY("robbery"), NO_TAGS("no tags");
    private final String value;

    SinType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<SinType> getValue(String value) {
        for (var v : values()) {
            if(v.getValue().equals(value)){
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }


}
