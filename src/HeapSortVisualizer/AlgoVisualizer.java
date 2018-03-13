package HeapSortVisualizer;

import java.awt.*;


public class AlgoVisualizer {

    private static int DELAY = 20;

    private HeapSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, HeapSortData.Type dataType){

        // 初始化数据
        data = new HeapSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Heap Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, HeapSortData.Type.Default);
    }

    public void run(){
        setData(data.N());

        for (int i = (data.N()-1-1)/2; i >= 0; i--) {
            shiftDown(data.N(), i);
        }

        for (int i = data.N()-1; i > 0; i--) {
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }

        setData(0);
    }

    private void shiftDown(int n, int k) {
        while (2*k+1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data.get(j+1) > data.get(j)) {
                j = j + 1;
            }

            if (data.get(k) >= data.get(j))
                break;
            data.swap(k, j);
            setData(data.heapIndex);

            k = j;
        }
    }


    private void setData(int heapIndex){
        data.heapIndex = heapIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, HeapSortData.Type.Default);

    }
}