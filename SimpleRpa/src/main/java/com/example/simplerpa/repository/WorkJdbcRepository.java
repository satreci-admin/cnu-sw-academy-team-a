package com.example.simplerpa.repository;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.model.Work;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.simplerpa.JdbcUtils.toLocalDateTime;

@Repository
public class WorkJdbcRepository implements WorkRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WorkJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Work insert(Work work) {
        var update = jdbcTemplate.update("INSERT INTO work_statements(email, statement_id, robot_id, statement_name, contents, deleted, scheduler_cron, is_active, created_at, updated_at)" +
                " VALUES (:email, :statementId, :robotId, :name, :contents, :deleted, :schedulerCron, :isActive, :createdAt, :updatedAt)", toParamMap(work));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return work;
    }

    @Override
    public Work update(Work work) {
        var update = jdbcTemplate.update(
                "UPDATE work_statements SET email =:email, statement_id =:statementId, robot_id =:robotId, statement_name =:name,contents =:contents,deleted =:deleted,scheduler_cron =:schedulerCron, is_active =:isActive, created_at = :createdAt,updated_at =:updatedAt"+
                        " WHERE statement_id = :statementId",
                toParamMap(work)
        );
        if(update !=1){
            throw new RuntimeException("Nothing was updated");
        }
        return work;
    }

    @Override
    public List<Work> findAll() {
        return jdbcTemplate.query(
                "select * from work_statements", workRowMapper);
    }

    @Override
    public Optional<Work> findById(int statementId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM work_statements WHERE statement_id =:statementId",
                            Collections.singletonMap("statementId", statementId), workRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Work> findByName(String statementName) {
        try{
            return Optional.of(
                    jdbcTemplate.queryForObject("SELECT * FROM work_statements WHERE statement_name =:statementName",
                            Collections.singletonMap("statementName",statementName),workRowMapper)
            );
        }catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Work> findByEmail(Email email) {
        try{
            return Optional.of(
                    jdbcTemplate.queryForObject("SELECT * FROM work_statements WHERE email=:email",
                            Collections.singletonMap("email",email),workRowMapper)
            );
        }catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM work_statements", Collections.emptyMap());
    }


    private static final RowMapper<Work> workRowMapper = (resultSet, i) -> {
        var email = resultSet.getString("email");
        var statementId = resultSet.getInt("statement_id");
        var robotId = resultSet.getInt("robot_id");
        var name = resultSet.getString("statement_name");
        var contents = resultSet.getString("contents");
        var deleted = resultSet.getBoolean("deleted");
        var schedulerCron = resultSet.getString("scheduler_cron");
        var isActive = resultSet.getInt("is_active");
        var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Work(new Email(email), statementId, robotId, name, contents, deleted, schedulerCron, isActive, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Work work) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("email", work.getEmail().getAddress());
        paramMap.put("statementId", work.getStatementId());
        paramMap.put("robotId", work.getRobotId());
        paramMap.put("name", work.getName());
        paramMap.put("contents", work.getContents());
        paramMap.put("deleted", work.isDeleted());
        paramMap.put("schedulerCron", work.getSchedulerCron());
        paramMap.put("isActive", work.getIsActive());
        paramMap.put("createdAt", work.getCreatedAt());
        paramMap.put("updatedAt", work.getUpdatedAt());
        return paramMap;
    }


}