package com.example.simplerpa.repository;

import com.example.simplerpa.model.Robot.Robot;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class RobotJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void setup(){
        var config = aMysqldConfig(v5_6_23)
            .withCharset(Charset.UTF8)
            .withPort(2215)
            .withUser("test", "test1234!")
            .withTimeZone("Asia/Seoul")
            .build();
        embeddedMysql = anEmbeddedMysql(config)
            .addSchema("test-simple_rpa", ScriptResolver.classPathScript("schema.sql"))
            .start();
    }

    @AfterAll
    static void cleanup() {
        embeddedMysql.stop();
    }

    @Autowired
    RobotRepository repository;

    private final Robot newRobot = new Robot("robot1", "127.0.0.1", 22, "test2", "test2");

    @Test
    @Order(1)
    @DisplayName("로봇을 추가할 수 있다.")
    void testInsert(){
        repository.insert(newRobot);
        var all = repository.findAll();
        assertThat(all.isEmpty(), is(false));
    }

    @Test
    @Order(2)
    @DisplayName("이름으로 로봇 조회")
    void testFindByName() {
        var robot = repository.findByName(newRobot.getRobotName());
        assertThat(robot.isEmpty(), is(false));
    }

    @Test
    @Order(3)
    @DisplayName("ip로 로봇 조회")
    void testFindByIp() {
        var robot = repository.findByIp(newRobot.getIp());
        assertThat(robot.isEmpty(), is(false));
    }

    @Test
    @Order(4)
    @DisplayName("user로 로봇 조회")
    void testFindByCategory() {
        var robot = repository.findByUser(newRobot.getUser());
        assertThat(robot.isEmpty(), is(false));
    }

    @Test
    @Order(5)
    @DisplayName("update 로봇")
    void testUpdate() {
        newRobot.setRunning(true);
        repository.update(newRobot);

        var robot = repository.findByName(newRobot.getRobotName());
        assertThat(robot.isEmpty(), is(false));
//        assertThat(robot.get(), samePropertyValuesAs(newRobot));
    }
//
//    @Test
//    @Order(6)
//    @DisplayName("delete all.")
//    void testDeleteAll() {
//        repository.deleteAll();
//        var all = repository.findAll();
//        assertThat(all.isEmpty(), is(true));
//    }
}