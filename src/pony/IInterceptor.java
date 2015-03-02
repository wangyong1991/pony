package pony;
/**
 * 拦截器接口<br>
 * 对客户端请求的合法性进行校验，拦截不合法的请求
 * @author WangYong
 *
 * @Date 2015年2月26日
 */
public interface IInterceptor {
	public boolean intercept(final IContext context, final IRequest request);
}
