import java.util.*;

class KnapsackItem {
    int weight;
    int value;

    public KnapsackItem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class KnapsackSolver {
    public static int[][] knapsack(KnapsackItem[] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (items[i - 1].weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i - 1].weight] + items[i - 1].value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp;
    }

    public static List<KnapsackItem> getSelectedItems(KnapsackItem[] items, int capacity, int[][] dp) {
        List<KnapsackItem> selectedItems = new ArrayList<>();
        int n = items.length;
        int w = capacity;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(items[i - 1]);
                w -= items[i - 1].weight;
            }
        }

        return selectedItems;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();
        KnapsackItem[] items = new KnapsackItem[numItems];

        for (int i = 0; i < numItems; i++) {
            System.out.println("Enter weight and value for item " + (i + 1) + ":");
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items[i] = new KnapsackItem(weight, value);
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        int[][] dp = knapsack(items, capacity);
        List<KnapsackItem> selectedItems = getSelectedItems(items, capacity, dp);

        System.out.println("Maximum value: " + dp[items.length][capacity]);
        System.out.println("Selected items:");
        for (KnapsackItem item : selectedItems) {
            System.out.println("Weight: " + item.weight + " Value: " + item.value);
        }

        scanner.close();
    }
    /*
     * Test Case:
     * Input:
     * Enter the number of items: 3
     * Enter weight and value for item 1:
     * 2 10
     * Enter weight and value for item 2:
     * 3 15
     * Enter weight and value for item 3:
     * 5 20
     * Enter the capacity of the knapsack: 7
     * 
     * Output:
     * Maximum value: 30
     * Selected items:
     * Weight: 5 Value: 20
     * Weight: 2 Value: 10
     */
}
