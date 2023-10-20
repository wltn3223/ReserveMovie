package model;

import java.util.Objects;

public class ItemVO {
	private String itemName;
	private String itemKind;
	private int itemUnitPrice;
	private String amount;
	public ItemVO(String itemName, String itemKind, int itemUnitPrice, String amount) {
		super();
		this.itemName = itemName;
		this.itemKind = itemKind;
		this.itemUnitPrice = itemUnitPrice;
		this.amount = amount;
	}
	public String getItemName() {
		return itemName;
	}
	public String getItemKind() {
		return itemKind;
	}
	public int getItemUnitPrice() {
		return itemUnitPrice;
	}
	public String getAmount() {
		return amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemName);
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ItemVO)) {
			return false;
		}
		ItemVO other = (ItemVO)obj;
		return Objects.equals(itemName, other.itemName);
	}
	
	
}
