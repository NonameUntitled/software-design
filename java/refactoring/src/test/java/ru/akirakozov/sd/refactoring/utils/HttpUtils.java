package ru.akirakozov.sd.refactoring.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtils {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    private static final String ADD_PRODUCT_REQUEST_FORMAT = "http://localhost:8081/add-product?name=%s&price=%d";
    private static final String GET_ALL_PRODUCTS_REQUEST = "http://localhost:8081/get-products";
    private static final String GET_ALL_PRODUCTS_COST_SUM_REQUEST = "http://localhost:8081/query?command=sum";
    private static final String GET_PRODUCT_MIN_PRICE = "http://localhost:8081/query?command=min";
    private static final String GET_PRODUCT_MAX_PRICE = "http://localhost:8081/query?command=max";
    private static final String GET_PRODUCT_UNKNOWN_REQUEST = "http://localhost:8081/query?command=lol";
    private static final String GET_PRODUCTS_NUMBER = "http://localhost:8081/query?command=count";

    public static final List<Product> products = List.of(
            new Product("A", 1L),
            new Product("B", 2L),
            new Product("C", 3L),
            new Product("D", 4L),
            new Product("E", 5L),
            new Product("F", 6L),
            new Product("G", 7L),
            new Product("H", 8L),
            new Product("I", 9L),
            new Product("J", 10L)
    );

    public static class Product {
        private String name;
        private Long price;

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(Long price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Long getPrice() {
            return price;
        }

        public Product(String name, Long price) {
            this.name = name;
            this.price = price;
        }
    }

    public static String addProduct(Product product) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        String.format(
                                ADD_PRODUCT_REQUEST_FORMAT,
                                product.getName(),
                                product.getPrice()
                        )
                ))
                .build();
        return getResponse(request);
    }

    public static void addAllProducts() {
        for (Product product : products) {
            addProduct(product);
        }
    }

    public static String getAllProducts() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_ALL_PRODUCTS_REQUEST))
                .build();
        return getResponse(request);
    }

    public static String getAllProductsCostSum() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_ALL_PRODUCTS_COST_SUM_REQUEST))
                .build();
        return getResponse(request);
    }

    public static String getProductMinPrice() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCT_MIN_PRICE))
                .build();
        return getResponse(request);
    }

    public static String getProductMaxPrice() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCT_MAX_PRICE))
                .build();
        return getResponse(request);
    }

    public static String getProductUnknown() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCT_UNKNOWN_REQUEST))
                .build();
        return getResponse(request);
    }

    public static String getProductsNumber() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GET_PRODUCTS_NUMBER))
                .build();
        return getResponse(request);
    }

    private static String getResponse(HttpRequest request) {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }
}
