package com.example.simplerpa.service.Jsch;

import com.example.simplerpa.repository.RobotRepository;

public class SSH_Controller {

    //MVC - AppController.  Server will run SSH_Controller.run
    //Private Instance Variables
    private final RobotRepository robotRepository;

    public SSH_Controller(RobotRepository robotRepository, int robotId) {
        this.robotRepository = robotRepository;
        this.robotId = robotId;
    }


    private Robot _robot; //지금은 변수 하나지만, 보다 많은 로봇의 삽입과 삭제 시 보다 효율적이고 빠르게 하기 위해 로봇 자료구조와 알고리즘을 제안

    private String      _localHostUser;      // "sysolab";
    private String      _localHostIp;        //= "192.168.56.1"; //sysolab(리눅스 로컬서버의 유저이름)

    private String      _remoteHostUser;
    private String      _remoteHostIp;

    private Integer     _port = 22;

    private String      _robotPassword ;
    private String      _publicKeyPath ;    //= "/home/sysolab/.ssh";

    private int robotId;



    //Getter & setter

    public Robot robot() { return this._robot; }
    public void set_robot(Robot newRobot) { this._robot = newRobot; }

    public String localHostUser() {return _localHostUser;}
    public void set_localHostUser(String user) {this._localHostUser = user;}

    public String localHostIp() {return this._localHostIp;}
    public void set_localHostIp(String newlocalHostIp) {this._localHostIp = newlocalHostIp;}

    public String remoteHostIp() {return this._remoteHostIp;}
    public void set_remoteHostIp(String newRemoteHostIp) {this._remoteHostIp = newRemoteHostIp;}

    public String remoteHostUser() { return this._remoteHostUser;}
    public void set_remoteHostUser(String newRemoteHostUser) { this._remoteHostUser = newRemoteHostUser;}

    public String robotPassword() { return this._robotPassword; }
    public void set_robotPassword(String newRobotPassword) { this._robotPassword = newRobotPassword; }


    //Constructor


    //Private Methods



    //public method
    public void runSSH(){

        //AppView.outputLine("user :" + this.localHostUser());
        //AppView.outputLine("localHost : " + this.localHostIp());

        // localserver Ip : sysolab 168.188.129.195

        /* <list of test remoteHost (robot) Ip  //테스트용으로 적어놓은거라 무시해도
        (1) neusi_2
        168.188.128.98

        (2) sslab
        168.188.129.195

        (3) rasbian
        168.188.128.76
         */

        /*
        <<< 여기에 사용자가 입력한 로봇의 정보를 DB로부터 불러오는 코드 부분을 구현한다 >>>
        임시로 아래와 같이 넘겨줄 인자를 저장함
         */

        this.set_localHostUser(System.getProperty("user.name"));

        /*
        set_localHostIp("168.188.129.195");
        set_remoteHostUser("rasbian");
        set_remoteHostIp("168.188.128.76"); // robot 의 ip 주소(다양하게 변경해서 실험)
        set_robotPassword("rasbian333");
        String localDirPathAndName = "/home/sysolab/test1.sh"; //<======== 로컬 서버의 특정한 디렉토리에 쉘스크립트가 보관되어 있음.
        //String fileName = "test1.sh";  //localDirPathAndName 에 쉘스크립트 이름까지 풀 경로를 저장했는데, 쉘스크립트 이름 따로 넣을수
        String robotDirPath = "/home/rasbian";
         */

        set_remoteHostUser(robotRepository.findById(robotId).get().getUser()); //rasbian
        set_remoteHostIp(robotRepository.findById(robotId).get().getIp()); // robot 의 ip 주소(다양하게 변경해서 실험)
        set_robotPassword(robotRepository.findById(robotId).get().getPassword());
        String localDirPathAndName = "/home/sysolab/test1.sh"; //<======== 로컬 서버의 특정한 디렉토리에 쉘스크립트가 보관되어 있음.
        //String fileName = "test1.sh";  //localDirPathAndName 에 쉘스크립트 이름까지 풀 경로를 저장했는데, 쉘스크립트 이름 따로 넣을수
        String robotDirPath = "/home/rasbian";


        //(1) 로봇 객체를 생성한다. (리눅스 서버(localHost)의 정보를 생성자에게 넘겨준다
       /*
       전달할 인자의 내용 :
       1. localHostUser :           로컬서버 OS 사용자 이름(보통 User 폴더의 이름과 같음)
       2. remoteHostUser :          리모트서버(== robot) 의 사용자 이름 (작업명세서의 DB에 저장되어 있어야 한다)
       3. remoteHostIp :            리모트서버(== robot) 의 IP
       4. _port :                   포트번호 (현재는 22로 고정, 만약 추후 필요하다면 이것도 정보를 받아서 넘겨줄수있음)
       5. robotPassword :           리모트서버(== robot) 의 root 패스워드 (필요함)
       6. localDirPathAndName :     로봇에게 전달할 쉘스크립트 파일이 저장되어 있는 로컬서버경로
       7. robotDirPath  :           쉘 스크립트 파일을 저장할 리모트서버(== robot) 의 경
        */

        // 로봇 생성자에 인자를 넘겨준다.
        this.set_robot(new Robot(this.localHostUser(),this.remoteHostUser(),this.remoteHostIp(), _port , this.robotPassword()
                ,localDirPathAndName, robotDirPath));

        //로봇과 연결한다
        this.robot().connectRobot();


    }

}