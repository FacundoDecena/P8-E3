package util;

import lombok.Getter;

public class Path {
    public static class Web{
        @Getter public static final String HOME = "home";
        @Getter public static final String SOLVE = "solve";
        @Getter public static final String BLACKBOARD = "http://localhost:8888/";
        @Getter public static final String UPDATE = "update";
        @Getter public static final String READ = "read";
    }

    public static class Template {
        public final static String LAYOUT = "templates/layout.vtl";
    }
}