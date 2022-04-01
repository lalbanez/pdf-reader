package br.com.consult.qprocessador.util.manual;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class LeitorPDFManual {
	public static void main(String[] args) {
		try {
			System.out.println("---- Inicio da Leitura ----");
			File file = new File("Entendimento Embalagens (versão final).pdf");
			PDDocument pdfDoc = PDDocument.load(file);

			for (int i = 0; i < pdfDoc.getNumberOfPages(); i++) {
				PDPage page = pdfDoc.getPage(i);

				Rectangle2D rect = new Rectangle2D.Float(0, 0, 595, 840);
				PDFTextStripperByArea str = new PDFTextStripperByArea();
				str.setSortByPosition(true);
				str.addRegion("pagina", rect);
				str.extractRegions(page);
				String texto = str.getTextForRegion("pagina");

				texto = removeUTF8Characters(texto);
				System.out.println(texto);
			}
			System.out.println("---- Fim da Leitura ----");
			pdfDoc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String removeUTF8Characters(String content) {
		// remove all non-ASCII characters
		content = content.replaceAll("[^\\x00-\\x7F]", "");

		// remove all the ASCII control characters
		content = content.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

		// removes non-printable characters from Unicode
		content = content.replaceAll("\\p{C}", "");

		return content;
	}

}
