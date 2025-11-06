package in.gov.serviceplus.pcpndt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class DiagnosticController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/diagnostic")
    public String diagnostic() {
        StringBuilder result = new StringBuilder();
        result.append("<h1>Application Diagnostic</h1>");

        try {
            // Check database connection
            result.append("<h2>Database Connection</h2>");
            result.append("URL: ").append(dataSource.getConnection().getMetaData().getURL()).append("<br>");

            // Check tables
            String[] tables = {"pcpndt_initiated_data", "pcpndt_execution_data", "pcpndt_filtered_execution"};

            for (String table : tables) {
                String checkSql = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='public' AND table_name=?";
                int exists = jdbcTemplate.queryForObject(checkSql, Integer.class, table);
                result.append("Table ").append(table).append(" exists: ").append(exists > 0).append("<br>");

                if (exists > 0) {
                    String countSql = "SELECT COUNT(*) FROM " + table;
                    int count = jdbcTemplate.queryForObject(countSql, Integer.class);
                    result.append("Rows in ").append(table).append(": ").append(count).append("<br>");
                }
            }

        } catch (Exception e) {
            result.append("<div style='color: red;'>Error: ").append(e.getMessage()).append("</div>");
        }

        return result.toString();
    }
}