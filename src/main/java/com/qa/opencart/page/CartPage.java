package com.qa.opencart.page;

import org.openqa.selenium.By;

public class CartPage {

	private By cart = By.id("cart");
	
	public void getCart() {
		System.out.println("Cart is added");
	}
}
