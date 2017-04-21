package io.github.reeceyang.old;

public class Utilities {

    public static int maxIndex(int[] array) {
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        int length = array.length;
        for (int i = 0 ; i < length; i++) {
            if (array[i] > max) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int minIndex(int[] array) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        int length = array.length;
        for (int i = 0 ; i < length; i++) {
            if (array[i] < min) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void quit() {
        System.exit(0);
    }
}
