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

        // セッションからユーザーオブジェクトを取得
        Object userObject = req.getSession(false).getAttribute("user");

        // ユーザーオブジェクトがManageUserかParentsUserのインスタンスかを確認
        if (userObject instanceof ManageUser || userObject instanceof ParentsUser) {
            // 認証済みの場合、次のフィルタに処理を渡す
            chain.doFilter(request, response);
        } else {
            // ユーザーが存在しない場合、ログインページにリダイレクト
            res.sendRedirect(req.getContextPath() + "/bussystem/login.jsp");
        }
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


