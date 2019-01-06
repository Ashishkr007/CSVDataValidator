package CSVDataValidator.CSVDataValidator;

import CSVDataValidator.Bean.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ashish kumar
 * @email niits007@gmail.com
 * @website riveriq.com
 * @since Dec 8, 2018
 * @version 1.0
 */
public class App {
	final static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Data quality check initializing.");
		int finalcode;
		try {
			if (args.length < 1) {
				System.out.println("Please Provide Property File Path");
				logger.error("Please Provide Property File Path");
				System.exit(0);
			}
			GetPropertyItems obj_getPrItems = new GetPropertyItems();
			PropertiesItems obj = obj_getPrItems.getpropertyItems(args[0]);
			FileUtility fc = new FileUtility();
			DataValidation dv = new DataValidation();
			ArrayList<String> Hlist = fc.getHdrFilesList(obj.getFeedpath().toString(), obj.getHeaderFileFormat(),
					obj.getDate());
			finalcode = dv.hdrVal(obj, Hlist, obj.getDataFeed(), obj.getSource());
			System.out.println(finalcode);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}
}
