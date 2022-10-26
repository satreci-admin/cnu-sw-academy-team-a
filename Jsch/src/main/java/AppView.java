import java.util.Scanner;

public final class AppView {
    private static Scanner scanner = new Scanner(System.in);

    private AppView(){ }

    public static void outputLine(String aString){
        System.out.println(aString);
    }

    private static void output(String aString){
        System.out.print(aString);
    }

    public static int inputInt() {
        int num;
        String scannedToken;
        while(true) {
            scannedToken = AppView.scanner.next();
            try {
                num = Integer.parseInt(scannedToken);
                return num;
            }
            catch(NumberFormatException e) {
                AppView.outputLine("정수가 아닙니다");
            }
        }
    }


}