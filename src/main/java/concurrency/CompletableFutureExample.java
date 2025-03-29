import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 is running");
            return 42;
        }).thenApply(result -> {
            System.out.println("Task 2 received: " + result);
            return result * 2;
        }).thenAccept(finalResult -> {
            System.out.println("Final Result: " + finalResult);
        });
    }
}
