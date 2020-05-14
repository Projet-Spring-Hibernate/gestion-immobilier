package com.intiformation.toolsEntities;

public class TypeBien {

	String typeBien;

	public String getTypeBien() {
		return typeBien;
	}

	public void setTypeBien(String typeBien) {
		this.typeBien = typeBien;
	}

	public TypeBien(String typeBien) {
		super();
		this.typeBien = typeBien;
	}

	@Override
	public String toString() {
		return "TypeBien [typeBien=" + typeBien + "]";
	}
	
}
