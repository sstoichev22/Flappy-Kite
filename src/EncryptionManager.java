import java.util.ArrayList;

public class EncryptionManager {
    public static int encrypt(int num, int base) {
        String s = ""+num;
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 1; j <= c; j *= base) arr.add(0, j);
            StringBuilder sb = new StringBuilder();
            for (int j : arr) {
                sb.append(c / j);
                c -= (c / j) * j;
            }
            r.append((char)Integer.parseInt(sb.toString()));
        }
        return Integer.parseInt(r.toString());
    }

    public static int decrypt(int num, int base) {
        String s = ""+num;
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String t = "" + (int)(s.charAt(i));
            int f = 0;
            for (int j = t.length() - 1, m = 1; j >= 0; j--, m *= base) {
                f += (t.charAt(j) - '0') * m;
            }
            r.append((char) (f));
        }
        return Integer.parseInt(r.toString());
    }
}
