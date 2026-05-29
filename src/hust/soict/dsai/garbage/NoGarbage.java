package hust.soict.dsai.garbage;

public class NoGarbage {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(i);
        }
        System.out.println("Done");
    }
}