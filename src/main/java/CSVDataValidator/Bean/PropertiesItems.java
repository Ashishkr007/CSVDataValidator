/**
 * 
 */
package CSVDataValidator.Bean;

/**
 * @author Ashish kumar
 * @email niits007@gmail.com
 * @website riveriq.com
 * @since Dec 8, 2018
 * @version 1.0
 */
 public class PropertiesItems {
	private String DestPath;
	private String SourcePath;
	private String Auditpath;
	private String Delimiter;
	private String HeaderDelimiter;
	private int DelimiterCount;
	private String Source;
	private String DataFeed;
	private String CheckSum;
	private String Date;
	private String DataFileFormat;
	private String HeaderFileFormat;
	private String DateFormat;
	private String Feedpath;
	private String FeedDataPath;
	private String FeedHdrPath;
	private String AuditLogs;
	private String Headerlines;
	
	
	/**
	 * @return the headerlines
	 */
	public String getHeaderlines() {
		return Headerlines;
	}

	/**
	 * @param headerlines the headerlines to set
	 */
	public void setHeaderlines(String headerlines) {
		Headerlines = headerlines;
	}

	/**
	 * @return the headerFileFormat
	 */
	public String getHeaderFileFormat() {
		return HeaderFileFormat;
	}

	/**
	 * @param headerFileFormat the headerFileFormat to set
	 */
	public void setHeaderFileFormat(String headerFileFormat) {
		HeaderFileFormat = headerFileFormat;
	}
	/**
	 * @return the auditLogs
	 */
	public String getAuditLogs() {
		return AuditLogs;
	}

	/**
	 * @param auditLogs the auditLogs to set
	 */
	public void setAuditLogs(String auditLogs) {
		AuditLogs = auditLogs;
	}

	/**
	 * @return the feedHdrPath
	 */
	public String getFeedHdrPath() {
		return FeedHdrPath;
	}

	/**
	 * @param feedHdrPath the feedHdrPath to set
	 */
	public void setFeedHdrPath(String feedHdrPath) {
		FeedHdrPath = feedHdrPath;
	}

	/**
	 * @return the feedDataPath
	 */
	public String getFeedDataPath() {
		return FeedDataPath;
	}

	/**
	 * @param feedDataPath the feedDataPath to set
	 */
	public void setFeedDataPath(String feedDataPath) {
		FeedDataPath = feedDataPath;
	}

	/**
	 * @return the feedBadDataPath
	 */
	public String getFeedBadDataPath() {
		return FeedBadDataPath;
	}

	/**
	 * @param feedBadDataPath the feedBadDataPath to set
	 */
	public void setFeedBadDataPath(String feedBadDataPath) {
		FeedBadDataPath = feedBadDataPath;
	}

	private String FeedBadDataPath;
	//may be useless
	private String DateExc;

	/**
	 * @return the dateExc
	 */
	public String getDateExc() {
		return DateExc;
	}

	/**
	 * @param dateExc the dateExc to set
	 */
	public void setDateExc(String dateExc) {
		DateExc = dateExc;
	}

	/**
	 * @return the feedpath
	 */
	public String getFeedpath() {
		return Feedpath;
	}

	/**
	 * @param feedpath the feedpath to set
	 */
	public void setFeedpath(String feedpath) {
		Feedpath = feedpath;
	}

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat() {
		return DateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat) {
		DateFormat = dateFormat;
	}

	/**
	 * @return the destPath
	 */
	public String getDestPath() {
		return DestPath;
	}

	/**
	 * @param destPath the destPath to set
	 */
	public void setDestPath(String destPath) {
		DestPath = destPath;
	}

	/**
	 * @return the sourcePath
	 */
	public String getSourcePath() {
		return SourcePath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	public void setSourcePath(String sourcePath) {
		SourcePath = sourcePath;
	}

	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return Delimiter;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		Delimiter = delimiter;
	}

	/**
	 * @return the headerDelimiter
	 */
	public String getHeaderDelimiter() {
		return HeaderDelimiter;
	}

	/**
	 * @param headerDelimiter the headerDelimiter to set
	 */
	public void setHeaderDelimiter(String headerDelimiter) {
		HeaderDelimiter = headerDelimiter;
	}

	/**
	 * @return the delimiterCount
	 */
	public int getDelimiterCount() {
		return DelimiterCount;
	}

	/**
	 * @param delimiterCount the delimiterCount to set
	 */
	public void setDelimiterCount(int delimiterCount) {
		DelimiterCount = delimiterCount;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return Source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		Source = source;
	}

	/**
	 * @return the dataFeed
	 */
	public String getDataFeed() {
		return DataFeed;
	}

	/**
	 * @param dataFeed the dataFeed to set
	 */
	public void setDataFeed(String dataFeed) {
		DataFeed = dataFeed;
	}

	/**
	 * @return the checkSum
	 */
	public String getCheckSum() {
		return CheckSum;
	}

	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(String checkSum) {
		CheckSum = checkSum;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		Date = date;
	}

	/**
	 * @return the dataFileFormat
	 */
	public String getDataFileFormat() {
		return DataFileFormat;
	}

	/**
	 * @param dataFileFormat the dataFileFormat to set
	 */
	public void setDataFileFormat(String dataFileFormat) {
		DataFileFormat = dataFileFormat;
	}
	/**
	 * @return the auditpath
	 */
	public String getAuditpath() {
		return Auditpath;
	}

	/**
	 * @param auditpath the auditpath to set
	 */
	public void setAuditpath(String auditpath) {
		Auditpath = auditpath;
	}

}
