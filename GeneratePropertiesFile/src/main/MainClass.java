package main;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import config.ClientProperties;

/**
 * 
 * Main (Generate Properties) class -<br>
 * The main function will Create new clientProperties object using clientProperties(int Port, String ip)<br>
 * and will encode and write it to xml file at "resources/Properties.xml" path.
 *
 */
public class MainClass {

	private static final String FILE_NAME = "resources/Properties.xml";
	
	public static void main(String[] args) {
		
		ClientProperties properties = new ClientProperties(7224, "127.0.0.1");
		XMLEncoder encoder = null;
		
		try {
			encoder = new XMLEncoder(new FileOutputStream(FILE_NAME));
			encoder.writeObject(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			encoder.close();
		}

	}
}
