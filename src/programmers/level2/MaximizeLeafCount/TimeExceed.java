package programmers.level2.MaximizeLeafCount;

class TimeExceed {
    int leafCount, distCount;
    int distLimit, splitLimit;
    int answer;

    public int solution(int dist_limit, int split_limit) {
        this.distLimit = dist_limit;
        this.splitLimit = split_limit;

        dfs(1, 1);

        return answer;
    }

    void dfs(int curSplit, int currChildCount) {
        // 현재 노드의 모든 자식이 리프 노드일 때
        answer = Math.max(answer, leafCount + currChildCount);

        if (curSplit * 2 > splitLimit) return;

        // 현재 노드의 자식 중 분배 노드가 있을 때
        for (int d = 1; d <= currChildCount; ++d) {
            if (distCount + d > distLimit) break;

            distCount += d;
            leafCount += currChildCount - d;

            dfs(curSplit * 2, 2 * d);
            if (curSplit * 3 <= splitLimit) dfs(curSplit * 3, 3 * d);

            distCount -= d;
            leafCount -= currChildCount - d;
        }

    }
}
