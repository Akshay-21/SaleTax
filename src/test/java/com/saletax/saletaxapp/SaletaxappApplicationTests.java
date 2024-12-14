package com.saletax.saletaxapp;

import com.saletax.saletaxapp.constant.ProductType;
import com.saletax.saletaxapp.model.Item;
import com.saletax.saletaxapp.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaletaxappApplicationTests {

	private TaxService taxService;

	@BeforeEach
	public void setUp() {
		taxService = new TaxService();
	}

	@Test
	public void testSalesTax_Book() {
		Item book = new Item("Book", 12.49, false, ProductType.BOOK);
		assertEquals(0.00, taxService.calculateSalesTax(book), 0.01);
		assertEquals(12.49, taxService.getTotalPrice(book), 0.01);
	}

	@Test
	public void testSalesTax_MusicCD() {
		Item musicCd = new Item("Music CD", 14.99, false, ProductType.OTHER);
		assertEquals(1.50, taxService.calculateSalesTax(musicCd), 0.01);
		assertEquals(16.49, taxService.getTotalPrice(musicCd), 0.01);
	}

	@Test
	public void testSalesTax_Chocolates() {
		Item chocolate = new Item("Chocolates", 0.85, false, ProductType.FOOD);
		assertEquals(0.00, taxService.calculateSalesTax(chocolate), 0.01);
		assertEquals(0.85, taxService.getTotalPrice(chocolate), 0.01);
	}

	@Test
	public void testSalesTax_ImportedChocolate() {
		Item importedChocolate = new Item("Imported Box of Chocolates", 10.00, true, ProductType.FOOD);
		assertEquals(0.50, taxService.calculateSalesTax(importedChocolate), 0.01);
		assertEquals(10.50, taxService.getTotalPrice(importedChocolate), 0.01);
	}

	@Test
	public void testSalesTax_ImportedPerfume() {
		Item importedPerfume = new Item("Imported Bottle of Perfume", 47.50, true, ProductType.OTHER);
		assertEquals(7.15, taxService.calculateSalesTax(importedPerfume), 0.01);
		assertEquals(54.65, taxService.getTotalPrice(importedPerfume), 0.01);
	}

	@Test
	public void testSalesTax_ImportedPerfumeInput3() {
		Item importedPerfume = new Item("Imported Bottle of Perfume", 27.99, true, ProductType.OTHER);
		assertEquals(4.20, taxService.calculateSalesTax(importedPerfume), 0.01);
		assertEquals(32.19, taxService.getTotalPrice(importedPerfume), 0.01);
	}

	@Test
	public void testSalesTax_Perfume() {
		Item perfume = new Item("Bottle of Perfume", 18.99, false, ProductType.OTHER);
		assertEquals(1.90, taxService.calculateSalesTax(perfume), 0.01);
		assertEquals(20.89, taxService.getTotalPrice(perfume), 0.01);
	}

	@Test
	public void testSalesTax_HeadachePills() {
		Item headachePills = new Item("Packet of Headache Pills", 9.75, false, ProductType.MEDICAL);
		assertEquals(0.00, taxService.calculateSalesTax(headachePills), 0.01);
		assertEquals(9.75, taxService.getTotalPrice(headachePills), 0.01);
	}

	@Test
	public void testSalesTax_ImportedChocolateInput3() {
		Item importedChocolate = new Item("Imported Box of Chocolates", 11.25, true, ProductType.FOOD);
		assertEquals(0.6, taxService.calculateSalesTax(importedChocolate), 0.01);
		assertEquals(11.85, taxService.getTotalPrice(importedChocolate), 0.01);
	}

//	Test Case for SalesTax
	@Test
	public void testTotalSalesTax() {
		Item importedPerfume = new Item("Imported Bottle of Perfume", 27.99, true, ProductType.OTHER);
		Item perfume = new Item("Bottle of Perfume", 18.99, false, ProductType.OTHER);
		Item headachePills = new Item("Packet of Headache Pills", 9.75, false, ProductType.MEDICAL);
		Item importedChocolate = new Item("Imported Box of Chocolates", 11.25, true, ProductType.FOOD);

		double totalSalesTax = taxService.calculateSalesTax(importedPerfume) +
				taxService.calculateSalesTax(perfume) +
				taxService.calculateSalesTax(headachePills) +
				taxService.calculateSalesTax(importedChocolate);
		assertEquals(6.70, totalSalesTax, 0.01);
	}


// Test Case for TotalPrice
	@Test
	public void testTotalPrice() {
		Item importedPerfume = new Item("Imported Bottle of Perfume", 27.99, true, ProductType.OTHER);
		Item perfume = new Item("Bottle of Perfume", 18.99, false, ProductType.OTHER);
		Item headachePills = new Item("Packet of Headache Pills", 9.75, false, ProductType.MEDICAL);
		Item importedChocolate = new Item("Imported Box of Chocolates", 11.25, true, ProductType.FOOD);

		double totalPrice = taxService.getTotalPrice(importedPerfume) +
				taxService.getTotalPrice(perfume) +
				taxService.getTotalPrice(headachePills) +
				taxService.getTotalPrice(importedChocolate);
		assertEquals(74.68, totalPrice, 0.01);
	}
}
