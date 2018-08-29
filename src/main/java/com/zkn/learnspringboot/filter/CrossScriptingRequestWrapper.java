package com.zkn.learnspringboot.filter;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.List;

public class CrossScriptingRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest orgRequest;

    private static final List<String> allowRUIs = Collections.singletonList("/api/server/profile");

    CrossScriptingRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        String uri = super.getRequestURI();
        if (value != null && !checkAllow(uri)) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        String uri = super.getRequestURI();
        if (checkAllow(uri)) {
            return values;
        }

        int count = values.length;
        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {
            encodedValues[i] = xssEncode(values[i]);
        }
        return encodedValues;
    }

    /**
     * 是否允许的请求
     *
     * @param uri
     * @return
     */
    private boolean checkAllow(String uri) {
        for (String allowRUI : allowRUIs) {
            if (uri.contains(allowRUI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param value
     * @return
     */
    private static String xssEncode(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return value.replace('<', '＜')
                .replace('>', '＞')
                .replace('(', '（')
                .replace(')', '）');
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    private HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof CrossScriptingRequestWrapper) {
            return ((CrossScriptingRequestWrapper) req).getOrgRequest();
        }
        return req;
    }
}
