package florizz.logging;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MyFilter implements Filter {

    /**
     * Determine what level should a handler take
     * Code taken from "https://www.digitalocean.com/community/tutorials/logger-in-java-logging-example"
     *
     * @param log  a LogRecord
     * @return
     */
    @Override
    public boolean isLoggable(LogRecord log) {
        //don't log CONFIG logs in file
        if (log.getLevel() == Level.CONFIG) {
            return false;
        }
        return true;
    }

}
