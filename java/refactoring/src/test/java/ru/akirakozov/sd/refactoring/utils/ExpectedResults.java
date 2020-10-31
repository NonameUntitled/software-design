package ru.akirakozov.sd.refactoring.utils;

public class ExpectedResults {
    public static String allProductsGroundTruth = """
            <html><body>\r
            A	1</br>\r
            B	2</br>\r
            C	3</br>\r
            D	4</br>\r
            E	5</br>\r
            F	6</br>\r
            G	7</br>\r
            H	8</br>\r
            I	9</br>\r
            J	10</br>\r
            </body></html>\r
            """;

    public static String allProductsCostGroundTruth = """
            <html><body>\r
            Summary price: \r
            55\r
            </body></html>\r
            """;

    public static String minPriceGroundTruth = """
            <html><body>\r
            <h1>Product with min price: </h1>\r
            A	1</br>\r
            </body></html>\r
            """;

    public static String maxPriceGroundTruth = """
            <html><body>\r
            <h1>Product with max price: </h1>\r
            J	10</br>\r
            </body></html>\r
            """;

    public static String productsAmountGroundTruth = """
            <html><body>\r
            Number of products: \r
            10\r
            </body></html>\r
            """;
}
