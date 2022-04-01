package br.com.consult.qprocessador.util.manual.table.structure;

public class QCell {
	private String name;
	private String value;
	private QCoords qCoords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public QCoords getqCoords() {
		return qCoords;
	}

	public void setqCoords(QCoords qCoords) {
		this.qCoords = qCoords;
	}

}
