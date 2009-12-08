package zombiesurvivalsim;

/**
 * Name:   Logger
 * Author: Christopher Welch <welchce at gmail.com>
 * Date:   11/05/2009
 *
 * Variables:
 *  _fileName: String
 *
 * Operations:
 *  public Logger() - Instantiation
 *  public Logger(fileName:String) - Overload
 *  public getFileName():String
 *  public write(thisEvent:Event)
 **/
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Logger {

    // The name of the file that we are printing the log to.
    String _fileName;

    /*
     * The default constructor for the Logger class, creates a log file.
     *  @pre computer has valid date
     *  @post _fileName as been created
     */
    public Logger() {
        final String DATE_FORMAT_NOW = "MM-dd-yy HH.mm.ss";
        Date curTime = Calendar.getInstance().getTime();
        _fileName = new SimpleDateFormat(DATE_FORMAT_NOW).format(curTime) + ".log";
    }

    /*
     * Constructor that takes a filename as a parameter.
     *  @pre file name doesn't already exist
     *  @post _fileName as been created
     */
    public Logger(String fileName) {
        _fileName = fileName;
    }

    /*
     * returns the filename of the log file.
     *  @pre log exists
     *  @post _fileName is returned
     */
    public String getFileName() {
        return _fileName;
    }

    /*
     * writes the passed String st into the log file.
     *  @pre log file is valid
     *  @pre event is valid string
     *  @post log file contains new event
     */
    public void write(Event thisEvent) throws java.io.IOException {
        FileWriter outFile = new FileWriter(_fileName, true);
        PrintWriter logWriter = new PrintWriter(outFile);
        logWriter.write(thisEvent.toString());
        logWriter.close();
    }
}
