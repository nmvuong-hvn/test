import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        int []a = new int []{
                1 ,13 ,9, 3 ,7, 2};
//        problem3(a);
        problem5("BBBB", 1);
    }

    public static void problem5(String a, int block ) {
        int left =0 , right = 0 , n = a.length();
        int cntB =0 ;
        int startB = 0 ;
        int ans = 0;
        int []counter = new int[26];
        Arrays.fill(counter, 0);
        while (left < n) {
            char c = a.charAt(left);
            counter[c - 'A']++;
            System.out.println(left +" "+ c);
            if (counter['B' - 'A'] != 0){
                ans++;
                counter['B' - 'A']--;
                left += block - 1;
            }

            left++;
        }
        System.out.println(ans);
    }
    public static void problem4(String a){
        int [] counter = new int[26];
        Arrays.fill(counter,0);
        int left = 0 , n = a.length() , right = 0 ;
        StringBuilder ans = new StringBuilder();
        char temp = a.charAt(0);
        while (left < n) {
            counter[a.charAt(left) - 'a']++;
            if (counter[temp-'a'] == 2) {
                if (left + 1 < a.length()) {
                    temp = a.charAt(left + 1);
                }
                ans.append(a.charAt(left));
                while (right <= left && right < n) {
                    counter[a.charAt(right) - 'a']--;
                    right++;
                }
            }

            left++;
        }
        System.out.println(ans);
    }
    public static void problem3(int []a){
        List<Integer> tempA = new ArrayList<>() ;
        for (int data : a ){
            tempA.add(data);
        }
        Collections.sort(tempA);

        int left =0 , right = tempA.size() - 1;
        int ans =0 ;
        while (left <= right){
            ans += (tempA.get(right)- tempA.get(left));
            left++;
            right--;
        }
        System.out.println(ans);

    }
    public static void problem2(int []a) {
        int sumTotal = 0;
        for(int i = 0 ; i < a.length ;i++) sumTotal += a[i];
        if (sumTotal % 3 != 0){
            System.out.println(0);
        }else {
            int indexFirst = 0;
            int sumOfFirst =0 ;
            for (int i = 0 ; i < a.length ;i++){
                sumOfFirst += a[i];
                indexFirst = i;
                if (sumOfFirst == sumTotal/3) break;
            }
            int l = indexFirst + 1 ;

            int sumOfLast = 0 ;
            int indexLast = 0;
            for (int i = a.length - 1; i>= 0 ; i--) {
                sumOfLast += a[i];
                indexLast = i ;
                if (sumOfLast == sumTotal/3 ) break;
            }
            if (indexFirst >= indexLast ) {
                System.out.println(0);
            }else {
                int ans =0 ;
                int left = indexFirst + 1 ;
                int right = left + 1;
                int sumMiddle = 0 ;
                while (left < indexLast){
                    sumMiddle += a[left];
                    if (sumMiddle == sumTotal/3) ans++;
                    while ((sumMiddle + a[left + 1] ) == sumTotal/3 && left + 1 < indexLast) {
                        ans++;
                        left++;
                    }
                    sumMiddle = 0;
                    left++;
                }
                System.out.println(ans);
            }
        }
    }
    public static void problem1(){
        int a[] = new int[]{1,5,5,9,10 ,12,14};
        int k = 3 ;
        int left = 0 , right = 0;
        int ans =0 ;
        while (left < a.length){
            while ( right + 1 < a.length && a[right + 1] - a[left] <= k ) right++;
            ans += right - left;
            left++;
        }
        System.out.println(ans);
    }
}