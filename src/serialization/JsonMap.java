package serialization;

public class JsonMap extends aJsonMap {

	@Override
	public void post_deserialize() {
	}

	@Override
	public void pre_serialize() {
	}

	@Override
	public aJsonMap instantiateObject(String key) {
		return new JsonMap();
	}

}
