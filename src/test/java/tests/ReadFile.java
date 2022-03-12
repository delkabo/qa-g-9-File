package junit.les09homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadFile {

    private static final String
            CSVFILE = "SampleCSVFile.csv",
            XLSXFILE = "SampleXLSFile.xls",
            PDFFILE = "SamplePDFFile.pdf";

    @Test
    void readZip() throws Exception {
        ZipFile zF = new ZipFile("src/test/resources/zipfile.zip");
        Enumeration<? extends ZipEntry> entries = zF.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().contains("csv")) {
                assertThat(entry.getName()).isEqualTo(CSVFILE);
                parseCsvTest(zF.getInputStream(entry));
            } else if (entry.getName().contains("xls")) {
                assertThat(entry.getName()).isEqualTo(XLSXFILE);
                parseXlsTest(zF.getInputStream(entry));
            } else if (entry.getName().contains("pdf")) {
                assertThat(entry.getName()).isEqualTo(PDFFILE);
                parsePdfTest(zF.getInputStream(entry));
            }
        }
    }

    void parseCsvTest(InputStream file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file));) {
            List<String[]> strA = reader.readAll();
            assertThat(strA.get(0)).contains(
                    "Eldon Base for stackable storage shelf, platinum"
            );
        }
    }

    void parseXlsTest(InputStream file) throws Exception {
        XLS xls = new XLS(file);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(0)
                .getCell(0)
                .getStringCellValue()).contains("QA.GURU");

    }

    void parsePdfTest(InputStream file) throws Exception {
        PDF pdf = new PDF(file);
        assertThat(pdf.text).contains(
                "Министерство образования Российской Федерации "
        );

    }


}