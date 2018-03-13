package MonteCarloPiVisualizer;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 30;
    private AlgoFrame frame;

    private MonteCarloPiData data;
    private int N;
//    private static int DELAY = 40;
//    private int[] money;
//    private Circle[] circles;
//    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

       Circle circle = new Circle(sceneWidth/2, sceneWidth/2, sceneHeight/2);
       data = new MonteCarloPiData(circle);
       this.N = N;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
               run();
            }).start();
        });
    }

    private void run() {

        while (true) {
            //        蒙特卡洛求Pi
            for (int i = 0; i < N; i++) {
                if (i % 100 == 0) {
                    frame.render(data);
                    AlgoVisHelper.pause(DELAY);

                    System.out.println(data.estimatePi());
                }

                int x = (int) (Math.random() * frame.getCanvasWidth());
                int y = (int) (Math.random() * frame.getCanvasHeight());
                Point p = new Point(x, y);
                data.addPoint(p);
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 600;
        int sceneHeight = 600;
        int N = 1000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);

    }
}
