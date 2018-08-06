package io.jcervelin.broker.message.domains;

public class Sale {

    private String product;
    private double price;

    public Sale(String product, double price) {
        this.product = product;
        this.price = price;
    }

    public Sale() {
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "product='" + product + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        if (Double.compare(sale.price, price) != 0) return false;
        return product != null ? product.equals(sale.product) : sale.product == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product != null ? product.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
