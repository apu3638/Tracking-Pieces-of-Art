package com.apu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        setDefaultList();
        showAllOptions();

    }


    private static void showAllOptions() {
        System.out.println("\n\nTracking Pieces of Art");
        System.out.println(
                """
                        #1: Add a Product
                        #2: Edit a Product
                        #3: Delete a Product
                        #4: See all Product
                        #5: See overall Info
                        """
        );

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number what you want: ");
        int option = scanner.nextInt();


        if (option == 1) {
            addProduct();
        } else if (option == 2) {
            editProduct();
        } else if (option == 3) {
            deleteProduct();
        } else if (option == 4) {
            seeAllProduct();
        } else if (option == 5) {
            seeOverallInfo();
        }
    }

    private static void seeOverallInfo() {
        if (productList.isEmpty()) {
            System.out.println("No products available");
            showAllOptions();
            return;
        }

        int totalConTime = 0;
        int totalDays = 0;
        double totalRating = 0.0;
        double avgRating = 0.0;

        int totalBookConTime = 0;
        int totalMovieConTime = 0;
        int totalSeriesConTime = 0;

        int totalBookConDay = 0;
        int totalMovieConDay = 0;
        int totalSeriesConDay = 0;

        double avgBookRating = 0;
        double avgMovieRating = 0;
        double avgSeriesRating = 0;

        int bookCount = 0, movieCount = 0, seriesCount = 0;

        for (Product product : productList) {
            totalConTime += product.getConsumptionTime();
            totalDays += product.getTotalConsumptionDays();
            totalRating += product.getRating();

            if (product.getProductType() == ProductType.BOOK) {
                totalBookConTime += product.getConsumptionTime();
                totalBookConDay += product.getTotalConsumptionDays();
                avgBookRating += product.getRating();
                bookCount++;
            }
            if (product.getProductType() == ProductType.MOVIE) {
                totalMovieConTime += product.getConsumptionTime();
                totalMovieConDay += product.getTotalConsumptionDays();
                avgMovieRating += product.getRating();
                movieCount++;
            }
            if (product.getProductType() == ProductType.SERIES) {
                totalSeriesConTime += product.getConsumptionTime();
                totalSeriesConDay += product.getTotalConsumptionDays();
                avgSeriesRating += product.getRating();
                seriesCount++;
            }
        }

        avgBookRating = avgBookRating / bookCount;
        avgMovieRating = avgMovieRating / movieCount;
        avgSeriesRating = avgSeriesRating / seriesCount;


        int totalConsumable = productList.size();
        if (totalRating != 0.0) {
            avgRating = totalRating / totalConsumable;
        }

        System.out.println(
                "Total consumption time : " + totalConTime +" \n"+
                        "Book consumption time : " +totalBookConTime +" \n"+
                        "Movie consumption time : " + totalMovieConTime +" \n"+
                        "Series consumption time : " + totalSeriesConTime +" \n"+
                        "Total consumption days : " + totalDays +" \n"+
                        "Book consumption days : " + totalBookConDay +" \n"+
                        "Movie consumption days : " + totalMovieConDay +" \n"+
                        "Series consumption days : " + totalSeriesConDay +" \n"+
                        "Avg rating : " + avgRating +" \n"+
                        "Book Avg rating : " + avgBookRating +" \n"+
                        "Movies Avg rating : " + avgMovieRating +" \n"+
                        "Series Avg rating : " + avgSeriesRating +" \n"+
                        "Total number of consumable : " + totalConsumable +" \n"+
                        "Total number of Books : " + bookCount +" \n"+
                        "Total number of Movies : " + movieCount +" \n"+
                        "Total number of Series : " + seriesCount
        );

        showAllOptions();
    }

    private static void seeAllProduct() {
        if (productList.isEmpty()) {
            System.out.println("No products available");
            showAllOptions();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product Info");

        System.out.println(
                """
                        Consumable type :
                        #1: Book
                        #2: Series
                        #3: Movie
                        """
        );
        System.out.print("Enter Consumable type number :");
        int type = scanner.nextInt();
        ProductType productType
                = (type == 1) ? ProductType.BOOK
                : (type == 2) ? ProductType.SERIES
                : (type == 3) ? ProductType.MOVIE
                : ProductType.OTHERS;

        List<Product> list = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductType() == productType
                    && !product.isDeleted()) {
                list.add(product);
            }
        }
        if (list.isEmpty()) {
            System.out.println("This type of products not available");
            showAllOptions();
        } else {
            showProduct(list);
        }
    }

    private static void showProduct(List<Product> list) {
        System.out.format("%20s%20s%16s%16s", "Product Name", "Con. days", "Con. Hour", "rating");
        for (Product product : list) {
            System.out.println();
            System.out.format("%20s%20s%16s%16s", product.getName(), product.getConsumptionTime(), product.getConsumptionTime(), product.getRating());
        }

        showAllOptions();
    }

    private static void deleteProduct() {
        if (productList.isEmpty()) {
            System.out.println("No products available");
            showAllOptions();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name :");
        String name = scanner.nextLine();

        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setDeleted(true);
                System.out.println("Product deleted successfully");
                showAllOptions();
                return;
            }
        }

        System.out.println("No product found in this name");
        deleteProduct();
    }

    private static void editProduct() {

    }

    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product Info");

        System.out.println(
                """
                        Consumable type :
                        #1: Book
                        #2: Series
                        #3: Movie
                        """
        );
        System.out.print("Enter Consumable type number :");
        int type = scanner.nextInt();
        ProductType productType
                = (type == 1) ? ProductType.BOOK
                : (type == 2) ? ProductType.SERIES
                : (type == 3) ? ProductType.MOVIE
                : ProductType.OTHERS;
        scanner.nextLine();

        System.out.print("Name :");
        String name = scanner.nextLine();

        System.out.print("Starting date in YYYY-MM-DD :");
        String startingDate = scanner.nextLine();

        System.out.print("Ending date in YYYY-MM-DD :");
        String endingDate = scanner.nextLine();

        System.out.print("Total consumption time in hours :");
        int consumptionTime = scanner.nextInt();

        System.out.print("Person Rating :");
        double rating = scanner.nextDouble();

        System.out.print("Total days of consumption :");
        int totalDays = scanner.nextInt();

        Product product
                = new Product(productType, name, startingDate,
                endingDate, consumptionTime, rating, totalDays);

        productList.add(product);

        System.out.println("Product added successfully !");

        showAllOptions();
    }

    private static void setDefaultList() {
        productList.add(
                new Product(
                        ProductType.MOVIE, "Harry potter", "2021-04-01", "2021-06-02", 20
                        , 9.0, 30)
        );

        productList.add(
                new Product(
                        ProductType.BOOK, "Titanic", "2021-05-01", "2021-07-02", 30
                        , 7.0, 30)
        );

        productList.add(
                new Product(
                        ProductType.SERIES, "Games of thrones", "2021-01-01", "2021-09-02", 50
                        , 8.0, 30)
        );




    }
}
