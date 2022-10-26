public class _Main {

    public static void main(String[] args) {

        //일단 서버 대신 main함수가 실행

        SSH_Controller server = new SSH_Controller();
        //all things that main method do is just call runServer() method.

        server.runSSH();

        /*
        readme :
        서버에서 추가할 class
         1. robot 객체를 생성하고 인자를 전달하고 로봇객체 메서드를 실행시키는 "class SSH_Controller" (전체 코드에 맞게 수정해야 함)
         2. robot 객체를 위한 "class Robot" (수정할 필요 없도록 구현(문제가 있으면 수정))
         */

    }

}