package com.pofil.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import groovy.transform.ToString;

@ToString
@Document(collection = "insuranceschemas")
public class InsuranceSchema {
    @Id
    private String id;
    private String insCompanyCode;
    private String insSchemaCode;
    private String insSchemaName;
    private String schemaType;
    private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInsCompanyCode() {
		return insCompanyCode;
	}
	public void setInsCompanyCode(String insCompanyCode) {
		this.insCompanyCode = insCompanyCode;
	}
	public String getInsSchemaCode() {
		return insSchemaCode;
	}
	public void setInsSchemaCode(String insSchemaCode) {
		this.insSchemaCode = insSchemaCode;
	}
	public String getInsSchemaName() {
		return insSchemaName;
	}
	public void setInsSchemaName(String insSchemaName) {
		this.insSchemaName = insSchemaName;
	}
	public String getSchemaType() {
		return schemaType;
	}
	public void setSchemaType(String schemaType) {
		this.schemaType = schemaType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
