package siku;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import siku.domain.Invoice;
import siku.domain.Play;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class IO {
    String path;

    IO(String path) {
        this.path = path;
    }

    public HashMap<String, Play> readPlays() {
        String filepath = path + "plays.json";
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Play> playsMap = null;

        try {
            File file = new File(filepath);
            playsMap = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return playsMap;
    }

    public List<Invoice> readInvoices() {
        String filepath = path + "invoices.json";  // JSON 파일명
        ObjectMapper mapper = new ObjectMapper();
        List<Invoice> invoices = null;

        try {
            File file = new File(filepath);
            invoices = mapper.readValue(file, new TypeReference<>() {

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoices;
    }
}
