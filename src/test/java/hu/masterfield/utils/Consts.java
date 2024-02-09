package hu.masterfield.utils;

/**
 * Ez az osztály konstansokat tartalmaz, amelyeket külső forrásból, property file-ból (.prop) olvasunk be.
 **/

public class Consts {
    // Konstansok az applikációhoz
    public static final String APPLICATION_URL = "application.url";

    // Konstansok az elérési utakhoz
    public static final String CONFIG_PROPERTIES = "/config.properties";
    public static final String GLOBAL_TEST_DATA_PROPERTIES = "/globalTestData.properties";
    public static final String SCREENSHOTS_FOLDER = System.getProperty("user.dir").replace("\\", "/") + "/target/screenshots";

    // KOnstansok a myProfilePage-hez
    public static final String TITLE = "Title";
    public static final String DEF_TITLE = "def.title";
    public static final String FIRST_NAME = "First Name";
    public static final String DEF_FIRST_NAME = "def.firstName";
    public static final String LAST_NAME = "Last Name";
    public static final String DEF_LAST_NAME = "def.lastName";
    public static final String HOMEPHONE = "Home Phone";
    public static final String DEF_HOMEPHONE = "def.homephone";
    public static final String MOBILEPHONE = "Mobile Phone";
    public static final String DEF_MOBILEPHONE = "def.mobilephone";
    public static final String WORKPHONE = "Work Phone";
    public static final String DEF_WORKPHONE = "def.workphone";
    public static final String ADDRESS = "Address";
    public static final String DEF_ADDRESS = "def.address";
    public static final String LOCALITY = "Locality";
    public static final String DEF_LOCALITY = "def.locality";
    public static final String REGION = "Region";
    public static final String DEF_REGION = "def.region";
    public static final String POSTAL_CODE = "Postal Code";
    public static final String DEF_POSTAL_CODE = "def.postalcode";
    public static final String COUNTRY = "Country";
    public static final String DEF_COUNTRY = "def.country";


    // Konstansok a loginhoz
    public static final String LOGIN_USERNAME = "login.username";
    public static final String LOGIN_PASSWORD = "login.password";
    public static final String LOGIN_WELCOME = "login.welcome";


    // Konstansok a Savings-hez
    public static final String ACCOUNT_TYPES_SAVINGS = "Savings";
    public static final String ACCOUNT_TYPES_MONEY_MARKET = "Money Market";
    public static final String OWNERSHIP_TYPES_INDIVIDUAL = "Individual";
    public static final String OWNERSHIP_TYPES_JOINT = "Joint";
    public static final String SAVINGS_DATA_CSV = "/savingsData.csv";
    public static final String SAVE_SAVINGS_DATA_CSV = "target/dumpSavings.csv";


    // Konstansok a regisztrációhoz
    public static final String REG_TITLE = "reg.title";
    public static final String REG_FIRST_NAME = "reg.firstName";
    public static final String REG_LAST_NAME = "reg.lastName";
    public static final String REG_GENDER = "reg.gender";
    public static final String REG_DATE_OF_BIRTH = "reg.dateOfBirth";
    public static final String REG_SOCIAL_SECURITY_NUMBER = "reg.socialSecurityNumber";
    public static final String REG_EMAIL_ADDRESS = "reg.emailAddress";
    public static final String REG_PASSWORD = "reg.password";
    public static final String REG_CONFIRM_PASSWORD = "reg.password";
    public static final String REG_ADDRESS = "reg.address";
    public static final String REG_LOCALITY = "reg.locality";
    public static final String REG_REGION = "reg.region";
    public static final String REG_POSTAL_CODE = "reg.postalCode";
    public static final String REG_COUNTRY = "reg.country";
    public static final String REG_HOME_PHONE = "reg.homePhone";
    public static final String REG_MOBILE_PHONE = "reg.mobilePhone";
    public static final String REG_WORK_PHONE = "reg.workPhone";

    // Konstansok a profiladatok módosításához
    public static final String MOD_TITLE = "mod.title";
    public static final String MOD_FIRST_NAME = "mod.firstName";
    public static final String MOD_LAST_NAME = "mod.lastName";
    public static final String MOD_ADDRESS = "mod.address";
    public static final String MOD_LOCALITY = "mod.locality";
    public static final String MOD_REGION = "mod.region";
    public static final String MOD_POSTAL_CODE = "mod.postalCode";
    public static final String MOD_COUNTRY = "mod.country";
    public static final String MOD_HOME_PHONE = "mod.homePhone";
    public static final String MOD_WORK_PHONE = "mod.workPhone";
    public static final String MOD_MOBILE_PHONE = "mod.mobilePhone";

    public static final String USER_ID = "api.userID";
}