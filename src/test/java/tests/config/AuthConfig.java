package tests.config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "C:/Users/delkabo/Desktop/auth2.properties",
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();

}
