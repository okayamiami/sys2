package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ManageUser;
import bean.ParentsUser;

@WebFilter(urlPatterns = { "/bussystem/main/*" })
public class LoginRequiredFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // セッション属性を取得（新しいセッションを作成しない）
        ManageUser user = (ManageUser) req.getSession(false).getAttribute("user");
        ParentsUser user2 = (ParentsUser) req.getSession(false).getAttribute("user2");

        // ユーザーが存在しない場合、ログインページにリダイレクト
        if (user == null && user2 == null) {
            res.sendRedirect(req.getContextPath() + "/bussystem/login.jsp");
            return;
        }

        // 認証済みの場合、次のフィルタに処理を渡す
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 任意: 初期化コードをここに追加
    }

    @Override
    public void destroy() {
        // 任意: リソース解放などのコードをここに追加
    }
}


