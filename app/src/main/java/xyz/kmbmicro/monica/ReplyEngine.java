package xyz.kmbmicro.monica;

import java.util.LinkedList;

public class ReplyEngine {
    public static String[] getReply(String str) {
        LinkedList<String> resultList = new LinkedList<>();

        str = str.toLowerCase();
        if(str.contains("saldo")) {
            resultList.add("[DELAY] 500");
            resultList.add("Tunggu Sebentar ...");
            resultList.add("[DELAY] 1500");
            resultList.add("[API_SALDO]");
        } else {
            resultList.add("[DELAY] 1000");
            resultList.add("Silahkan coba lagi");

        }
        return resultList.toArray(new String[0]);
    }
}
