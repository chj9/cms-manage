package com.dliberty.cms.common.logging;

import com.dliberty.cms.common.util.Pair;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SouthernQuietLogFormatter {
    public Pair<String, List<?>> formatLogContext(SouthernQuietLogger.LogContext logContext) {
        String format = generateFormat(logContext);
        List<Object> parameters = generateParameters(logContext);
        String msg = logContext.getMessage();

        String result = combine(logContext, msg, format);

        return new Pair<>(result, parameters);
    }

    protected String combine(SouthernQuietLogger.LogContext logContext, String msg, String format) {
        if (null != logContext.getThrowable()) {
            return msg + "\t" + format + "\n" + formatThrowable(logContext.getThrowable(), "");
        }
        else {
            return msg + "\t" + format;
        }
    }

    protected String generateFormat(SouthernQuietLogger.LogContext logContext) {
        return logContext.getContext().keySet().stream().map(key -> key + "={}").collect(Collectors.joining(", "));
    }

    @SuppressWarnings("rawtypes")
    protected List<Object> generateParameters(SouthernQuietLogger.LogContext logContext) {
        return logContext.getContext().values().stream()
            .map(v -> {
                if (v instanceof Supplier) {
                    return ((Supplier) v).get();
                }
                else {
                    return v;
                }
            })
            .collect(Collectors.toList());
    }

    protected String formatThrowable(Throwable throwable, String indent) {
        if (null == throwable) return "";

        StringBuilder sb = new StringBuilder(throwable.toString());
        final String currentIndent = indent + "\t";

        String stack = Arrays.stream(throwable.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n" + currentIndent + "at "));
        String suppressed = Arrays.stream(throwable.getSuppressed()).map(t -> formatThrowable(t, currentIndent)).collect(Collectors.joining("\n" + currentIndent + "at "));
        String cause = formatThrowable(throwable.getCause(), currentIndent);

        sb.append("\n").append(stack);

        if (StringUtils.hasText(suppressed)) {
            sb.append("\n").append(currentIndent).append("Suppressed: ").append(suppressed);
        }

        if (StringUtils.hasText(cause)) {
            sb.append("\n").append(currentIndent).append("Caused by: ").append(cause);
        }

        return sb.toString();
    }
}
