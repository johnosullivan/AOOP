package edu.comp373.model.patterns;

public interface Request {

	void attach(Observer user);
	
	public void accept(ReportPartVisitor reportPartVisitor);	
}
