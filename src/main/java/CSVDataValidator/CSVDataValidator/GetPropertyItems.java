/**
 * 
 */
package CSVDataValidator.CSVDataValidator;

import java.io.FileInputStream;
import CSVDataValidator.Bean.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ashish kumar
 * @email niits007@gmail.com
 * @website riveriq.com
 * @since Dec 8, 2018
 * @version 1.0
 */
public class GetPropertyItems {
	final static Logger logger = LoggerFactory.getLogger(GetPropertyItems.class);

	public PropertiesItems getpropertyItems(String propertyFilePath) {
		Properties prop = new Properties();
		InputStream input = null;
		PropertiesItems obj = new PropertiesItems();

		try {
			input = new FileInputStream(propertyFilePath);

			// load a properties file from class path, inside static method
			prop.load(input);

			if (prop.get("SourcePath") == null || prop.get("SourcePath").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for SourcePath");
				logger.error("Please Provide value for SourcePath");
			} else {
				obj.setSourcePath(prop.getProperty("SourcePath"));
			}
			if (prop.get("Auditpath") == null || prop.get("Auditpath").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for Auditpath");
				logger.error("Please Provide value for Auditpath");
			} else {
				obj.setAuditpath(prop.getProperty("Auditpath"));
			}
			if (prop.get("Delimiter") == null || prop.get("Delimiter").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for Delimiter");
				logger.error("Please Provide value for Delimiter");
			} else {
				obj.setDelimiter(prop.getProperty("Delimiter"));
			}
			if (prop.get("HeaderDelimiter") == null || prop.get("HeaderDelimiter").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for HeaderDelimiter");
				logger.error("Please Provide value for HeaderDelimiter");
			} else {
				obj.setHeaderDelimiter(prop.getProperty("HeaderDelimiter"));
			}
			if (prop.get("DelimiterCount") == null || prop.get("DelimiterCount").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for DelimiterCount");
				logger.error("Please Provide value for DelimiterCount");
			} else {
				obj.setDelimiterCount(Integer.parseInt(prop.getProperty("DelimiterCount")));
			}
			if (prop.get("Source") == null || prop.get("Source").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for Source");
				logger.error("Please Provide value for Source");
			} else {
				obj.setSource(prop.getProperty("Source"));
			}
			if (prop.get("DataFeed") == null || prop.get("DataFeed").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for DataFeed");
				logger.error("Please Provide value for DataFeed");
			} else {
				obj.setDataFeed(prop.getProperty("DataFeed"));
			}
			if (prop.get("CheckSum") == null || prop.get("CheckSum").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for CheckSum");
				logger.error("Please Provide value for CheckSum");
			} else {
				obj.setCheckSum(prop.getProperty("CheckSum"));
			}
			if (prop.get("Date") == null || prop.get("Date").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for Date");
				logger.error("Please Provide value for Date");
			} else {
				obj.setDate(prop.getProperty("Date"));
			}
			if (prop.get("DataFileFormat") == null || prop.get("DataFileFormat").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for DataFileFormat");
				logger.error("Please Provide value for DataFileFormat");
			} else {
				obj.setDataFileFormat(prop.getProperty("DataFileFormat"));
			}
			if (prop.get("HeaderFileFormat") == null || prop.get("HeaderFileFormat").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for HeaderFileFormat");
				logger.error("Please Provide value for HeaderFileFormat");
			} else {
				obj.setHeaderFileFormat(prop.getProperty("HeaderFileFormat"));
			}
			if (prop.get("DateFormat") == null || prop.get("DateFormat").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for DateFormat");
				logger.error("Please Provide value for DateFormat");
			} else {
				obj.setDateFormat(prop.getProperty("DateFormat"));
			}
			if (prop.get("Headerlines") == null || prop.get("Headerlines").toString().equalsIgnoreCase("")) {
				System.out.println("Please Provide value for Headerlines");
				logger.error("Please Provide value for Headerlines");
			} else {
				obj.setHeaderlines(prop.getProperty("Headerlines"));
			}
			obj.setFeedpath(obj.getSourcePath().toString() + "\\" + obj.getDataFeed());
			obj.setDateExc(new SimpleDateFormat(obj.getDateFormat()).format(new Date()));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}
