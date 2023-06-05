package sk.peterrendek.advanced.enums;

import java.util.Arrays;
import java.util.Optional;

public enum BrowserType {
    CHROME, FIREFOX, INVALID_NAME;
    public static BrowserType getValue(String s) {
        if(s==null){
            return BrowserType.INVALID_NAME;
        }
        return Arrays.stream(values())
                .filter(browserType -> browserType.toString().equalsIgnoreCase(s))
                .findFirst()
                .orElse(BrowserType.INVALID_NAME);
    }
}
