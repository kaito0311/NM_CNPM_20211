package model.person;

import java.time.LocalDate;

public class TemporaryAbRe {
	private boolean isAbsent;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String tmpResidencePlace;

	public TemporaryAbRe(boolean isAbsent, LocalDate fromDate, LocalDate toDate, String tmpResidencePlace) {
		super();
		this.isAbsent = isAbsent;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.tmpResidencePlace = tmpResidencePlace;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getTmpResidencePlace() {
		return tmpResidencePlace;
	}

	public void setTmpResidencePlace(String tmpResidencePlace) {
		this.tmpResidencePlace = tmpResidencePlace;
	}

	public String getTmpType() {
		if (isAbsent)
			return "Tạm vắng";
		return "Tạm trú";
	}

	public void setTmpType(boolean isAbsent) {
		this.isAbsent = isAbsent;
	}
	
}
