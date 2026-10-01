import model.Mahasiswa;
import model.AlatUkur;
import model.Alat;
import model.Laptop;
import model.Petugas;
import model.Laboratorium;
import model.Proyektor;
import Transaksi.*;
import java.time.LocalDate;
import layanan.InventarisLab;
public class Main {
    public static void main(String[] args) {
        
        InventarisLab invLab = new InventarisLab();
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023, 16, true);
        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022, 3300, 1200);
        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020, "Volt", LocalDate.of(2024,3,10));

        invLab.tambah(lap);
        invLab.tambah(pro);
        invLab.tambah(ukur);

        
        System.out.println(lap.deskripsi() + " | siap: " + lap.siapDipinjam());
        System.out.println(pro.deskripsi() + " | siap: " + pro.siapDipinjam());
        System.out.println(ukur.deskripsi() + " | siap: " + ukur.siapDipinjam());
        System.out.println(ukur.statusKalibrasi());

        System.out.println("====== Posisi Alat-alat =======");
        System.out.println(lap.ringkasanLacak());
        System.out.println(pro.ringkasanLacak());
        
        System.out.println("====== Status Alat-alat =======");
        System.out.println(" Jumlah alat yang siap dipinjam : " + invLab.hitungSiapDipinjam());

        System.out.println("Daftar Alat yang tidak bisa dipinjam : ");
        for(Alat a : invLab.daftarTidakSiap()){
            
            System.out.println("x. " + a.laporanRingkas());
        }
        
        System.out.println("....");
        System.out.println("....");
        System.out.println("....");
        /* Pertemuan 4 */
        
        Mahasiswa mhs = new Mahasiswa("2205062001", "Rani Simatupang", "TRPL");
        Petugas ptg = new Petugas("198701012015041002", "Bapak Sutrisno");
        
        Laboratorium lab = new Laboratorium("LAB-RPL", "Laboratorium Rekayasa PL");
        lab.tambahAlat(lap);
        lab.tambahAlat(pro);
        System.out.println("Jumlah alat di lab: " + lab.jumlahAlat());
        
        Peminjaman pinjam = new Peminjaman("PJM-0001", mhs, ptg);
        pinjam.tambahDetail(lap, 1);
        pinjam.tambahDetail(pro, 2);
        System.out.println("Total item dipinjam: " + pinjam.totalItem());
        
        for (DetailPeminjaman d : pinjam.getDetail()) {
            System.out.println(" " + d.baris());
        }
        
        System.out.println(" Tes latihan 4 ");
        pinjam.kembalikan(LocalDate.now());
        System.out.println("Hari terlambat   : " + pinjam.hitungHariTerlambat() + " hari");


        pinjam.kembalikan(LocalDate.of(2026, 10, 20));   
        System.out.println("Hari terlambat   : " + pinjam.hitungHariTerlambat() + " hari");
        
        
        
    }
}