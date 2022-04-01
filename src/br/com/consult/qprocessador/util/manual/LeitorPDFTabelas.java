package br.com.consult.qprocessador.util.manual;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.consult.qprocessador.util.manual.table.structure.QColumn;
import br.com.consult.qprocessador.util.manual.table.structure.QCoords;
import br.com.consult.qprocessador.util.manual.table.structure.QTable;
import br.com.consult.qprocessador.util.manual.table.structure.QTableBuilder;
import br.com.consult.qprocessador.util.manual.table.structure.TextoDocumentoDTO;

public class LeitorPDFTabelas {
	

	public final static int H = 2;
	public final static int W = 2;

	public static void main(String[] args) {

		List<TextoDocumentoDTO> listaDocumento = new ArrayList<>();

		try {
			List<QTable> listQTable = init();
			
			PDDocument document = PDDocument.load(new File("BOLETIM 19-11-2021.pdf"));

			for (int i = 0; i < 3; i++) {
				PDPage page = document.getPage(i);

				for (QTable qTable : listQTable) {
					if (qTable.getPage() != i) {
						continue;
					}

					JsonObject jsonTableObject = new JsonObject();
					jsonTableObject.addProperty("name", qTable.getName());
					jsonTableObject.addProperty("sigla", qTable.getSigla());

					JsonArray jsonArrayColumns = new JsonArray();

					for (QColumn qColumn : qTable.getListQColumns()) {

						JsonObject jsonColumnObject = new JsonObject();
						jsonColumnObject.addProperty("name", qColumn.getName());

						int x = qColumn.getQCoords().getX1();
						int y = qColumn.getQCoords().getY1();

						int w = qColumn.getQCoords().getX2() - qColumn.getQCoords().getX1();
						int h = qColumn.getQCoords().getY4() - qColumn.getQCoords().getY1();

						Rectangle2D rect = new Rectangle2D.Float(x, y, w, h);
						PDFTextStripperByArea str = new PDFTextStripperByArea();
						str.setSortByPosition(true);
						str.addRegion("coluna", rect);
						str.extractRegions(page);

						String texto[] = str.getTextForRegion("coluna").split("\n");

						JsonArray jsonArray = new JsonArray();

						for (String valor : texto) {
							jsonArray.add(valor);
						}

						jsonColumnObject.add("values", jsonArray);
						jsonArrayColumns.add(jsonColumnObject);
					}

					jsonTableObject.add("columns", jsonArrayColumns);
					System.out.println(jsonArrayColumns);
					TextoDocumentoDTO textoDocumentoDTO = new TextoDocumentoDTO();
					textoDocumentoDTO.setSigla(qTable.getSigla());
					textoDocumentoDTO.setTipo("JSONT");
					textoDocumentoDTO.setTexto(jsonTableObject.toString());
					textoDocumentoDTO.setPagina(i);
					listaDocumento.add(textoDocumentoDTO);
				}

			}

			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<QTable> init() {
		List<QTable> listQTable = new ArrayList<QTable>();
		listQTable = new ArrayList<>();

		listQTable.add(QTableBuilder.buildTable(0, "TABPECAS", "Tabela Peças",
				new QColumn("Peça", new QCoords(new int[] { 48, 366, 168, 366, 126, 482, 48, 482 })),
				new QColumn("Atac/Super SP", new QCoords(new int[] { 168, 366, 240, 366, 240, 482, 168, 482 })),
				new QColumn("Revendedor SP", new QCoords(new int[] { 240, 366, 312, 366, 312, 482, 240, 482 })),
				new QColumn("Atac/Super RJ", new QCoords(new int[] { 312, 366, 390, 366, 390, 482, 312, 482 })),
				new QColumn("Revendedor RJ", new QCoords(new int[] { 390, 366, 452, 366, 452, 482, 390, 482 })),
				new QColumn("NE", new QCoords(new int[] { 452, 366, 528, 366, 528, 482, 452, 482 }))));

		listQTable.add(QTableBuilder.buildTable(0, "CARNESIND", "Carnes Para Industrialização",
				new QColumn("Produto", new QCoords(new int[] { 48, 524, 168, 524, 168, 730, 48, 730 })),
				new QColumn("Min/Max SP", new QCoords(new int[] { 168, 524, 240, 524, 240, 730, 168, 730 })),
				new QColumn("Min/Max RJ", new QCoords(new int[] { 240, 524, 312, 524, 312, 730, 240, 730 }))));

		listQTable.add(QTableBuilder.buildTable(0, "MUIDOSCONG", "Miúdos Congelados",
				new QColumn("Produto", new QCoords(new int[] { 312, 524, 390, 524, 390, 730, 312, 730 })),
				new QColumn("SP/RJ", new QCoords(new int[] { 390, 524, 452, 524, 452, 730, 390, 730 })),
				new QColumn("NE Min/Max", new QCoords(new int[] { 452, 524, 528, 524, 528, 730, 452, 730 }))));

//	/*Coordenadas da Pagina 2*/
//		listQTable.add(QTableBuilder.buildTable(1,"APROVMATAN1", "Aproveitamento de Matança", new QColumn("Produto", new QCoords(new int[] { 103, 120, 171, 120, 171, 192, 103, 198 })),
//																			                new QColumn("Preço",   new QCoords(new int[] { 171, 120, 239, 120, 239, 192, 171, 198 })),
//																			                new QColumn("Prazo",   new QCoords(new int[] { 239, 120, 307, 120, 307, 192, 239, 198 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"APROVMATAN1", "Aproveitamento de Matança 2", new QColumn("Produto", new QCoords(new int[] { 314, 120, 375, 120, 375, 192, 314, 198 })),
//																			                  new QColumn("Preço",   new QCoords(new int[] { 375, 120, 428, 120, 428, 192, 375, 198 })),
//																			                  new QColumn("Prazo",   new QCoords(new int[] { 428, 120, 502, 120, 502, 192, 428, 198 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"BOVRESFTR1", "Carne Bovina Resfriada - Traseiro 1", new QColumn("Produto", new QCoords(new int[] {  34, 264, 109, 264, 109, 390,  34, 390 })),
//				   																	                 new QColumn("Preço",   new QCoords(new int[] { 109, 264, 172, 264, 172, 390, 109, 390 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"BOVRESFTR2", "Carne Bovina Resfriada - Traseiro 2", new QColumn("Produto", new QCoords(new int[] { 172, 264, 240, 264, 240, 390, 172, 390 })),
//																					                 new QColumn("Preço",   new QCoords(new int[] { 238, 264, 306, 264, 306, 390, 238, 390 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"BOVRESFDI1", "Carne Bovina Resfriada - Dianteiro 1", new QColumn("Produto", new QCoords(new int[] {  34, 411, 109, 411, 109, 510,  34, 510 })),
//																					                  new QColumn("Preço",   new QCoords(new int[] { 109, 411, 172, 411, 172, 510, 109, 510 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"BOVRESFDI2", "Carne Bovina Resfriada - Dianteiro 2", new QColumn("Produto", new QCoords(new int[] { 172, 411, 240, 411, 240, 510, 172, 510 })),
//				   																	                  new QColumn("Preço",   new QCoords(new int[] { 238, 411, 306, 411, 306, 510, 238, 510 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"TABAVES", "Aves", new QColumn("Produto",  new QCoords(new int[] {  34, 534, 162, 534, 162, 663,  34, 663 })),
//				    								    		   new QColumn("Preço SP", new QCoords(new int[] { 162, 534, 244, 534, 244, 663, 162, 663 })),
//														           new QColumn("Preço RJ", new QCoords(new int[] { 244, 534, 307, 534, 307, 663, 244, 663 }))));
//
//		
//		listQTable.add(QTableBuilder.buildTable(1,"CORTSUINO", "Carne e Cortes Suínos", new QColumn("Produto",  new QCoords(new int[] { 314, 280, 427, 280, 427, 510, 314, 510 })),
//																		              new QColumn("Preço SP", new QCoords(new int[] { 427, 280, 500, 280, 500, 510, 427, 510 })),
//																		              new QColumn("Preço RJ", new QCoords(new int[] { 500, 280, 550, 280, 550, 510, 500, 510 }))));
//
//		listQTable.add(QTableBuilder.buildTable(1,"CRSUISAL", "Carne e Cortes Suínos - Salgados", new QColumn("Produto",  new QCoords(new int[] { 314, 534, 427, 534, 427, 666, 314, 666 })),
//																					            new QColumn("Preço SP", new QCoords(new int[] { 427, 534, 500, 534, 500, 666, 427, 666 })),
//																					            new QColumn("Preço RJ", new QCoords(new int[] { 500, 534, 550, 534, 550, 666, 500, 666 }))));
//
//		listQTable.add(QTableBuilder.buildTable(2,"SUBPRO1", "Subprodutos 1", new QColumn("Produto",  new QCoords(new int[] {  36, 114, 132, 114, 132, 228,  36, 228 })),
//																 			new QColumn("Preço",    new QCoords(new int[] { 132, 114, 192, 114, 192, 228, 132, 228 })),
//																            new QColumn("Prazo",    new QCoords(new int[] { 192, 114, 234, 114, 234, 228, 504, 228 }))));
//		
//		listQTable.add(QTableBuilder.buildTable(2,"SUBPRO2", "Subprodutos 2", new QColumn("Produto",  new QCoords(new int[] { 243, 114, 357, 114, 357, 228, 243, 228 })),
//							 												new QColumn("Preço SP", new QCoords(new int[] { 357, 114, 420, 114, 420, 228, 357, 228 })),
//							 												new QColumn("Prazo RJ", new QCoords(new int[] { 420, 114, 480, 114, 480, 228, 420, 228 })),
//																			new QColumn("Preço NE", new QCoords(new int[] { 480, 114, 546, 114, 546, 228, 480, 228 }))));
//		
//		listQTable.add(QTableBuilder.buildTable(2,"COTRMFIS", "Cortes Mercado Físico (Boi e Vaca)", new QColumn("Estado",         new QCoords(new int[] {  36, 258, 132, 258, 132, 417,  36, 417 })),
//																			 		 			  new QColumn("UF",             new QCoords(new int[] { 132, 258, 195, 258, 195, 417, 132, 417 })),
//																			 		 			  new QColumn("Unidade",        new QCoords(new int[] { 195, 258, 240, 258, 240, 417, 195, 417 })),
//																			 		 			  new QColumn("Prazo",          new QCoords(new int[] { 240, 258, 279, 258, 279, 417, 240, 417 })),
//																			 		 			  new QColumn("Boi",            new QCoords(new int[] { 279, 258, 357, 258, 357, 417, 279, 417 })),
//																			 		 			  new QColumn("Comportamento",  new QCoords(new int[] { 357, 258, 405, 258, 405, 417, 357, 417 })),
//																			 		 			  new QColumn("Vaca",           new QCoords(new int[] { 405, 258, 480, 258, 480, 417, 405, 417 })),
//																			 		 			  new QColumn("Comportameento", new QCoords(new int[] { 480, 258, 546, 258, 546, 417, 480, 417 }))));
		return listQTable;					
	}
}
