package br.com.consult.qprocessador.util.manual.table.structure;

import java.util.List;

public class QRow {

	private String name;
	private List<QCell> listQCell;
	private QCoords qCoords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QCell> getListQCell() {
		return listQCell;
	}

	public void setListQCell(List<QCell> listQCell) {
		this.listQCell = listQCell;
	}

	public QCoords getqCoords() {
		return qCoords;
	}

	public void setqCoords(QCoords qCoords) {
		this.qCoords = qCoords;
	}

}
