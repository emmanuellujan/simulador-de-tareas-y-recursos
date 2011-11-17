package Model.DataModel.Configurator;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Configurator {
	String confFile;
	String ioDirectory;
	
	String inputDir;
	String outputDir;
	
	//Obsolete
	String inputFile;
	String outputFile;
	
	public Configurator(){
		//String confFile = "conf.xml"; // Usar esta línea cuando se pretende crear el jar, sino poner el path completo
		String confFile = "/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/src/conf.xml";
		this.setConfFile(confFile);
		this.setConfParameters();
	}

	private void setConfParameters() {
		String fileName = this.getConfFile();
		String sIODirectory = "";
		String sInputDir = "";
		String sOutputDir = "";
		String sInputFile = "";
		String sOutputFile = "";
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList ioDirectoryElementList = doc.getElementsByTagName("ioDirectory");
			Element ioDirectoryElement = (Element) ioDirectoryElementList.item(0);
			NodeList ioDirectory = ioDirectoryElement.getChildNodes();
			sIODirectory = ((Node) ioDirectory.item(0)).getNodeValue();
			
			NodeList inputDirElementList = doc.getElementsByTagName("inputDir");
			Element inputDirElement = (Element) inputDirElementList.item(0);
			NodeList inputDir = inputDirElement.getChildNodes();
			sInputDir = ((Node) inputDir.item(0)).getNodeValue();
			
			NodeList outputDirElementList = doc.getElementsByTagName("outputDir");
			Element outputDirElement = (Element) outputDirElementList.item(0);
			NodeList outputDir = outputDirElement.getChildNodes();
			sOutputDir = ((Node) outputDir.item(0)).getNodeValue();
			
			//Obsolete 
			NodeList inputFileElementList = doc.getElementsByTagName("inputFile");
			Element inputFileElement = (Element) inputFileElementList.item(0);
			NodeList inputFile = inputFileElement.getChildNodes();
			sInputFile = ((Node) inputFile.item(0)).getNodeValue();
			
			NodeList outputFileElementList = doc.getElementsByTagName("outputFile");
			Element outputFileElement = (Element) outputFileElementList.item(0);
			NodeList outputFile = outputFileElement.getChildNodes();
			sOutputFile = ((Node) outputFile.item(0)).getNodeValue();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setIoDirectory(sIODirectory);
		this.setInputDir(sIODirectory+sInputDir);
		this.setOutputDir(sIODirectory+sOutputDir);
		this.setInputFile(sInputFile);
		this.setOutputFile(sOutputFile);
	}
	
	public String getConfFile() {
		return confFile;
	}

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}
	
	public String getIoDirectory() {
		return ioDirectory;
	}

	public void setIoDirectory(String ioDirectory) {
		this.ioDirectory = ioDirectory;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
}
