package Transaksi;
import model.Mahasiswa;
import model.Alat;
import model.Petugas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class Peminjaman {
        private final String nomorPeminjaman;
        private final LocalDate tanggalPinjam;
        private LocalDate tanggalKembali;
        private LocalDate hariIni;
        
// ASOSIASI: mahasiswa dan petugas hidup mandiri, hanya dirujuk
        private final Mahasiswa peminjam;
        private final Petugas penyetuju;
// KOMPOSISI: detail dibuat di dalam kelas ini dan ikut lenyap bersamanya
        
        private final List<DetailPeminjaman> detail = new ArrayList<>();
        
        public Peminjaman(String nomorPeminjaman, Mahasiswa peminjam, Petugas penyetuju) {
            this.nomorPeminjaman = nomorPeminjaman;
            this.peminjam = peminjam;
            this.penyetuju = penyetuju;
            this.tanggalPinjam = LocalDate.now();
        }
        
        public void tambahDetail(Alat alat, int jumlah) {
            detail.add(new DetailPeminjaman(alat, jumlah));
        }
           
        public List<DetailPeminjaman> getDetail() {
            return Collections.unmodifiableList(detail);
        }
            
        public int totalItem() {
            int total = 0;
            for (DetailPeminjaman d : detail) {
            total += d.getJumlah();
            }
            return total;
        }
        
        public void kembalikan(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public long hitungHariTerlambat() {
        if (tanggalKembali == null) {
            return 0; // belum dikembalikan, dianggap belum terlambat
        }
        LocalDate batasKembali = tanggalPinjam.plusDays(0); //Membuat berapa hari alat terlambat dikembalikan
        long selisih = ChronoUnit.DAYS.between(batasKembali, tanggalKembali); //Digunakan untuk menghitung selisih jumlah hari antara dua objek waktu 

        return Math.max(0, selisih);  //Mengembalikan nilai tertinggi dari antara (saat alat sudah kembali tepat waktu (0)) dan variabel selisih  

    }
}