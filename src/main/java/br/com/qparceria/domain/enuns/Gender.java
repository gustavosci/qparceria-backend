package br.com.qparceria.domain.enuns;

public enum Gender {
	
	MASCULINO(1, "Masculino"),
	FEMININO(2, "Feminino");
	
	private int id;
	private String describe;
	
	private Gender(int id, String describe) {
		this.id = id;
		this.describe = describe;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescribe() {
		return describe;
	}
	
	public static Gender toEnum(Integer id) {
		if( id == null) {
			return null;
		}
		
		for( Gender g : Gender.values()) {
			if(id.equals(g.getId())) {
				return g;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
