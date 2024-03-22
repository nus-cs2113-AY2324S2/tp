package florizz.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    /**
     * Better format logging output
     * Code taken from "https://www.digitalocean.com/community/tutorials/logger-in-java-logging-example"
     *
     * @param record the log record to be formatted.
     * @return
     */
    @Override
    public String format(LogRecord record) {
        return record.getThreadID()+"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
    }

}
