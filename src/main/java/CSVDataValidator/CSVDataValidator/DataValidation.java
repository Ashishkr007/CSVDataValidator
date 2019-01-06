/**
 * 
 */
package CSVDataValidator.CSVDataValidator;

import CSVDataValidator.Bean.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

/**
 * @author Ashish kumar
 * @email niits007@gmail.com
 * @website riveriq.com
 * @since Dec 9, 2018
 * @version 1.0
 */
public class DataValidation {
	File fin;
	PropertiesItems obj = null;
	FileUtility fc = new FileUtility();
	boolean status;
	private char del;
	SimpleDateFormat df;
	Date startTime;
	Long getsize;
	String getchecksum;

	private boolean sizeFlag;
	private boolean rowcountFlag;
	private boolean delimetercountFlag;
	private boolean checksumFlag;
	final static Logger logger = LoggerFactory.getLogger(App.class);

	public int hdrVal(PropertiesItems obj, ArrayList<String> hlist, String objname, String source) throws IOException {
		this.obj = obj;
		int statuscode = 0;
		boolean dqstatus = false;

		if (hlist.size() > 0) {
			for (String listh : hlist) {
				HashMap<Integer, List<String>> hdrList = fc.getHdrMetaData(obj, listh, obj.getFeedpath());
				if (hdrList.size() > 0) {
					statuscode = datafileAvalCheck(hdrList);
					logger.info("_________________________________________________________________________");
					logger.info("Validating Header File:-" + listh);
					dqstatus = dataQualityCheck(hdrList);
					System.out.println(dqstatus);

				} else {
					logger.error("header Details not found");
					statuscode = 1;
				}
			}
		}
		if (statuscode == 0) {
			if (status) {
				statuscode = 1;
			} else {
				statuscode = 0;
			}
		}
		return statuscode;
	}

	public int datafileAvalCheck(HashMap<Integer, List<String>> hdrList) throws IOException {
		int statuscode = 0;
		Set<Integer> keySet = hdrList.keySet();
		Iterator<Integer> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
			int key = keySetIterator.next();
			String FileName = hdrList.get(key).get(0);
			if (FileName != null) {
				String Fpath = obj.getFeedpath() + "\\" + FileName;
				fin = new File(Fpath);
				if (!fin.exists()) {
					logger.error("Data File Not Found " + fin.getName());
				}
			} else {
				logger.error("Data file name not found in header");
				statuscode = 1;
			}
		}
		return statuscode;
	}

	public boolean dataQualityCheck(HashMap<Integer, List<String>> hdrList) throws IOException {
		boolean status = false;
		Set<Integer> keySet = hdrList.keySet();
		Iterator<Integer> keySetIterator = keySet.iterator();

		while (keySetIterator.hasNext()) {
			logger.info("----------------------------------------------------------------------------");
			int key = keySetIterator.next();
			String FileName = hdrList.get(key).get(0);
			String recordcount = hdrList.get(key).get(1);
			String Size = hdrList.get(key).get(2);
			String checksum = hdrList.get(key).get(3);
			String Filepath = obj.getFeedpath() + "\\" + FileName;
			String data = obj.getFeedDataPath() + "\\" + FileName;
			String getencoding = fc.getEncoding(Filepath);
			int delimitercount = obj.getDelimiterCount();

			try {
				boolean isFileExist = ValFileExist(Filepath, FileName, key);
				if (!isFileExist) {
					continue;
				}
				sizeFlag = ValFileSize(Filepath, Size);
				checksumFlag = ValCheckSum(Filepath, checksum);
				delimetercountFlag = ValDelCount(Filepath, delimitercount);

				if (sizeFlag == true & checksumFlag == true & delimetercountFlag == true) {
					status = true;
				} else {
					status = false;
				}
			} catch (IOException e) {
				logger.error("IO exception before " + e.getMessage());
			}
		}
		return status;
	}

	public boolean ValFileExist(String Filepath, String FileName, int key) throws IOException {
		boolean isFileExist = true;
		try {
			fin = new File(Filepath);
			logger.info("File No.:- " + key + " Reading :-" + FileName);
			if (!fin.exists()) {
				logger.error("File Not Found " + fin.getName());
				isFileExist = false;
			}
		} catch (Exception ex) {
		}
		return isFileExist;
	}

	public boolean ValFileSize(String Filepath, String Size) {
		boolean sizeFlag = true;
		try {
			getsize = fc.getFileSize(Filepath);
			System.out.println(getsize);
			if (getsize == Long.parseLong(Size)) {
				sizeFlag = true;
				logger.info("File Size Matches");
			} else if (fin.length() == Long.parseLong(Size)) {
				sizeFlag = true;
				logger.info("File Size Matches ");
			} else {
				sizeFlag = false;
				logger.error("File Size Does Not Match");
			}
		} catch (Exception ex) {
		}
		return sizeFlag;
	}

	// checksum check with either MD5 or SHA1
	public boolean ValCheckSum(String Filepath, String checksum) {
		boolean checksumFlag = true;
		try {
			getchecksum = fc.getChecksumSieble(Filepath, obj.getCheckSum());
			if (getchecksum.equalsIgnoreCase(checksum.replaceAll("\\s", ""))) {
				checksumFlag = true;
				logger.info("Checksum Matches");
			} else {
				checksumFlag = false;
				logger.error("Checksum Does Not Found:- " + getchecksum);
			}
		} catch (Exception ex) {
		}
		return checksumFlag;
	}

	public boolean ValDelCount(String Filepath, int delimetercount) {
		boolean delimetercountFlag = true;
		del = obj.getDelimiter().toString().charAt(0);
		try {
			FileReader fileReader = new FileReader(Filepath);
			CsvParserSettings parserSettings = new CsvParserSettings();
			parserSettings.setLineSeparatorDetectionEnabled(true);
			// parserSettings.setHeaderExtractionEnabled(true);
			parserSettings.setAutoConfigurationEnabled(true);
			parserSettings.setQuoteDetectionEnabled(true);
			// parserSettings.detectFormatAutomatically();
			parserSettings.setIgnoreLeadingWhitespaces(true);
			parserSettings.setIgnoreTrailingWhitespaces(true);
			parserSettings.getFormat().setDelimiter(del);
			// parserSettings.setSkipEmptyLines(true);
			parserSettings.setKeepQuotes(true);
			// parserSettings.setParseUnescapedQuotes(false);
			CsvParser parser = new CsvParser(parserSettings);
			parser.beginParsing(fileReader);
			String[] headers = null;

			if (obj.getHeaderlines().equalsIgnoreCase("0")) {
				headers = parser.parseNext();
				if (headers.length - 1 == delimetercount) {
					delimetercountFlag = true;
					logger.info("Delimeter count match");
				} else {
					delimetercountFlag = false;
					logger.error("Delimeter count not match");
				}
			}
			parser.stopParsing();
		} catch (Exception ex) {
		}
		return delimetercountFlag;
	}

}
