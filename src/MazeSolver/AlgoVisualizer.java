package MazeSolver;

import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {

    private static int blockSide = 7;
    private static int DELAY = 50;

    private MazeData data;
    private AlgoFrame frame;

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {

        // 初始化数据
        data = new MazeData(mazeFile);

        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze Solver Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {

        setData(-1, -1, true);

//        if (!go(data.getEntranceX(), data.getEntranceY()))
//            System.out.println("The maze has NO solution!");

//        Stack<Position> stack = new Stack<>();
//        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
//        stack.push(entrance);
//        data.visited[entrance.getX()][entrance.getY()] = true;
//
//        boolean isSolved = false;
//        while (!stack.empty()) {
//            Position curPos = stack.pop();
//            setData(curPos.getX(), curPos.getY(), true);
//
//            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
//                findPath(curPos);
//                isSolved = true;
//                break;
//            }
//
//            for (int i = 0; i < 4; i++) {
//                int newX = curPos.getX() + d[i][0];
//                int newY = curPos.getY() + d[i][1];
//
//                if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD
//                    && !data.visited[newX][newY]) {
//                    stack.push(new Position(newX, newY, curPos));
//                    data.visited[newX][newY] = true;
//                }
//
//            }
//        }
//        if (!isSolved) {
//            System.out.println("The maze has no Solution!");
//        }

        LinkedList<Position> queue = new LinkedList<>();
        Position entrance = new Position(data.getEntranceX(), data.getEntranceY());
        queue.addLast(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while (queue.size() != 0) {
            Position curPos = queue.pop();
            setData(curPos.getX(), curPos.getY(), true);

            if (curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()) {
                findPath(curPos);
                isSolved = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD
                    && !data.visited[newX][newY]) {
                    queue.addLast(new Position(newX, newY, curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        setData(-1, -1, true);
    }

    private void findPath(Position des) {
        Position cur = des;
        while (cur != null) {
            data.result[cur.getX()][cur.getY()] = true;
            setData(cur.getX(), cur.getY(), false);
            cur = cur.getPrev();
        }
    }


    private boolean go(int x, int y) {

        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in go function.");

        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];

            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD
                    && !data.visited[newX][newY]) {
                if(go(newX, newY))
                    return true;
            }
        }
        setData(x, y, false);

        return false;
    }


    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String mazeFile = "/home/kingsbam/Workstation/IdeaProjects" +
                "/ViewAlgorithm/src/MazeSolver/maze_101_101.txt";
        AlgoVisualizer visualizer = new AlgoVisualizer(mazeFile);
    }
}
