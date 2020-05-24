package util;

import lombok.Getter;

public class Path {
    public static class Web{
        @Getter public static final String API = "/api/";
        @Getter public static final String HOME = API + "home";
    }

    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
    }
}