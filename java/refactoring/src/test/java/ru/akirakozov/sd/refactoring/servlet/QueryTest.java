package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Assert;
import org.junit.Test;
import ru.akirakozov.sd.refactoring.utils.DatabaseUtilsTest;
import ru.akirakozov.sd.refactoring.utils.ExpectedResults;
import ru.akirakozov.sd.refactoring.utils.HttpUtils;

public class QueryTest {
    private static final String OK_RESPONSE = "OK" + System.lineSeparator();

    private void withClearDatabase(Runnable test) {
        DatabaseUtilsTest.clearDatabase();
        test.run();
    }

    @Test
    public void testProductAddition() {
        withClearDatabase(() -> {
            String result = HttpUtils.addProduct(new HttpUtils.Product("New", 10L));
            Assert.assertEquals(OK_RESPONSE, result);
        });
    }

    @Test
    public void testAddAllProducts() {
        withClearDatabase(HttpUtils::addAllProducts);
    }

    @Test
    public void testGetAllProducts() {
        withClearDatabase(() -> {
            HttpUtils.addAllProducts();
            String result = HttpUtils.getAllProducts();
            Assert.assertEquals(result, ExpectedResults.allProductsGroundTruth);
        });
    }

    @Test
    public void testGetAllProductsCostSum() {
        withClearDatabase(() -> {
            HttpUtils.addAllProducts();
            String result = HttpUtils.getAllProductsCostSum();
            Assert.assertEquals(result, ExpectedResults.allProductsCostGroundTruth);
        });
    }

    @Test
    public void testGetProductMinPrice() {
        withClearDatabase(() -> {
            HttpUtils.addAllProducts();
            String result = HttpUtils.getProductMinPrice();
            Assert.assertEquals(result, ExpectedResults.minPriceGroundTruth);
        });
    }

    @Test
    public void testGetProductMaxPrice() {
        withClearDatabase(() -> {
            HttpUtils.addAllProducts();
            String result = HttpUtils.getProductMaxPrice();
            Assert.assertEquals(result, ExpectedResults.maxPriceGroundTruth);
        });
    }

    @Test
    public void testProductsAmount() {
        withClearDatabase(() -> {
            HttpUtils.addAllProducts();
            String result = HttpUtils.getProductsNumber();
            Assert.assertEquals(result, ExpectedResults.productsAmountGroundTruth);
        });
    }
}
