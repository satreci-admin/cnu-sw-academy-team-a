package com.example.simplerpa.service.Jsch;

import com.jcraft.jsch.*;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.ChannelExec;

import java.io.*;
import java.util.Properties;
//import java.io.BufferedReader;
//import com.jcraft.jsch.UserInfo;

public class Robot {

    //Private Instance Variables

    // Identify
    private String          _hostRobot;         // 접속할 로봇(pc)의 IP
    private Integer         _port;              // ssh의 포트번호 (SSH_PORT_NUMBER)
    private String          _localUser;
    private String          _robotUser;
    private String          _robotPassword;
    //private String          _publicKey;       // 공개키
    private String          _privateKeyPath;    // SSH private key 가 위치한 경로

    // Jsch
    private JSch            _jsch;
    private Session         _session;
    private ChannelExec     _execChannel; //private Channel         _channel;
    private ChannelSftp     _sftpChannel;

    //.sh path
    private String _localScriptFilePathAndName;
    private String _robotDirPath;


    //Constant
    private static final Integer SSH_PORT_NUMBER = 22; // 기본 ssh : 22

    //getter & setter
    private String robotIp() { return this._hostRobot; }
    private void set_RobotIp(String new_hostRobotIP) {this._hostRobot = new_hostRobotIP;}
    private Integer port() { return this._port;}
    private void set_port(Integer new_port) {this._port = new_port;}

    private String user() {return this._localUser;}
    private void set_user(String new_user) { this._localUser = new_user;}
    public String robotUser() {return this._robotUser;}
    public void set_robotUser(String newRobotUser) {this._robotUser = newRobotUser;}
    private String robotPassword() {return this._robotPassword;}
    private void set_robotPassword(String newRobotPassword) {this._robotPassword = newRobotPassword;}
    private JSch jsch() {return this._jsch;}
    private void set_jsch(JSch new_jsch) {this._jsch = new_jsch;}
    private Session session() {return this._session;}
    private void set_session(Session new_session) {this._session = new_session;}
    private ChannelExec execChannel() {return this._execChannel;}
    private void set_execChannel(ChannelExec new_channel) { this._execChannel = new_channel;}
    private ChannelSftp sftpChannel() {return this._sftpChannel;}
    private void set_sftpChannel(ChannelSftp new_sftpChannel) {this._sftpChannel = new_sftpChannel;}
    public void set_privateKeyPath(String new_privateKeyPath) { this._privateKeyPath = new_privateKeyPath;}
    public String privateKeyPath() {return this._privateKeyPath;}
    public String localScriptFilePathAndName() { return _localScriptFilePathAndName; }
    public void set_localScriptFilePathAndName(String newlocalDirPathAndName) { this._localScriptFilePathAndName = newlocalDirPathAndName;}
    public String robotDirPath() { return this._robotDirPath; }
    public void set_robotDirPath(String newRobotDirPath) { this._robotDirPath = newRobotDirPath; }


    //Constructor 생성자
    public Robot(String givenLocalUser,String givenRobotUser, String givenHostRobotIP, Integer givenPort, String givenPassword,
                 String givenLocalDirPathAndName, String givenRobotDirPath ){
        this.set_user(givenLocalUser);
        this.set_robotPassword(givenPassword);
        this.set_robotUser(givenRobotUser);
        this.set_RobotIp(givenHostRobotIP);

        if(givenPort == null){
            this.set_port(SSH_PORT_NUMBER); //22
        }else{
            this.set_port(givenPort);
        }
        this.set_privateKeyPath("/home/sysolab/.ssh/id_rsa"); // 본인 로컬 서버의 private ssh key 가 있는 디렉토리 경로
        // privateKeyPath 는 인자로 받지 않고 Rotbot 객체에 이렇게 생성자로 넣었음.
        // 그러나 로컬서버에서 미래에 다수의 키로 로봇을 제어하게 되면 이 값도 인자로 받게 고칠수 있어서 일단 생성자에 둠
        //= "/home/sysolab/.ssh/";  "~/.ssh/ssl_rsa" "/root/.ssh/id_rsa"

        this.set_localScriptFilePathAndName(givenLocalDirPathAndName); // 로컬서버에 있는 쉘스크립트파일의 경로와 이름
        this.set_robotDirPath(givenRobotDirPath);
    }


    //Private Methods
    public int connectRobot(){

        try {
            System.out.println("connecting to robot ip : " + this.robotIp());
            System.out.println("robot name : " + this.robotUser());
            System.out.println("robot pw : " + this.robotPassword());

            /* (1) jsch */
            this.set_jsch(new JSch());

            File keyFile = new File(this.privateKeyPath());
            System.out.println("keyFile.getPath() : " + keyFile.getPath());

            //String knownHosts = "/home/sysolab/.ssh/known_hosts";
            //this.jsch().setKnownHosts(knownHosts);

            /*사용자인증*/
            this.jsch().addIdentity(keyFile.getPath(),this.robotPassword());

            /* (2) session 연결 */
            this.set_session(this.jsch().getSession(this.robotUser(), this.robotIp(), this.port()) ); //this.user()
            //this.set_session(this.jsch().getSession(this.user(), this.robotIp(), this.port()) ); //this.user()

            //username and passphrase

            /*세션과 관련된 정보를 설정*/
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");//호스트 키를 확인하지 않겠다
            //config.put("PreferredAuthentications", "password");
            this.session().setConfig(config);

            this.session().setPassword(this.robotPassword());


            /*세션 연결*/
            this.session().connect();
            System.out.println("session connected");

            /* (3) Channel */
            this.set_execChannel((ChannelExec)this.session().openChannel("exec"));
            //System.out.println("ession().openChannel(\"exec\")");


            //~~~~//쉘 스크립트 파일 전송//~~~~~
            //String localDirPathAndName = "/home/sysolab/test1.sh"; //<======== 로컬 서버의 특정한 디렉토리에 쉘스크립트가 보관되어 있음.
            //String fileName = "test1.sh";
            //String robotDirPath = "/home/rasbian";

            this.set_sftpChannel((ChannelSftp)this.session().openChannel("sftp"));
            this.sftpChannel().connect(); /*채널 연결*/

            try {
                this.sftpChannel().cd(this.robotDirPath());// Change to output directory
                File file = new File(this.localScriptFilePathAndName());// Upload file
                FileInputStream fis = new FileInputStream(file);// 입력 파일을 가져온다.
                this.sftpChannel().put(fis, file.getName());// 파일을 업로드한다.
                fis.close();
                System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
            this.sftpChannel().disconnect();//"this.session().disconnect();" X, 세션은 exec으로도 열려있어야해서 닫으면 안됨.


            /* Robot의 쉘 스크립트를 실행시킨다. */
            try {
                InputStream in = this.execChannel().getInputStream();

                ((ChannelExec) this.execChannel()).setCommand("ls -ltr"); //ls -ltr
                //((ChannelExec) this.channel()).setCommand("./test1.sh"); //chmod 755 test1.sh && ./test1.sh

                ((ChannelExec) this.execChannel()).setCommand("chmod 755 test1.sh && ./test1.sh");

                ((ChannelExec)this.execChannel()).setErrStream(System.err);
                this.execChannel().connect(); /*채널 연결*/
                System.out.println(this.robotIp() + "channel connected" + "!! *^^* !!");

                //로봇의 터미널 출력문을 가져와서 콘솔에 출력. read the result form robot(remote server) terminal
                BufferedReader reader = new BufferedReader(new InputStreamReader( in ));
                String line;
                line = reader.readLine();
                System.out.println("from rasbian: " + line);
                while ((line = reader.readLine())!=null){ System.out.println(line);} //입력받을 때까지 while에서 대기

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            /* 세션과 채널을 닫는다. */
            this.execChannel().disconnect();
            this.session().disconnect();

            return 1;

        }catch (JSchException e){
            System.out.println("fail to connect");
            e.printStackTrace();
            return 0;
        }
    }

    //2. disconect to hostRobot
    public void disconnectRobot(){
        System.out.println("disconnecting..." + this.robotIp());
        this.execChannel().disconnect(); //채널 먼저
        this.session().disconnect(); //그다음 세션도
        System.out.println(this.robotIp() + "disconnected");
    }


}