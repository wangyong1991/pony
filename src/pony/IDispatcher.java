package pony;

/**
 * 消息分派
 * 
 * <pre>
 * 该类主要用于实现消息的分派
 * 通过{@link register}方法注册{@link IHandler}对象，以实现{@link IEvent}与
 * {@link IHandler}之间的映射关系
 * 当有消息需要分派时，调用{@link dispatch}方法将消息交予对应的Handler去处理
 * </pre>
 * @author &#x738B;&#x52C7;
 * @version 1.0
 */
public interface IDispatcher {
	/**
	 * 注册{@link IHandler}对象
	 * <p>
	 * 以实现{@link IEvent}与{@link IHandler}之间的映射关系
	 * </p>
	 * 
	 * @param _clazz
	 * @param _handler
	 */
	void register(Class<? extends IEvent> _clazz, IHandler _handler);

	/**
	 * 消息分派
	 * <p>
	 * 将消息交予对应的Handler去处理
	 * </p>
	 * @param _message
	 */
	void dispatch(final IEvent _message);

}
