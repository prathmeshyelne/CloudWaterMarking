package models;

import java.sql.ResultSet;

public class Cities {
private String state,city;

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}
public Cities(ResultSet rs)
{
	try
	{
	state=rs.getString("state").toString().trim();
	city=rs.getString("city").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
}
