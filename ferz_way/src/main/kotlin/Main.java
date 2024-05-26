/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D://algos//ferz_way//src//main//kotlin//input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("D://algos//ferz_way//src//main//kotlin//output.txt"));

        int n = Integer.parseInt(reader.readLine());
        List<int[]> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] coordinates = reader.readLine().split(" ");
            cities.add(new int[]{Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])});
        }
        reader.close();

        List<Integer> route = nearestNeighbor(cities);
        long totalDistance = calculateTotalDistance(route, cities);

        for (int city : route) {
            writer.write(city + 1 + " ");
        }
        writer.newLine();
        writer.write(String.valueOf(totalDistance));
        writer.newLine();
        writer.close();
    }

    // Находит ближайший город к заданному городу
    private static int findNearestCity(int[] city, List<int[]> cities, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int nearestCity = -1;
        for (int i = 0; i < cities.size(); i++) {
            if (!visited[i]) {
                int distance = Math.abs(city[0] - cities.get(i)[0]) + Math.abs(city[1] - cities.get(i)[1]);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCity = i;
                }
            }
        }
        return nearestCity;
    }

    // Ищет маршрут с помощью алгоритма ближайшего соседа
    private static List<Integer> nearestNeighbor(List<int[]> cities) {
        List<Integer> route = new ArrayList<>();
        boolean[] visited = new boolean[cities.size()];
        route.add(0);
        visited[0] = true;
        int currentCity = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            int nearestCity = findNearestCity(cities.get(currentCity), cities, visited);
            route.add(nearestCity);
            visited[nearestCity] = true;
            currentCity = nearestCity;
        }
        route.add(0); // Возвращаемся в начальный город
        return route;
    }

    // Вычисляет общее расстояние маршрута
    private static long calculateTotalDistance(List<Integer> route, List<int[]> cities) {
        long totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            int[] city1 = cities.get(route.get(i));
            int[] city2 = cities.get(route.get(i + 1));
            totalDistance += Math.abs(city1[0] - city2[0]) + Math.abs(city1[1] - city2[1]);
        }
        return totalDistance;
    }
}
*/
