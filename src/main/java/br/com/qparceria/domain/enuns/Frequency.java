package br.com.qparceria.domain.enuns;

public enum Frequency {
	
	SPECIFIC_DATE(1),
	REGULAR(2);
	
	private int id;
	
	private Frequency(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Frequency toEnum(Integer id) {
		if( id == null) {
			return null;
		}
		
		for( Frequency f : Frequency.values()) {
			if(id.equals(f.getId())) {
				return f;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
