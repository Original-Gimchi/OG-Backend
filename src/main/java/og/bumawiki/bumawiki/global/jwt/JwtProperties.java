package og.bumawiki.bumawiki.global.jwt;

public class JwtProperties {
    public static final Long Access_Token_Valid_Time = 1000L * 60 * 30;
    public static final Long Refresh_Token_Valid_Time = 1000L * 60 * 60 * 24 * 30;

    public static final String JWT_ACCESS = "ACCESS-TOKEN";
}
