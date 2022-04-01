package br.com.consult.qprocessador.util.manual.table.structure;

public class QTableBuilder {

	public static QTable buildTable(int page, String sigla, String name, QColumn ...columns) {
		QTable qTable = new QTable();
		qTable.setName(name);
		qTable.setSigla(sigla);
		qTable.setPage(page);
		
		for(QColumn column : columns) {
			qTable.getListQColumns().add(column);
		}
		
		return qTable;
	}
}
