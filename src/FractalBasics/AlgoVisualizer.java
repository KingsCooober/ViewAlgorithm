package FractalBasics;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlgoVisualizer {

    private AlgoFrame frame;
    private FractalData data;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int depth, double splitAngle) {

        // 初始化数据
        data = new FractalData(depth, splitAngle);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());

            new Thread(() -> {
               run();
            }).start();
        });
    }

    private void run() {

        // 绘制数据
        setData(data.depth);

    }

    private void setData(int depth) {

        if (depth >= 0)
            data.depth = depth;

        frame.render(data);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9') {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {

        int depth = 6;
        double splitAngle = 60.0;
        int sceneWidth = 800;
        int sceneHeight = 800;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, depth, splitAngle);

    }
}
