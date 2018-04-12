package br.com.qparceria.domain.enuns;

public enum Profile {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int id;
	private String describe;
	
	private Profile(int id, String describe) {
		this.id = id;
		this.describe = describe;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescribe() {
		return describe;
	}
	
	public static Profile toEnum(Integer id) {
		if( id == null) {
			return null;
		}
		
		for( Profile g : Profile.values()) {
			if(id.equals(g.getId())) {
				return g;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
