public class Main {
    public static void main(String[] args) {
        int[] elements = {5, 3, 7, 8, 9, 2, 4, 12};
        int theMaxElement = getMaxElement(elements);
        System.out.println("The Max element is " +
                theMaxElement);
    }
    public static int getMaxElement(int[] elementList) {
        int maxElement = 0, idx = 0;
        while (idx < elementList.length) {
            if (elementList[idx] > maxElement) {
                maxElement = elementList[idx];
            }
            idx++;
        }
        return maxElement;
    }
}