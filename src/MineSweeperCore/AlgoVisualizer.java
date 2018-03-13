package MineSweeperCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 32;
    private AlgoFrame frame;
    private MineSweeperData data;

    private int sceneWidth;
    private int sceneHeight;

    public AlgoVisualizer(int N, int M, int mineNumber) {

        // 初始化数据
        data = new MineSweeperData(N, M, mineNumber);
        this.sceneWidth = M * blockSide;
        this.sceneHeight = N * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        setData(false, -1, -1);
    }

    private void setData(boolean isLeftClicked, int x, int y) {
        if (data.inArea(x, y)) {
            if (isLeftClicked) {
                if (data.isMines(x, y)) {
                    // TODO Game Over
                    data.open[x][y] = true;
                }
                else {
                    data.open(x, y);
                }
            }
            else
                data.flags[x][y] = !data.flags[x][y];
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event){

            event.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();

            int x = pos.y / h;
            int y = pos.x / w;
            System.out.println(x + " , " + y);

            if(SwingUtilities.isLeftMouseButton(event))
                setData(true, x, y);
            else if(SwingUtilities.isRightMouseButton(event))
                setData(false, x, y);

        }
    }


    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 40;

        AlgoVisualizer visualizer = new AlgoVisualizer(N, M, mineNumber);
    }
}
