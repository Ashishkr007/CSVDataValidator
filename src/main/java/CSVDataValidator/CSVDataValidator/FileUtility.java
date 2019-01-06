package CSVDataValidator.CSVDataValidator;

import CSVDataValidator.Bean.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ashish kumar
 * @email niits007@gmail.com
 * @website riveriq.com
 * @since Dec 8, 2018
 * @version 1.0
 */
public class FileUtility {
	final static Logger logger = LoggerFactory.getLogger(App.class);
	ArrayList<String> successList = new ArrayList<String>();

	public ArrayList<String> getHdrFilesList(String FILE_DIR, String FILE_TEXT_EXT, String datestr) throws IOException {
		return new FileUtility().listFile(FILE_DIR, FILE_TEXT_EXT, datestr);
	}

	public ArrayList<String> listFile(String folder, String ext, String datestr) {
		ArrayList<String> successList = new ArrayList<String>();

		GenericExtFilter filter = new GenericExtFilter(ext);
		File dir = new File(folder);

		if (dir.isDirectory() == false) {
			System.out.println("Directory does not exists : " + dir);
			logger.error("Directory does not exists : " + dir);
			return successList;
		}

		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			System.out.println("no files end with : " + ext);
			logger.error("no files end with : " + ext);
			return successList;
		} else {
			for (String file : list) {
				String pattern = "_*" + datestr + "_*";
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(file);

				if (m.find()) {
					successList.add(file);
				}
			}
		}
		return successList;
	}

	class GenericExtFilter implements FilenameFilter {
		private String ext;

		public GenericExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(ext);
		}
	}
	
	public HashMap<Integer, List<String>> getHdrMetaData(PropertiesItems obj, String filename, String filepath) {
		int lineCount = 1;
		HashMap<Integer, List<String>> hdrData = new HashMap<Integer, List<String>>();
		// Input file which needs to be parsed
		String fileToParse = filepath + "\\" + filename;
		BufferedReader fileReader = null;
		String DELIMITER = obj.getHeaderDelimiter().toString();
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileToParse));

			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				if (!line.contains("\"") && !line.contains(":")) {
					String[] tokens = line.split(DELIMITER, -1);

					if (tokens.length == 4) {
						List<String> tokenList = new ArrayList<String>(Arrays.asList(tokens));

						hdrData.put(lineCount, tokenList);
						lineCount++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return hdrData;
	}

	public String getChecksum(String datafile) throws IOException {
		MessageDigest md = null;
		String checksum = null;
		FileInputStream fis = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return e.getMessage();
		}
		try {
			fis = new FileInputStream(datafile);
			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}

			checksum = Base64.encodeBase64String(md.digest());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			fis.close();
		}
		return checksum;
	}

	public String getChecksumOthers(String datafile) throws IOException {
		InputStream fis = new FileInputStream(datafile);
		byte[] buffer = new byte[1024];
		MessageDigest complete = null;
		try {
			complete = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		{
			fis.close();
		}
		byte[] b = complete.digest();
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public String getChecksumSieble(String datafile, String Algo) throws IOException {
		StringBuffer sb = new StringBuffer("");
		MessageDigest md = null;
		FileInputStream fis = null;
		try {
			// md = MessageDigest.getInstance("SHA1");
			md = MessageDigest.getInstance(Algo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			fis = new FileInputStream(datafile);
			byte[] dataBytes = new byte[1024];
			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			byte[] mdbytes = md.digest();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			fis.close();
		}
		return sb.toString();
	}

	public String getEncoding(String datafile) throws IOException {

		FileInputStream in = null;
		InputStreamReader ins = null;
		String sc = null;
		try {
			in = new FileInputStream(datafile);
			ins = new InputStreamReader(in);
			sc = ins.getEncoding();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			in.close();
			ins.close();
		}
		return sc;
	}

	public long getFileSize(String datafile) throws IOException {
		int read = 0;
		long charCount = 0;
		BufferedReader reader = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(datafile);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			char[] cbuf = new char[1024];
			while ((read = reader.read(cbuf)) != -1) {
				charCount += read;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			reader.close();
			in.close();
		}
		return charCount;
	}
}
