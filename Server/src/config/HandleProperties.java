package config;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * HandleProperties class will know how to read xml file at a given path and create new ServerProperties object,
 * using it's readProperties() function.<br><br>
 * To set the xml file path, use setFILE_NAME(string fileName) 
 *
 */
public class HandleProperties {
	
	private static String fileName = "resources/Properties.xml";
	
	/**
	 * using java.beans.XMLDecoder to read given xml file and set the ServerProperties according to it
	 * @return
	 */
	public static ServerProperties readProperties() {
		XMLDecoder decoder = null;

		try {
			decoder = new XMLDecoder(new FileInputStream(fileName));
			ServerProperties properties = (ServerProperties)decoder.readObject();
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
	 public static void setFILE_NAME(String FILE_NAME){
		 fileName = FILE_NAME;
	 }
}
