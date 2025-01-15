package com.study.board.demo.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

public class Utils {

    public static ModelAndView sendMessage(HttpServletResponse response, String message, String forwardUrl) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head></head><body>");
            out.println("<script language='javascript'>");
            out.println("alert(\"" + message + "\");");
            if (forwardUrl == null) {
                out.println("window.history.back();");
            } else {
                out.println("location.href = \"" + forwardUrl + "\"");
            }
            out.println("</script></body></html>");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
