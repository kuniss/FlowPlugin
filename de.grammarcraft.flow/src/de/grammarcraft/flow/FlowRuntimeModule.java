/*
 * generated by Xtext
 */
package de.grammarcraft.flow;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class FlowRuntimeModule extends de.grammarcraft.flow.AbstractFlowRuntimeModule {

	public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
        return de.grammarcraft.flow.generator.FlowGenerator.class;
    }
}
