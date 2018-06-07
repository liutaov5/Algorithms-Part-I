/**
 * @author liutao
 * @date 2018/6/7
 */

public class Social {
    static String[] logs = new String[]{"2018-1-1 10:23:23,0,3", "2018-1-2 11:23:23,1,2", "2018-1-3 10:23:23,0,5"
            , "2018-2-8 10:23:23,5,1", "2018-3-7 10:23:23,6,4", "2018-3-8 10:23:23,4,1", "2018-4-1 10:23:23,0,7", "2018-4-5 10:23:23,0,3"
            , "2018-5-1 10:23:23,9,3", "2018-6-1 10:23:23,5,2", "2018-6-4 10:23:23,8,2", "2018-6-10 10:23:23,7,4"};

    //store root postion
    static int[] roots = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    //size
    static int[] sizes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    static int count = 10;

    public static void main(String[] args) {
        for (String log : logs) {
            int left = Integer.parseInt(log.split(",")[1]);
            int right = Integer.parseInt(log.split(",")[2]);
            if (isConnect(left, right)) {
                continue;
            } else {
                union(left, right);
            }
            if (count == 1) {
                System.out.println("the earliest time is " + log.split(",")[0]);
                break;
            }
        }
    }

    public static boolean isConnect(int left, int right) {
        return getRoot(left) == getRoot(right);
    }

    /**
     * weighted quick-union
     */
    public static void union(int left, int right) {
        int leftRootPosition = getRoot(left);
        int rightRootPosition = getRoot(right);
        if (sizes[leftRootPosition] < sizes[rightRootPosition]) {
            roots[leftRootPosition] = rightRootPosition;
            sizes[rightRootPosition] = sizes[leftRootPosition] + sizes[rightRootPosition];
        }else {
            roots[rightRootPosition] = leftRootPosition;
            sizes[leftRootPosition] = sizes[leftRootPosition] + sizes[rightRootPosition];
        }
        count--;
    }

    public static int getRoot(int position) {
        while (roots[position] != position) {
            position = roots[position];
        }
        return position;
    }
}
