package br.com.qparceria.domain.enuns;

public enum WeekDays {
	
	MONDAY(1, "Segunda"),
	TUESDAY(2, "Terça"),
	WEDNESDAY(3, "Quarta"),
	THURSDAY(4, "Quinta"),
	FRIDAY(5, "Sexta"),
	SATURDAY(6, "Sábado"),
	SUNDAY(7, "Domingo");	
	
	private int id;
	private String describe;
	
	private WeekDays(int id, String describe) {
		this.id = id;
		this.describe = describe;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescribe() {
		return describe;
	}
	
	public static WeekDays toEnum(Integer id) {
		if( id == null) {
			return null;
		}
		
		for( WeekDays w : WeekDays.values()) {
			if(id.equals(w.getId())) {
				return w;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
