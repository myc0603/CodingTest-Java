package baekjoon.clazz.class5.g1_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] board;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[] red, blue;

    static final int FAIL= 100;

    static void printBoard() {
        System.out.println("== print board ==");
        for (int i = 0; i < n; ++i) {
            System.out.println(board[i]);
        }
        System.out.println();
    }

    static void moveRedBlueAndPrint(int dir) {
        move(red, movedResult(red, dir));
        move(blue, movedResult(blue, dir));
        printBoard();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') red = new int[]{i, j};
                else if (board[i][j] == 'B') blue = new int[]{i, j};
            }
        }

//        moveRedBlueAndPrint(0);
//        moveRedBlueAndPrint(1);

        int ans = FAIL;
        for (int i = 0; i < 4; ++i) {
            ans = Math.min(ans, dfs(1, i));
        }
        System.out.println(ans > 10 ? -1 : ans);
    }

    static int dfs(int cnt, int dir) {
        if (cnt > 10) return FAIL;

        int result = FAIL;
        boolean moveRedOneMore = false;
        int[] initialRedPos = red;
        int[] initialBluePos = blue;

        int[] redResult = movedResult(red, dir);
        boolean redDoesNotMove = red[0] == redResult[0] && red[1] == redResult[1];

        if (isGoalIn(redResult)) result = cnt; // 이후에 파란 구슬이 골인해서 return FAIL 할 수 있도록 여기서 return X
        else if (isBlockedByMarvel(redResult, dir)) moveRedOneMore = true; // 파란구슬에 막히면 파란구슬 굴리고 red 한번 더 move

        move(red, redResult);

        int[] blueResult = movedResult(blue, dir);
        boolean blueDoesNotMove = blue[0] == blueResult[0] && blue[1] == blueResult[1];

        move(blue, blueResult);

        // red, blue 둘 다 안 움직이면 의미 x
        if ((redDoesNotMove && blueDoesNotMove) || isGoalIn(blueResult)) {
            recover(initialRedPos, initialBluePos);
            return FAIL;
        }

        if (moveRedOneMore) {
            redResult = movedResult(redResult, dir);
            if (isGoalIn(redResult)) result = cnt;
            move(red, redResult);
        }

//        System.out.println("cnt = " + cnt + ", dir = " + dir);
//        printBoard();

        if (result == FAIL) {
            for (int i = 0; i < 4; ++i) {
                if ((dir + 2) % 4 == i) continue;
                result = Math.min(result, dfs(cnt + 1, i));
            }
        }

        recover(initialRedPos, initialBluePos);
        return result;
    }

    static void move(int[] start, int[] end) {
        char marvelType = board[start[0]][start[1]];
        boolean isGoalIn = board[end[0]][end[1]] == 'O';

        if (marvelType == 'R') red = end;
        else blue = end;

        board[start[0]][start[1]] = '.';
        if (!isGoalIn) board[end[0]][end[1]] = marvelType;
    }

    static int[] movedResult(int[] start, int dir) {
        int y = start[0], x = start[1];
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        while (inMap(ny, nx) && (board[ny][nx] == '.')) {
            y = ny;
            x = nx;

            ny = y + dy[dir];
            nx = x + dx[dir];
        }

        if (board[ny][nx] == 'O') {
            y = ny;
            x = nx;
        }

        return new int[]{y, x};
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static boolean isBlockedByMarvel(int[] result, int dir) {
        int y = result[0] + dy[dir];
        int x = result[1] + dx[dir];
        return inMap(y, x) && (board[y][x] == 'R' || board[y][x] == 'B');
    }

    static boolean isGoalIn(int[] result) {
        return board[result[0]][result[1]] == 'O';
    }

    // 기존 위치와 바뀌지 않았을 때는 recover 로직 수정
    static void recover(int[] initialRedPos, int[] initialBluePos) {
        if (red[0] != initialRedPos[0] || red[1] != initialRedPos[1]) {
            board[initialRedPos[0]][initialRedPos[1]] = 'R';
            // 구멍에 빠진 경우 고려
            if (board[red[0]][red[1]] == 'R') board[red[0]][red[1]] = '.';
            red = initialRedPos;
        }

        if (blue[0] != initialBluePos[0] || blue[1] != initialBluePos[1]) {
            board[initialBluePos[0]][initialBluePos[1]] = 'B';
            if (board[blue[0]][blue[1]] == 'B') board[blue[0]][blue[1]] = '.';
            blue = initialBluePos;
        }
    }
}
