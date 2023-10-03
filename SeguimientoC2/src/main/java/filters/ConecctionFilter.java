package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConecctionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try (Connection conn = ConnectionDB.getInstance()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (ServiceJDBCException e) {
                conn.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        e.getMessage());
                        e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
