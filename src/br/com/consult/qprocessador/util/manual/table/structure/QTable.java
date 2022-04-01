package br.com.consult.qprocessador.util.manual.table.structure;

import java.util.ArrayList;
import java.util.List;

public class QTable {
	
	public QTable() {
		listQColumns = new ArrayList<>();
	}
	
	private int page;
	private String sigla;
	private String name;
	private QCoords qCoords;
	private List<QColumn> listQColumns;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QCoords getqCoords() {
		return qCoords;
	}
	public void setqCoords(QCoords qCoords) {
		this.qCoords = qCoords;
	}
	public List<QColumn> getListQColumns() {
		return listQColumns;
	}
	public void setListQColumns(List<QColumn> listQColumns) {
		this.listQColumns = listQColumns;
	}

	
}
