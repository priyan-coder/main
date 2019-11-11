package ui;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import executor.command.CommandExport;
import executor.command.CommandType;
import org.junit.jupiter.api.Test;
import storage.StorageManager;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandExportTest {

    @Test
    void execute() {
        StorageManager storageManager = new StorageManager();
        CommandExport c1 = new CommandExport("export");

        Receipt receiptOne = new Receipt(10.0);
        receiptOne.addTag("transport");
        receiptOne.setDate(LocalDate.parse("2019-02-01"));
        storageManager.getWallet().addReceipt(receiptOne);


        try {
            c1.setFileName("testData.csv");
            c1.execute(storageManager);
        } catch (Exception e) {
           e.printStackTrace();
        }
        assertEquals(CommandType.EXPORT, c1.getCommandType());

        Receipt receiptTwo = new Receipt(15.0);
        receiptTwo.addTag("food");
        receiptTwo.setDate(LocalDate.parse("2019-02-02"));
        storageManager.getWallet().addReceipt(receiptTwo);

        CommandExport c2 = new CommandExport("export hahaha");
        c2.setFileName("testData.csv");
        try {
            c2.execute(storageManager);
            String error1 = "Incorrect Command but DUKE$$$ understands"
                    + " you would want to export Wallet to csv !\n";
            assertTrue(c2.getInfoCapsule().getOutputStr().contains(
                    "testData.csv has been created ! Please check the project folder \n" + error1));
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }

        Receipt receiptThree = new Receipt(20.0);
        receiptThree.addTag("dating");
        receiptThree.setDate(LocalDate.parse("2019-02-03"));
        storageManager.getWallet().addReceipt(receiptThree);

        CommandExport c3 = new CommandExport("export");
        c3.setFileName("testData.csv");
        try {
            c3.execute(storageManager);
            String headerMessage = "testData.csv has been created ! Please check the project folder \n";
            assertTrue(c3.getInfoCapsule().getOutputStr().contains(headerMessage));
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        String description = "Exports txt into CSV\n"
                + "FORMAT : export \n";
        assertEquals(CommandType.EXPORT, c3.getCommandType());
        assertEquals(description, c3.getDescription());

        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            System.out.println(storageManager.getPrintableReceipts());
            FileReader filereader = new FileReader("testData.csv");

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
//                    System.out.print(cell + "\t");
                }
//                System.out.println();
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
    }
}
