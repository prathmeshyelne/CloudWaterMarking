package models;

import java.sql.ResultSet;

public class States {
private String state;

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}
public States(ResultSet rs)
{
	try
	{
	state=rs.getString("state").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public States()
{
	
}
}
