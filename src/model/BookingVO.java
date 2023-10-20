package model;

import java.util.Objects;

public class BookingVO {
	private String memberId;
	private int bookingNo;
	private int pmNo;
	private String title;
	private String sheets;
	private int sheetPrice;
	public BookingVO(String memberId, int bookingNo, int pmNo, String title, String sheets, int sheetPrice) {
		super();
		this.memberId = memberId;
		this.bookingNo = bookingNo;
		this.pmNo = pmNo;
		this.title = title;
		this.sheets = sheets;
		this.sheetPrice = sheetPrice;
	}
	public String getMemberId() {
		return memberId;
	}
	public int getBookingNo() {
		return bookingNo;
	}
	public int getPmNo() {
		return pmNo;
	}
	public String getTitle() {
		return title;
	}
	public String getSheets() {
		return sheets;
	}
	public int getSheetPrice() {
		return sheetPrice;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bookingNo, memberId);
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BookingVO)) {
			return false;
		}
		BookingVO other = (BookingVO) obj;
		return this.bookingNo == other.getBookingNo() && this.memberId.equals(other.getMemberId());
	}
	
	
}
