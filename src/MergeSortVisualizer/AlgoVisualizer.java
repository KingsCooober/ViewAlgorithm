package MergeSortVisualizer;

import java.awt.*;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 30;
    private AlgoFrame frame;

    private MergeSortData data;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new MergeSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization!", sceneWidth, sceneHeight);

            new Thread(() -> {
               run();
            }).start();
        });
    }

    private void run() {

        setData(-1, -1, -1);
        mergeSortDown2Up();
//        mergeSort(0, data.N() - 1);
        setData(0, data.N() - 1, data.N() - 1);
    }

    public void mergeSortDown2Up() {
        for (int sz = 1; sz < data.N(); sz *= 2) {
            for (int i = 0; i < data.N() - sz; i += sz + sz) {
                merge(i, i+sz-1, Math.min(i+sz+sz-1, data.N()-1));
            }
        }
    }

    public void mergeSort(int l, int r) {
        if (l >= r)
            return;

        setData(l, r, -1);

        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r+1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                data.numbers[k] = aux[j-l];
                j++;
            }
            else if (j > r) {
                data.numbers[k] = aux[i-l];
                i++;
            }
            else if (aux[i-l] < aux[j-l]) {
                data.numbers[k] = aux[i-l];
                i++;
            }
            else {
                data.numbers[k] = aux[j-l];
                j++;
            }
            setData(l, r, k);
        }
    }

    public void setData(int l, int r, int mergeIndex) {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);

    }
}
