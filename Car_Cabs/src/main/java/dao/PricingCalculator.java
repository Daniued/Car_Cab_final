package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PricingCalculator {

    // List of Cities
    private static final String[] cities = {
        "Batticaloa", "Trincomalee", "Ampara", "Bathula", "Hatton",
        "Jaffna", "Colombo", "Kandy", "Galle", "Matara"
    };

    // Distance Matrix (Distances in km, all below 10 km)
    private static final int[][] distances = {
        {0, 6, 4, 7, 5, 9, 3, 8, 6, 7},
        {6, 0, 5, 6, 4, 8, 5, 7, 6, 5},
        {4, 5, 0, 8, 7, 6, 5, 4, 7, 8},
        {7, 6, 8, 0, 3, 5, 6, 7, 8, 4},
        {5, 4, 7, 3, 0, 6, 5, 8, 7, 6},
        {9, 8, 6, 5, 6, 0, 7, 6, 8, 7},
        {3, 5, 5, 6, 5, 7, 0, 6, 5, 8},
        {8, 7, 4, 7, 8, 6, 6, 0, 5, 6},
        {6, 6, 7, 8, 7, 8, 5, 5, 0, 4},
        {7, 5, 8, 4, 6, 7, 8, 6, 4, 0}
    };

    // Rate per km based on vehicle type
    private static final Map<String, Double> ratePerKm = new HashMap<>() {{
        put("Car", 1.0);
        put("Van", 1.5);
        put("Bus", 2.0);
        put("Bike", 0.5);
    }};

    // Get Distance between Cities
    public static int getDistance(String pickup, String dropoff) {
        int startIndex = Arrays.asList(cities).indexOf(pickup);
        int endIndex = Arrays.asList(cities).indexOf(dropoff);
        if (startIndex >= 0 && endIndex >= 0) {
            return distances[startIndex][endIndex];
        }
        return -1; // Return -1 if cities are not found
    }

    // Calculate Price based on Distance and Vehicle Type
    public static double calculatePrice(String pickup, String dropoff, String vehicleType) {
        int distance = getDistance(pickup, dropoff);
        if (distance < 0) {
            throw new IllegalArgumentException("Invalid cities selected.");
        }
        double rate = ratePerKm.getOrDefault(vehicleType, 1.0);
        return distance * rate;
    }

    public static void main(String[] args) {
        String pickup = "Batticaloa";
        String dropoff = "Trincomalee";
        String vehicleType = "Car";
        int distance = getDistance(pickup, dropoff);
        double price = calculatePrice(pickup, dropoff, vehicleType);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Price: $" + price);
    }
}
