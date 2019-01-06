# CSVDataValidator
CSV Data Validator is a tool to validate csv file. It parse csv and validate the data with .hdr(csv meta data) before ingestion to Data Lake. It checks data file availability for every day load and validate data with respective meta data like File Size, Checksum, Delimiter, Record count etc. It ensure landed data conformity before give go ahead for data ingestion to Data Lake. It generate complete stats or error log.

# Property File 
CSVDataValidator/src/main/resources/dv_config.properties

DelimiterCount=36
Delimiter=,
Source=Source001
HeaderFileFormat=.hdr
CheckSum=MD5
Headerlines=0
Date=20180202
Auditpath=E:\\StudyDoc\\ProjectsWS\\JavaWS\\CSVDataValidator\\testDir\\Logging
DataFeed=Feed001
HeaderDelimiter=,
SourcePath=E:\\StudyDoc\\ProjectsWS\\JavaWS\\CSVDataValidator\\testDir\\SourceData\\Source001
DataFileFormat=.csv
DateFormat=yyyyMMdd

# Test Data and HDR file
CSVDataValidator/testDir/SourceData/Source001/Feed001/Feed001TestData_20180202.HDR
CSVDataValidator/testDir/SourceData/Source001/Feed001/Feed001TestData_20180202.csv
