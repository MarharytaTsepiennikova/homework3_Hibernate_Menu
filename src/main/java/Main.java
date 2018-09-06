import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

/**
 * Создать таблицу «Меню в ресторане».
 * Колонки: название блюда, его стоимость, вес, наличие скидки.
 * Написать код для добавления записей в таблицу и их выборки по критериям:
 * «стоимость от-до», «только со скидкой», выбрать набор блюд так, чтобы их суммарный вес был не более 1 КГ.
 */

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add new dish to menu");
                    System.out.println("2: price list");
                    System.out.println("3: dishes with discount");
                    System.out.println("4: show dishes up to 1 kg");
                    System.out.print("-> ");

                    String str = sc.nextLine();

                    switch (str) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            viewPriceList(sc);
                            break;
                        case "3":
                            viewDishesWithDiscount(sc);
                            break;
                        case "4":
                            viewDishesUpTo1Kg(sc);
                            break;
                        default:
                            return;
                    }
                }

            } finally {
                sc.close();
                emf.close();
                em.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public static void addDish(Scanner sc){
        System.out.println("Enter name of dish: ");
        String dish = sc.nextLine();
        System.out.println("Enter price: ");
        String strPrice = sc.nextLine();
        double price = Double.parseDouble(strPrice);
        System.out.println("Enter weight: ");
        String strWeight = sc.nextLine();
        double weight = Double.parseDouble(strWeight);
        System.out.println("Enter discount for dish: ");
        String strDiscount = sc.nextLine();
        double discount = Double.parseDouble(strDiscount);

        em.getTransaction().begin();
        try {
            Menu menu = new Menu (dish, price, weight, discount);
            em.persist(menu);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
        }
    }

    public static void viewPriceList(Scanner sc){
        Query query = em.createQuery("SELECT m FROM Menu m ORDER BY m.price, Menu.class");
        List<Menu> list = (List<Menu>) query.getResultList();
        for (Menu m : list)
            System.out.println(m);
    }

    public static void viewDishesWithDiscount(Scanner sc){
        Query query = em.createQuery("SELECT m FROM Menu m WHERE (m.discount IS NOT NULL) AND (m.discount = 0), Menu.class ");
        List<Menu> list = (List<Menu>) query.getResultList();
        for (Menu m : list)
            System.out.println(m);
    }

    public static void viewDishesUpTo1Kg(Scanner sc){
        double sum = 0;
        Query query = em.createQuery("SELECT m FROM Menu m, Menu.class ");
        List<Menu> list = (List<Menu>) query.getResultList();
        for (Menu m : list) {
            sum += m.getWeight();
            if (sum < 1.0) {
                System.out.println(m);
            }
        }
    }
}

