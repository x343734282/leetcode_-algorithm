import java.util.HashMap;
import java.util.Map;
class Solution {

    public int maxPoints(int[][] points) {
 if (points.length <= 2) return points.length;

        int max = 0;
        Map<Float, Integer> map = new HashMap<>();
        boolean ok = false;
        for (int i = 0; i <= points.length - 1; i++) {
            if (ok) break;

//            int c;
            map.clear();
//            System.out.println(i);

            for (var j = i + 1; j <= points.length - 1; j++) {

                float k1 = 0;
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                if (y1 == y2)
                    k1 = 0;
                else if (x1 == x2)
                    k1 = Float.MAX_VALUE;
                else
                    k1 = (float) (y1 - y2) / (x1 - x2);

if (map.containsKey( k1)) {
                    map.put( k1, map.get( k1) + 1);
                } else {
                    map.put(k1, 2);
                }    
                if (map.get(k1) > max) max = map.get(k1);
//                System.out.println(String.format("(%d,%d),(%d,%d)-----k:%f,count %d", points[i][0], points[i][1], points[j][0], points[j][1], k1, map.get(i + "" + k1)));
                if (max > (points.length / 2) && j == points.length - 1) {
//                    System.out.println("true:" + i);
                    ok = true;
                }
            }

        }
        return max;
    }
}