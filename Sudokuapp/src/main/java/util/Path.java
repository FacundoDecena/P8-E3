package util;

import lombok.Getter;

public class Path {
    public static class Web{
        @Getter public static final String HOME = "home";
        @Getter public static final String READ = "localhosthome";
        @Getter public static final String UPDATE = "home";
    }

    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
    }
}