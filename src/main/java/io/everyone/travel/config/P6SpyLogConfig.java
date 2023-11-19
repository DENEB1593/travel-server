package io.everyone.travel.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.stream.Stream;

@Configuration
public class P6SpyLogConfig implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        if (P6SpyOptions.getActiveInstance() != null) {
            P6SpyOptions.getActiveInstance().setLogMessageFormat(P6SpySqlFormatter.class.getName());
        }
    }


    public static class P6SpySqlFormatter implements MessageFormattingStrategy {

        private final static DateTimeFormatter SQL_DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        private final static String THIS_PACKAGE = "io.everyone.travel";

        @Override
        public String formatMessage(
            int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

            if (sql == null || sql.isEmpty()) {
                return "";
            }

            return String.format("Time: %s | SQL: %s",
                    SQL_DATE_FORMAT.format(LocalDateTime.now()) + " | " + elapsedInfo(elapsed),
                    formatSql(category, sql)
            );
        }
    }

    private static String formatSql(String category, String sql) {

        if (Category.STATEMENT.getName().equals(category)) {
            String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
            if(tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            }else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
            sql = sql;
        }

        return sql;
    }

    private static String elapsedInfo(long elapsed) {
        return String.format("Elapsed time %d ms", elapsed);
    }

    private static String stackTraceInfo() {
        Queue<String> call = new LinkedList<>();

        Stream.of(new Throwable().getStackTrace())
            .map(StackTraceElement::toString)
            .filter(trace -> trace.contains(P6SpySqlFormatter.THIS_PACKAGE) && !trace.contains("P6SpySqlPostProcessor"))
            .forEach(call::add);

        StringBuilder callStackBuilder = new StringBuilder();
        while (!call.isEmpty()) {
            callStackBuilder.append(MessageFormat.format("{0}\t {1}", "\n", call.poll()));
        }
        return callStackBuilder.toString();
    }
}
