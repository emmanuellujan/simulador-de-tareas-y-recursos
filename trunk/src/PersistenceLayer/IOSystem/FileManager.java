package PersistenceLayer.IOSystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {

	public FileManager() {
	}

	public String readFile(String fileName) {
		DataInputStream f = null;
		String txt = null;
		try {
			f = new DataInputStream(new FileInputStream(fileName));
			f.read();
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		return txt;
	}

	public void writeFile(String fileName, String txt) {
		DataOutputStream f = null;
		try {
			f = new DataOutputStream(new FileOutputStream(fileName, false));
			f.writeBytes(txt);
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
	}

}
