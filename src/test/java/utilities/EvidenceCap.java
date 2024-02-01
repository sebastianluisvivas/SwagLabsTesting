package utilities;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v119.page.model.FontSizes;



public class EvidenceCap {
	public static void captureScreenOnDocument(
			WebDriver driver, 
			String rutaImagen, 
			String nombreDocumento, 
			String tituloEvidencia) throws IOException, InterruptedException, InvalidFormatException {
	
	File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE	);
	File imageFile = new File(rutaImagen);
	FileUtils.copyFile(screen, imageFile);
	File fichero = new File (nombreDocumento);
	XWPFDocument docx;
	
	if (!fichero.exists()) {
		docx = new XWPFDocument();
	} else {
		FileInputStream ficheroStream = new FileInputStream (fichero);
		docx = new XWPFDocument(ficheroStream);
	}
	XWPFParagraph paragraph = docx.createParagraph();
	XWPFRun run = paragraph.createRun();
	run.setText(tituloEvidencia);
	run.setFontSize(13);
	
	InputStream pic = new FileInputStream(rutaImagen);
	
	run.addPicture(pic, Document.PICTURE_TYPE_PNG, rutaImagen, Units.toEMU(500),Units.toEMU(200));
	pic.close();
	
	FileOutputStream out = new FileOutputStream (nombreDocumento);
	docx.write(out);
	out.flush();
	out.close();
	docx.close();
	
	
	

	}

	
	
	
	@SuppressWarnings("resource")
	public static void setTittleForDocument(String nombreDocumento,
			String tituloEvidencia, 
			int fotSize) throws IOException, InterruptedException {
		
		File fichero = new File(nombreDocumento);
		XWPFDocument docx;
		
		if (!fichero.exists()) {
			docx = new XWPFDocument();
		} else {
			FileInputStream ficheroStream = new FileInputStream(fichero);
			docx = new XWPFDocument (ficheroStream);
		}
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(tituloEvidencia);
		
		run.setFontSize(fotSize);
				
		FileOutputStream out = new FileOutputStream(nombreDocumento);
		docx.write(out);
		out.flush();
		

		
	
}






}
