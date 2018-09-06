/**
 * Создать таблицу «Меню в ресторане».
 * Колонки: название блюда, его стоимость, вес, наличие скидки.
 * Написать код для добавления записей в таблицу и их выборки по критериям:
 * «стоимость от-до», «только со скидкой», выбрать набор блюд так, чтобы их суммарный вес был не более 1 КГ.
 */

import javax.persistence.*;

@Entity
@Table
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "dish", nullable = false)
    private String dish;
    private double price;
    private double weight;
    private double discount;

    public Menu(){}

    public Menu(String dish, double price, double weight, double discount){
        this.dish = dish;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public String getDish() {
        return dish;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public double getDiscount() {
        return discount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString(){
        return "Menu{" +
                "dish = " + dish +
                ", price = " + price +
                ", weight = " + weight +
                ", discount = " + discount +
                "}";
    }
}
