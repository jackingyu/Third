package com.third.facade.populator;

public interface ModifiableConfigurablePopulator<SOURCE, TARGET, OPTION>
		extends ConfigurablePopulator<SOURCE, TARGET, OPTION> {
	void addModification(
			ConfigurablePopulatorModification<SOURCE, TARGET, OPTION> modification);

	void applyModification(
			ConfigurablePopulatorModification<SOURCE, TARGET, OPTION> modification);
}
