package ShuffleExp;

public class ShuffeExp {
    private int n, m;
    private int N;

    public ShuffeExp(int N, int n, int m) {

        if (N <= 0)
            throw new IllegalArgumentException("N must be larger than 0!");

        if (n < m)
            throw new IllegalArgumentException("n must be lager than or equal to m!");

        this.N = N;
        this.n = n;
        this.m = m;
    }

    public void run() {

        int[] freq = new int[n];
        int[] arr = new int[n];
        for (int i = 0; i < N; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < n; j++) {
                freq[j] += arr[j];
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(i + ":" + (double)freq[i]/N);
        }
    }

    private void shuffle(int[] arr) {
        for (int i = 0; i < n; i++) {
            int x = (int)(Math.random() * (n - i)) + i;
            swap(arr, i, x);
        }
    }

    private void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    private void reset(int[] arr) {

        for (int i = 0; i < m; i++) {
            arr[i] = 1;
        }

        for (int i = m; i < n; i++) {
            arr[i] = 0;
        }
    }

    public static void main(String[] args) {

        int N = 10000000;
        int n = 20;
        int m = 5;

        ShuffeExp exp = new ShuffeExp(N, n, m);

        exp.run();

    }
}
