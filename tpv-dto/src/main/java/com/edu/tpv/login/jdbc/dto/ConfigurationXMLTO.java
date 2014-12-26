package com.edu.tpv.login.jdbc.dto;


public class ConfigurationXMLTO extends DTO{
	 private String idEnterprise;
	 private String driverClass;
	 private String password;
	 private String url;
	 private String username;
	 private String dialect;
	 private int groupSystems;
	 private String jndiUrl;
	 private String jndiClass;
	 private String datasource;
	public ConfigurationXMLTO() {
		super();
	}
	public ConfigurationXMLTO(String idEnterprise, String driverClass,
			String password, String url, String username, String dialect,
			int groupSystems, String jndiUrl, String jndiClass,
			String datasource) {
		super();
		this.idEnterprise = idEnterprise;
		this.driverClass = driverClass;
		this.password = password;
		this.url = url;
		this.username = username;
		this.dialect = dialect;
		this.groupSystems = groupSystems;
		this.jndiUrl = jndiUrl;
		this.jndiClass = jndiClass;
		this.datasource = datasource;
	}
	public String getIdEnterprise() {
		return idEnterprise;
	}
	public void setIdEnterprise(String idEnterprise) {
		this.idEnterprise = idEnterprise;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public int getGroupSystems() {
		return groupSystems;
	}
	public void setGroupSystems(int groupSystems) {
		this.groupSystems = groupSystems;
	}
	public String getJndiUrl() {
		return jndiUrl;
	}
	public void setJndiUrl(String jndiUrl) {
		this.jndiUrl = jndiUrl;
	}
	public String getJndiClass() {
		return jndiClass;
	}
	public void setJndiClass(String jndiClass) {
		this.jndiClass = jndiClass;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigurationXMLTO [idEnterprise=")
				.append(idEnterprise).append(", driverClass=")
				.append(driverClass).append(", password=").append(password)
				.append(", url=").append(url).append(", username=")
				.append(username).append(", dialect=").append(dialect)
				.append(", groupSystems=").append(groupSystems)
				.append(", jndiUrl=").append(jndiUrl).append(", jndiClass=")
				.append(jndiClass).append(", datasource=").append(datasource)
				.append("]");
		return builder.toString();
	}
	 
	 
	
}
