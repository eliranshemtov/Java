package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HandleProperties {
	private static String FILE_NAME = "resources/Properties.xml";

	/**
	 * using XMLDecoder to read the given xml file and set the client properties according to it
	 * @return ClientProperties object
	 */
	public static ClientProperties readProperties() {
		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
			ClientProperties properties = (ClientProperties)decoder.readObject();
			return properties;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			decoder.close();
		}
		return null;
	}
	
	/**
	 * Sets input XML file path
	 * @param fILE_NAME
	 */
	public static void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
}
