import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ModelKaryawan {

    // Simpan data karyawan ke database
    public void simpan(String nik, String nama, String ttl, String jk, String alamat, String telp, String email) {
        try {
            String sql = "INSERT INTO karyawan VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
            ps.setString(1, nik);
            ps.setString(2, nama);
            ps.setString(3, ttl);
            ps.setString(4, jk);
            ps.setString(5, alamat);
            ps.setString(6, telp);
            ps.setString(7, email);
            ps.executeUpdate();
            ps.close();
            System.out.println("Data karyawan berhasil disimpan.");
        } catch (SQLException e) {
            System.err.println("Gagal simpan: " + e.getMessage());
        }
    }

    // Edit data karyawan
    public void edit(String nik, String nama, String ttl, String jk, String alamat, String telp, String email) {
        try {
            String sql = "UPDATE karyawan SET nama=?, tempat_tanggal_lahir=?, jenis_kelamin=?, alamat=?, no_tlpn=?, email=? WHERE nik=?";
            PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, ttl);
            ps.setString(3, jk);
            ps.setString(4, alamat);
            ps.setString(5, telp);
            ps.setString(6, email);
            ps.setString(7, nik);
            ps.executeUpdate();
            ps.close();
            System.out.println("Data karyawan berhasil diubah.");
        } catch (SQLException e) {
            System.err.println("Gagal edit: " + e.getMessage());
        }
    }

    // Hapus data karyawan berdasarkan NIK
    public void hapus(String nik) {
        try {
            String sql = "DELETE FROM karyawan WHERE nik=?";
            PreparedStatement ps = Koneksi.getConnection().prepareStatement(sql);
            ps.setString(1, nik);
            ps.executeUpdate();
            ps.close();
            System.out.println("Data karyawan berhasil dihapus.");
        } catch (SQLException e) {
            System.err.println("Gagal hapus: " + e.getMessage());
        }
    }

    // Ambil semua data karyawan dan tampilkan ke tabel
    public DefaultTableModel tampilkanData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Tempat Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("No Telepon");
        model.addColumn("Email");

        try {
            String sql = "SELECT * FROM karyawan";
            Statement st = Koneksi.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nik"),
                    rs.getString("nama"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("alamat"),
                    rs.getString("no_tlpn"),
                    rs.getString("email")
                });
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.err.println("Gagal tampilkan data: " + e.getMessage());
        }

        return model;
    }
}
