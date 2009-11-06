/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

    //the name of the file that we are printing the log to.
    String _fileName;

    /*
     * The default constructor for the Logger class, creates a log file.
    */
    public Logger() {
        final String DATE_FORMAT_NOW = "MM-dd-yy HH.mm.ss";
        Date curTime = Calendar.getInstance().getTime();
        _fileName = new SimpleDateFormat(DATE_FORMAT_NOW).format(curTime)+".log";
    }
    /*
     * Constructor that takes a filename as a parameter.
    */
    public Logger(String fileName) {
        _fileName = fileName;
    }
    /*
     * returns the filename of the log file.
    */
    public String getFileName() { return _fileName; }

    /*
     * writes the passed String st into the log file.
    */
    public void write(Event thisEvent) throws java.io.IOException {
        FileWriter outFile = new FileWriter(_fileName,true);
        PrintWriter logWriter = new PrintWriter(outFile);
        //logWriter.write(thisEvent.ToString());
        logWriter.close();
    }

}
