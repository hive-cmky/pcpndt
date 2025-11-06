package in.gov.serviceplus.pcpndt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DatabaseCheckService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void checkDatabase() {
        try {
            System.out.println("=== DATABASE CONNECTION CHECK ===");
            System.out.println("Database URL: " + dataSource.getConnection().getMetaData().getURL());

            // Check all three tables
            String[] tables = {"pcpndt_initiated_data", "pcpndt_execution_data", "pcpndt_filtered_execution"};

            for (String table : tables) {
                String checkTableSql = "SELECT COUNT(*) FROM information_schema.tables " +
                        "WHERE table_schema = 'public' AND table_name = ?";
                int tableCount = jdbcTemplate.queryForObject(checkTableSql, Integer.class, table);
                System.out.println("Table " + table + " exists: " + (tableCount > 0));

                if (tableCount > 0) {
                    String countSql = "SELECT COUNT(*) FROM " + table;
                    int rowCount = jdbcTemplate.queryForObject(countSql, Integer.class);
                    System.out.println("Total rows in " + table + ": " + rowCount);

                    if (rowCount > 0 && table.equals("pcpndt_initiated_data")) {
                        String sampleSql = "SELECT appl_id, applicant_name, name_of_centre FROM " + table + " LIMIT 3";
                        jdbcTemplate.query(sampleSql, (rs, rowNum) -> {
                            System.out.println("Sample - ID: " + rs.getLong("appl_id") +
                                    ", Name: " + rs.getString("applicant_name") +
                                    ", Centre: " + rs.getString("name_of_centre"));
                            return null;
                        });
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Database check failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}