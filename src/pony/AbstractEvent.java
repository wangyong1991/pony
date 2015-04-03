package pony;

public class AbstractEvent implements IEvent {

	@Override
	public Class<?> getID()
	{
		return this.getClass();
	}

}
