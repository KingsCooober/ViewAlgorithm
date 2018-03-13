package RecursiveCircle;

import java.awt.*;

public class AlgoVisualizer {

    private AlgoFrame frame;
    private static int DELAY = 40;
    private CircleData data;

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {

        // 初始化数据
        int R = Math.min(sceneWidth, sceneHeight) / 2 - 2;
        data = new CircleData(sceneWidth/2, sceneHeight/2, R, R/2, 2);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);

            new Thread(() -> {
               run();
            }).start();
        });
    }

    private void run() {

        // 绘制数据
        setData();

    }

    private void setData() {

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 700;
        int sceneHeight = 700;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);

    }
}
