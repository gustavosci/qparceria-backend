package br.com.qparceria.domain.enuns;

public enum Frequency {
	
	SPECIFIC_DATE(1, "Data específica"),
	REGULAR(2, "Regular");
	
	private int id;
	private String describe;
	
	private Frequency(int id, String describe) {
		this.id = id;
		this.setDescribe(describe);
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
