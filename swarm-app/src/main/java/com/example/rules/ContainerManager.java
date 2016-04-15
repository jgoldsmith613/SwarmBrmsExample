package com.example.rules;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;

import com.rhc.model.RulesContainerDescription;

public class ContainerManager {

	private Map<String, KieContainer> containerMap;

	public ContainerManager() {
		containerMap = new HashMap<String, KieContainer>();
	}

	public void createContainer(RulesContainerDescription description) {
		KieContainer container = KieServices.Factory.get().newKieContainer(makeReleaseId(description));
		containerMap.put(description.getName(), container);
	}

	public void updateContainer(RulesContainerDescription description) {
		containerMap.get(description.getName()).updateToVersion(makeReleaseId(description));
	}

	public KieContainer getContainer(String name) {
		return containerMap.get(name);
	}

	private ReleaseId makeReleaseId(RulesContainerDescription description) {
		return KieServices.Factory.get().newReleaseId(description.getGroupId(), description.getArtifactId(),
				description.getVersion());
	}

}
