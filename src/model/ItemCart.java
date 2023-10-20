package model;

import java.util.Objects;

public class ItemCart {
	private int cartNo;
	private String memberId;
	private String itemName;
	private String itemKind;
	private int totalPrice;
	public int getCartNo() {
		return cartNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getItemName() {
		return itemName;
	}
	public String getItemKind() {
		return itemKind;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cartNo, memberId);
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemCart)) {
			return false;
		}
		
		ItemCart other = (ItemCart) obj;
		return this.cartNo == other.cartNo && this.memberId.equals(other.getMemberId());
	}
	

}
