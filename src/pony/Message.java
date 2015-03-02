package pony;

public class Message implements IMessage {

	@Override
	public Class<?> getID()
	{
		return this.getClass();
	}

}
