package PersistenceLayer.DataPersistenceLayer.Configurator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configurator {
	String confFile;

	String inputDir;
	String outputDir;

	String projectName;

	String bar;

	public Configurator() {
		// String confFile = "conf.xml"; // Usar esta línea cuando se pretende
		// crear el jar, sino poner el path completo
		// String confFile =
		// "C:\\Users\\Administrador\\workspace-test\\TaskOnResourcesII\\src\\conf.xml";
		String confFile = "/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/src/conf.xml";
		this.setConfFile(confFile);
		this.setConfParameters();
	}

	public String getBar() {
		return bar;
	}

	public String getBarFromPath(String s) {
		String bar = "";
		if (s.contains("/"))
			bar = "/";
		else
			bar = "\\\\";
		return bar;
	}

	public String getConfFile() {
		return confFile;
	}

	public String getInputDir() {
		return inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getSaveBarFromPath(String s) {
		String bar = "";
		if (s.contains("/"))
			bar = "/";
		else
			bar = "\\";
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}

	private void setConfParameters() {
		String fileName = this.getConfFile();
		String sInputDir = "";
		String sOutputDir = "";
		String sProjectName = "";
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList inputDirElementList = doc.getElementsByTagName("inputDir");
			Element inputDirElement = (Element) inputDirElementList.item(0);
			NodeList inputDir = inputDirElement.getChildNodes();
			sInputDir = ((Node) inputDir.item(0)).getNodeValue();

			NodeList outputDirElementList = doc
					.getElementsByTagName("outputDir");
			Element outputDirElement = (Element) outputDirElementList.item(0);
			NodeList outputDir = outputDirElement.getChildNodes();
			sOutputDir = ((Node) outputDir.item(0)).getNodeValue();

			NodeList projectNameElementList = doc
					.getElementsByTagName("projectName");
			Element projectNameElement = (Element) projectNameElementList
					.item(0);
			NodeList projectName = projectNameElement.getChildNodes();
			sProjectName = ((Node) projectName.item(0)).getNodeValue();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String bar = this.getBarFromPath(sInputDir);
		this.setBar(bar);
		this.setInputDir(sInputDir + bar + sProjectName + bar);
		this.setOutputDir(sOutputDir + bar + sProjectName + bar);
		this.setProjectName(sProjectName);

	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
