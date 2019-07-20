package xyz.kmbmicro.monica;

import java.util.LinkedList;

public class ReplyEngine {
    public static String[] getReply(String str) {
        LinkedList<String> resultList = new LinkedList<>();

        str = str.toLowerCase();
        if(str.contains("saldo")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Tunggu Sebentar ...");
            resultList.add("[DELAY] 1500");
            resultList.add("[API_SALDO]");
        } else if(str.contains("transfer")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Baik, silahkan masukkan nomor rekening tujuan");
        }  else if(str.contains("1000009172")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Baik, silahkan masukkan jumlah transfer");
        } else if(str.contains("100000")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Apakah anda yakin transfer ke Nomor rekening 1000009172, Atas nama Abdul Arfan sebesar Rp. 100.000,- ?\n\nKetik YA atau TIDAK");
        } else if(str.contains("ya")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Transfer ke Nomor rekening 1000009172 Atas Nama Abdul Arfan sebesar Rp. 100.000,- Berhasil\nSekarang saldo anda Rp. 4.900.000,-");
            resultList.add("[DELAY] 1000");
            resultList.add("Apakah ada pertanyaan lain?");
        } else if(str.contains("tidak")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Terima kasih telah menghubungi layanan Mandiri Online");
        } else if(str.contains("gopay")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Baik, silahkan masukkan nomor telepon tujuan");
        }  else if(str.contains("081293321203")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Baik, silahkan masukkan jumlah top-up\n1. 10000\n2. 25000\n3. 100000\n\nKetik angka");
        } else if(str.equals("1")) {
            resultList.add("[DELAY] 1000");
            resultList.add("Top-up ke Nomor telepon 081293321203 sebesar Rp. 10.000 berhasil\nApakah ada pertanyaan lain?");
        } else {
            resultList.add("[DELAY] 1000");
            resultList.add("Silahkan coba lagi");

        }
        return resultList.toArray(new String[0]);
    }
}
