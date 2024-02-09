package hu.masterfield.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import hu.masterfield.pages.Saving;
import hu.masterfield.testcases.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DataSource {
    protected static Logger logger = LogManager.getLogger(DataSource.class);
    public static List<Saving> loadSavings() {
        logger.info("load savings from file= " + Consts.SAVINGS_DATA_CSV);
        InputStreamReader ls = new InputStreamReader(BaseTest.class.getResourceAsStream(Consts.SAVINGS_DATA_CSV));
        List<Saving> savings = new CsvToBeanBuilder<Saving>(ls)
                .withType(Saving.class)
                .withSeparator(',')
                .build()
                .parse();
        return savings;
    }

    public static void saveSavings(List<Saving> savingList) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(Consts.SAVINGS_DATA_CSV))) {
            new StatefulBeanToCsvBuilder<>(writer)
                    .withOrderedResults(true)
                    .withApplyQuotesToAll(false)
                    .build()
                    .write(savingList);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        }
    }
}