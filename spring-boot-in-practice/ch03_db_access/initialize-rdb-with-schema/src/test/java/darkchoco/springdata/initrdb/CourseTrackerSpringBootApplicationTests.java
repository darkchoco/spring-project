package darkchoco.springdata.initrdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
        assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
        assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
    }

    @Test
    public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException {
        int noOfCourses = 0;
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT COUNT(1) FROM courses");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                noOfCourses = rs.getInt(1);
            }
            assertThat(noOfCourses).isEqualTo(5L);
        }
    }
}
