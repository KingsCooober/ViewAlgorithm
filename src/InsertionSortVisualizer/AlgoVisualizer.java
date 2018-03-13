package InsertionSortVisualizer;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 30;
    private AlgoFrame frame;

    private InsertionSortData data;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, InsertionSortData.Type dataType) {

        data = new InsertionSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insertion Sort Visualization!", sceneWidth, sceneHeight);

            new Thread(() -> {
               run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }

    private void run() {

        setData(0, -1);
        for (int i = 0; i < data.N(); i++) {
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--){
                data.swap(j , j - 1);
                setData(i+1, j-1);
            }
        }
        setData(data.N(), -1);
    }

    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, InsertionSortData.Type.NearlyOrdered );

    }
}
