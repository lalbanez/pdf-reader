package br.com.consult.qprocessador.util.manual.table.structure;

public class QCoords {

	public QCoords(int[] coordsArray) {
		x1 = coordsArray[0];
		y1 = coordsArray[1];
		x2 = coordsArray[2];
		y2 = coordsArray[3];
		x3 = coordsArray[4];
		y3 = coordsArray[5];
		x4 = coordsArray[6];
		y4 = coordsArray[7];
	}

	// ponto superior esquerdo
	private Integer x1;
	private Integer y1;

	// ponto inferior esquerdo
	private Integer x2;
	private Integer y2;

	// ponto inferior direito
	private Integer x3;
	private Integer y3;

	// ponto superior direito
	private Integer x4;
	private Integer y4;

	private String status;

	private String value;

	@Override
	public boolean equals(Object object) {
		int precision = 3;
		if (object instanceof QCoords) {
			QCoords newCoords = (QCoords) object;

			if ((newCoords.getX1() >= x1 - precision && newCoords.getX1() <= x1 + precision)
					&& (newCoords.getY1() >= y1 - precision && newCoords.getY1() <= y1 + precision)
					&& (newCoords.getX2() >= x2 - precision && newCoords.getX2() <= x2 + precision)
					&& (newCoords.getY2() >= y2 - precision && newCoords.getY2() <= y2 + precision)
					&& (newCoords.getX3() >= x3 - precision && newCoords.getX3() <= x3 + precision)
					&& (newCoords.getX3() >= y3 - precision && newCoords.getY3() <= y3 + precision)
					&& (newCoords.getX4() >= x4 - precision && newCoords.getX4() <= x4 + precision)
					&& (newCoords.getY4() >= y4 - precision && newCoords.getY4() <= y4 + precision)) {
				return true;
			}

		}
		return false;
	}

	public String toString() {
		return "(".concat(x1.toString()).concat(",").concat(y1.toString()).concat(") ")
				.concat("(".concat(x2.toString()).concat(",").concat(y2.toString()).concat(") "))
				.concat("(".concat(x3.toString()).concat(",").concat(y3.toString()).concat(") "))
				.concat("(".concat(x4.toString()).concat(",").concat(y4.toString()).concat(") "));
	}

	public Integer getX1() {
		return x1;
	}

	public void setX1(Integer x1) {
		this.x1 = x1;
	}

	public Integer getY1() {
		return y1;
	}

	public void setY1(Integer y1) {
		this.y1 = y1;
	}

	public Integer getX2() {
		return x2;
	}

	public void setX2(Integer x2) {
		this.x2 = x2;
	}

	public Integer getY2() {
		return y2;
	}

	public void setY2(Integer y2) {
		this.y2 = y2;
	}

	public Integer getX3() {
		return x3;
	}

	public void setX3(Integer x3) {
		this.x3 = x3;
	}

	public Integer getY3() {
		return y3;
	}

	public void setY3(Integer y3) {
		this.y3 = y3;
	}

	public Integer getX4() {
		return x4;
	}

	public void setX4(Integer x4) {
		this.x4 = x4;
	}

	public Integer getY4() {
		return y4;
	}

	public void setY4(Integer y4) {
		this.y4 = y4;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
