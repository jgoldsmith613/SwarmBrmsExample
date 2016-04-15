package com.rhc.model;


public class RulesContainerDescription {
	
	public String name;
	public String groupId;
	public String artifactId;
	public String version;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "RulesContainerDescription [name=" + name + ", groupId=" + groupId + ", artifactId=" + artifactId
				+ ", version=" + version + "]";
	}
	
	
	

}
