package ShareMoneyQuestion;

import java.awt.*;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 30;
    private AlgoFrame frame;

    private int[] money;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

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
            // 分钱问题
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

            // 更新数据
            for (int k = 0; k < 30; k++)
                for (int i = 0; i < money.length; i++) {
//                    if (money[i] > 0) {
                        int j = (int) (Math.random() * money.length);
                        money[i] -= 1;
                        money[j] += 1;
//                    }
                }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 600;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);

    }
}
