package outsourcing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String charSet;
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");	//过滤器设置页面所有的编码为GBK
		chain.doFilter(request, response);	//传递请求
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.charSet = config.getInitParameter("charset");	//从web.xml文件中取得初始化参数
	}

}
