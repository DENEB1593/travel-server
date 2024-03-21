package io.everyone.travel.api.config.xss;

import io.everyone.travel.core.util.HtmlUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

class XssPreventRequestWrapper extends HttpServletRequestWrapper {
    public XssPreventRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = HtmlUtils.escape(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value != null) {
            value = HtmlUtils.escape(value);
        }
        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value != null) {
            value = HtmlUtils.escape(value);
        }
        return value;
    }
}
