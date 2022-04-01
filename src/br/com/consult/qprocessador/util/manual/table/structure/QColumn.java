package br.com.consult.qprocessador.util.manual.table.structure;

import java.util.List;

public class QColumn {

	public QColumn(String name, QCoords qCoords) {
		this.name = name;
		this.qCoords = qCoords;
	}

	private String name;
	private String title;
	private List<QCell> listQCell;
	private QCoords qCoords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QCell> getListQCell() {
		return listQCell;
	}

	public void setListQCell(List<QCell> listQCell) {
		this.listQCell = listQCell;
	}

	public QCoords getQCoords() {
		return qCoords;
	}

	public void setQCoords(QCoords qCoords) {
		this.qCoords = qCoords;
	}

}
