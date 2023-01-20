package com.dosrobles.produccion.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SchemaUtils {
    public static String getSchema(String key) {
        if(true) return "DOS_ROBLES";
        Properties prop = new Properties();
	       InputStream input = null;

	try {

		input = new FileInputStream("C:\\corpsoft-erp-config\\schema.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
                String schema = prop.getProperty(key);
		return schema == null ? prop.getProperty("schema01") : schema;

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
        return null;
    }
}
