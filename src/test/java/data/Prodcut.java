package data;

public class Prodcut {
    private String name;
    private String description;
    private double price;

    public Prodcut(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Prodcut{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {   
        if (this == o) return true;
        if (!(o instanceof Prodcut)) return false;

        Prodcut prodcut = (Prodcut) o;

        if (Double.compare(prodcut.price, price) != 0) return false;
        if (!name.equals(prodcut.name)) return false;
        return description.equals(prodcut.description);
    }
}
