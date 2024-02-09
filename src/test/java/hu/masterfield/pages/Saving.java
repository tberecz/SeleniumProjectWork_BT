package hu.masterfield.pages;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

/** A Saving típusú account-ok megadásakor használt adatok osztályba szervezése
 */

public class Saving {
    // fiók típusa
    // Savings
    // Money Market
    @CsvBindByName(column = "accountTypes")
    private String accountTypes;

    //tulajdonos típus
    //Individual
    //Joint
    @CsvBindByName(column = "ownershipTypes")
    private String ownerShipTypes;

    //account name
    @CsvBindByName(column = "accountName")
    private String accountName;

    // opening balance
    @CsvBindByName(column = "openingBalance")
    private String openingBalance;
}