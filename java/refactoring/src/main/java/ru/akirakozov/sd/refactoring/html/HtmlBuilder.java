package ru.akirakozov.sd.refactoring.html;

import ru.akirakozov.sd.refactoring.Product;

public class HtmlBuilder {
    private String text = "";

    public void addHeader(String headerTitle) {
        text += "<h1>" + headerTitle + "</h1>" + System.lineSeparator();
    }

    public void addLine(String line) {
        text += line + System.lineSeparator();
    }

    public void addProduct(Product product) {
        text += product.getName() + "\t" + product.getPrice() + "</br>" + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "<html><body>" + System.lineSeparator() + text + "</body></html>" + System.lineSeparator();
    }
}
