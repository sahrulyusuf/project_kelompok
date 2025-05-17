import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/input_data_karyawan"; // Ganti dengan nama DB kamu
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Atur sesuai password MySQL kamu

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
