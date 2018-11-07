public class Arrays {
    public static int[] insert(int[] arr, int item, int position) {
        int[] newArr = new int[arr.length + 1];
        position = Math.min(position, arr.length);
        for (int i = 0; i < position; i++) {
            newArr[i] = arr[i];
        }
        newArr[position] = item;
        for (int i = position + 1; i < newArr.length; i++) {
            newArr[i] = arr[i - 1];
        }
        return newArr;
    }

    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int indexSwitch = arr.length - i - 1;
            int temp = arr[i];
            arr[i] = arr[indexSwitch];
            arr[indexSwitch] = temp;
        }
    }

    public static int[] replicate(int[] arr) {
        // First calculate the length. Just sum up the items of the list.
        int sum = 0;
        for (int item: arr) sum += item;
        int[] repArray = new int[sum];
        int p = 0;
        for (int item: arr) {
            for (int count = 0; count < item; count++) {
                repArray[p++] = item;
            }
        }
        return repArray;
    }

    public static void main(String[] args) {
        int[] l = {3, 2, 1};
        l = replicate(l);
        for (int item: l) {
            System.out.println(item);
        }
    }
}