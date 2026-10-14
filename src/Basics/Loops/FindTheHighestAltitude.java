package Basics.Loops;

public class FindTheHighestAltitude {
    public static void main(String[] args) {
        int[] gain = {-5,1,5,0,-7};

        int result = largestAltitude(gain);
        System.out.println(result);
    }

    private static int largestAltitude(int[] gain) {
        int ans = 0;
        int height = 0;
        for (int i = 0; i < gain.length; i++) {
            height += gain[i];
            ans = Math.max(ans, height);
        }
        return ans;
    }
}
