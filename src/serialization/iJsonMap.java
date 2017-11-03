package serialization;

public interface iJsonMap {
	public void post_deserialize();
	public void pre_serialize() ;
	public aJsonMap instantiateObject(String key);

}