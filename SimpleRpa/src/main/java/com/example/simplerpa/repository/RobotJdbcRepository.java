package com.example.simplerpa.repository;

import com.example.simplerpa.model.Robot;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.simplerpa.JdbcUtils.toLocalDateTime;

@Repository
public class RobotJdbcRepository implements RobotRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RobotJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Robot insert(Robot robot) {
        var update = jdbcTemplate.update("INSERT INTO robots(robot_id, robot_name, ip, port_num, user, password, running, deleted, created_at, updated_at)" +
                " VALUES (:robotId, :robotName, :ip, :portNum, :user, :password, :running, :deleted, :createdAt, :updatedAt)", toRobotParamMap(robot));
        if (update != 1) {
            throw new RuntimeException("Noting wat inserted");
        }
        return robot;
    }

    @Override
    public Robot update(Robot robot) {
        var update = jdbcTemplate.update(
            "UPDATE robots SET robot_name =:robotName, ip =:ip, port_num =:portNum, user =:user, password =:password, running =:running, deleted =:deleted, created_at =:createdAt, updated_at =:updatedAt"+
                " WHERE robot_id = :robotId",
            toRobotParamMap(robot)
        );
        if(update !=1){
            throw new RuntimeException("Nothing was updated");
        }
        return robot;
    }

    @Override
    public List<Robot> findAll() {
        return jdbcTemplate.query("select * from robots", robotRowMapper);
    }

    @Override
    public Optional<Robot> findById(int robotId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM robots WHERE robot_id =:robotId",
                            Collections.singletonMap("robotId", robotId), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Robot> findByName(String robotName) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM robots WHERE robot_name =:robotName",
                    Collections.singletonMap("robotName", robotName), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Robot> findByIp(String ip) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM robots WHERE ip =:ip",
                    Collections.singletonMap("ip", ip), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Robot> findByUser(String user) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM robots WHERE user =:user",
                    Collections.singletonMap("user", user), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Robot> findByRunning(Boolean running) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject("SELECT * FROM robots WHERE running =:running",
                    Collections.singletonMap("running", running), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Robot> deleteByName(String robotName) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject("DELETE FROM robots WHERE robot_name =:robotName",
                    Collections.singletonMap("robotName", robotName), robotRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM robots", Collections.emptyMap());
    }

    private static final RowMapper<Robot> robotRowMapper = (resultSet, i) -> {
        int robotId = resultSet.getInt("robot_id");
        String robotName = resultSet.getString("robot_name");
        String ip = resultSet.getString("ip");
        int port_num = resultSet.getInt("port_num");
        String user = resultSet.getString("user");
        String password = resultSet.getString("password");
        boolean running = resultSet.getBoolean("running");
        boolean deleted = resultSet.getBoolean("deleted");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Robot(robotId, robotName, ip, port_num, user, password, running, deleted, createdAt, updatedAt);
    };

    private Map<String, Object> toRobotParamMap(Robot robot) { // 삽입 또는 수정할 때 필요한 파라미터 맵
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("robotId", robot.getRobotId());
        paramMap.put("robotName", robot.getRobotName());
        paramMap.put("ip", robot.getIp());
        paramMap.put("portNum", robot.getPortNum());
        paramMap.put("user", robot.getUser());
        paramMap.put("password", robot.getPassword());
        paramMap.put("running", robot.isRunning()); // boolean 타입은 get 이 아닌 is...
        paramMap.put("deleted", robot.isDeleted());
        paramMap.put("createdAt", robot.getCreatedAt());
        paramMap.put("updatedAt", robot.getUpdatedAt());
        return paramMap;
    }


}