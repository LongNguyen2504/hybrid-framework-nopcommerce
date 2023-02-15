package utilities;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/${env}.properties"})
public interface Environment extends Config {
    @Key("App.Url")
    String appURL();

    @Key("App.User")
    String appUserName();

    @Key("App.Pass")
    String appPassword();

    @Key("DB.Host")
    String databaseHostname();

    @Key("DB.User")
    String databaseUserName();

    @Key("DB.Pass")
    String databasePassword();

/*    App.Url=https://demo.nopcommerce.com/
    App.User=admin@yourstore.com
    App.Pass=admin
    DB.Host=jdbc:mysql://35.16.28.150:4096
    DB.User=nopcommerce
    DB.Pass=admin123*/
}
