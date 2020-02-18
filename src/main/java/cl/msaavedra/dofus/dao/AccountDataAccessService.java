package cl.msaavedra.dofus.dao;

import cl.msaavedra.dofus.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class AccountDataAccessService implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account insertAccount(UUID id, Account account) {
        final String sql = "INSERT INTO account(id, name) VALUES(?, ?) RETURNING *";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{ id, account.getName() },
                (resultSet, i) -> {
                    UUID accountId = UUID.fromString(resultSet.getString("id"));
                    String accountName = resultSet.getString("name");

                    return new Account( accountId, accountName );
                }
        );
    }

    @Override
    public List<Account> selectAllAccounts() {
        final String sql = "SELECT * FROM account";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");

            return new Account(id, name);
        });
    }

    @Override
    public Optional<Account> selectAccountById(UUID id) {
        final String sql = "SELECT id, name FROM account WHERE id = ?";

        Account account = jdbcTemplate.queryForObject(
            sql,
            new Object[]{id},
            (resultSet, i) -> {
                UUID accountId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");

                return new Account(accountId, name);
            });

        return Optional.ofNullable(account);
    }

    @Override
    public Account deleteAccountById(UUID id) {
        final String sql = "DELETE FROM account WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{ id },
                (resultSet, i) -> {
                    UUID accountId = UUID.fromString(resultSet.getString("id"));
                    String accountName = resultSet.getString("name");

                    return new Account( accountId, accountName );
                }
        );
    }

    @Override
    public Account updateAccountById(UUID id, Account account) {
        final String sql = "UPDATE account SET name = ? WHERE id = ? RETURNING *";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{ account.getName(), id },
                (resultSet, i) -> {
                    UUID accountId = UUID.fromString(resultSet.getString("id"));
                    String accountName = resultSet.getString("name");

                    return new Account( accountId, accountName );
                }
        );
    }
}
