package QuickSortVisualizer;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 30;
    private AlgoFrame frame;

    private QuickSortData data;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType) {

        data = new QuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
               run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    private void run() {

        setData(-1, -1, -1, -1, -1, -1);
        quickSort(0, data.N() - 1);
        setData(-1, -1,-1, -1, -1, -1);

    }

    private void quickSort(int l, int r) {
        if (l > r)
            return;
        if (l == r) {
            setData(l, r, l, -1, -1, -1);
            return;
        }
        setData(l, r, -1, -1, -1, -1);

        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int lt = l;
        int gt = r + 1;
        int i = l+1;
        setData(l, r, -1, l, lt, gt);

        while (i < gt) {
            if (data.get(i) < v) {
                data.swap(i++, ++lt);
            }
            else if (data.get(i) > v) {
                data.swap(i, --gt);
            }
            else {
                i++;
            }
            setData(l, r, -1, l, i, gt);
        }
        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);

        quickSort(l, lt-1);
        quickSort(gt, r);
    }


    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while(i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i ++;
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Identical);

    }
}
